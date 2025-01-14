package com.example.proyectopmdm

import android.content.Context
import android.util.Log
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.tasks.await

class GoogleAuthClient(
    private val context: Context,
) {
    private val tag = "GoogleSignInClient: "

    private val credentialManager = CredentialManager.create(context)

    private val firebaseAuth = FirebaseAuth.getInstance()

    private val db = FirebaseFirestore.getInstance()

    var userName: String? = null
    var userId: String? = null
    var userPhotoUrl: String? = null

    suspend fun signOut() {
        credentialManager.clearCredentialState(
            ClearCredentialStateRequest()
        )
        firebaseAuth.signOut()
        userName = null
        userId = null
        userPhotoUrl = null
    }

    fun isSignedIn(): Boolean {
        if (firebaseAuth.currentUser != null) {
            println(tag + "already signed in")
            return true
        }
        return false
    }

    suspend fun signIn(): Boolean {
        if (isSignedIn()) {
            updateUserInfo() // Actualiza la información del usuario si ya está firmado
            return true
        }

        try {
            println("$tag Starting signIn process")
            val result = buildCredentialRequest()
            println("$tag Credential request successful")
            return handleSignIn(result)
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            println("$tag signIn error ${e.message}")
            return false
        }
    }

    private suspend fun buildCredentialRequest(): GetCredentialResponse {
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(
                GetGoogleIdOption.Builder()
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(
                        "879259244644-mc48m6mv2ibmss41tbt2apm4ivqg3422.apps.googleusercontent.com"
                    )
                    .setAutoSelectEnabled(false)
                    .build()
            )
            .build()

        println("$tag Building credential request")
        return credentialManager.getCredential(
            request = request,
            context = context
        ).also {
            println("$tag Credential request built successfully")
        }
    }

    private suspend fun handleSignIn(result: GetCredentialResponse): Boolean {
        val credential = result.credential

        if (credential is CustomCredential &&
            credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
        ) {
            try {
                val tokenCredential = GoogleIdTokenCredential.createFrom(credential.data)

                println(tag + "name: ${tokenCredential.displayName}")
                println(tag + "email: ${tokenCredential.id}")
                println(tag + "image: ${tokenCredential.profilePictureUri}")

                val authCredential = GoogleAuthProvider.getCredential(
                    tokenCredential.idToken, null
                )

                val authResult = firebaseAuth.signInWithCredential(authCredential).await()

                if (authResult.user != null) {
                    updateUserInfo()
                    addUserWithDefaultMoney(authResult.user!!.uid)
                    return true
                }

            } catch (e: GoogleIdTokenParsingException) {
                println("$tag GoogleIdTokenParsingException: ${e.message}")
                return false
            }

        } else {
            println("$tag credential is not a GoogleIdTokenCredential")
            return false
        }
        return false
    }

    fun updateUserInfo() {
        val currentUser = firebaseAuth.currentUser

        currentUser?.let {
            userName = it.displayName
            userId = it.uid
            userPhotoUrl = it.photoUrl.toString()
            println(tag + "updateUserInfo: usuario $userName, id $userId, foto $userPhotoUrl")
        } ?: run {
            println("No hay usuario autenticado.")
        }
    }

    private fun addUserWithDefaultMoney(userId: String) {
        val userRef = db.collection("totalMoney").document(userId)
        userRef.get().addOnSuccessListener { document ->
            if (!document.exists()) {
                val moneyData = hashMapOf(
                    "money" to 100
                )
                userRef.set(moneyData)
                    .addOnSuccessListener { Log.d(tag, "User added with default money") }
                    .addOnFailureListener { e -> Log.w(tag, "Error adding user", e) }
            }
        }.addOnFailureListener { e -> Log.w(tag, "Error getting user", e) }
    }

    fun getUserMoney(userId: String, callback: (Int?) -> Unit) {
        val userRef = db.collection("totalMoney").document(userId)
        userRef.get().addOnSuccessListener { document ->
            if (document.exists()) {
                val money = document.getLong("money")?.toInt()
                callback(money)
            } else {
                callback(null)
            }
        }.addOnFailureListener { e ->
            Log.w(tag, "Error getting user money", e)
            callback(null)
        }
    }

    fun initializeUserMoney() {
        val userId = firebaseAuth.currentUser?.uid ?: return

        getUserMoney(userId) { money ->
            if (money != null) {
                println(tag + "User has $money euros")
//                com.example.proyectopmdm.screens.money = money
            } else {
                println(tag + "User not found or error occurred")
            }
        }
    }

    fun setUserMoney(userId: String, amount: Int, callback: (Int?) -> Unit) {
        val userRef = db.collection("totalMoney").document(userId)
        userRef.get().addOnSuccessListener { document ->
            if (document.exists()) {
                val currentMoney = document.getLong("money")?.toInt()
                if (currentMoney != null) {
                    userRef.update("money", currentMoney + amount)
                        .addOnSuccessListener {
                            callback(currentMoney + amount)
                        }
                        .addOnFailureListener { e ->
                            Log.w(tag, "Error updating user money", e)
                            callback(null)
                        }
                } else {
                    callback(null)
                }
            } else {
                val moneyData = hashMapOf(
                    "money" to 100
                )
                userRef.set(moneyData)
                    .addOnSuccessListener { Log.d(tag, "User added with default money") }
                    .addOnFailureListener { e -> Log.w(tag, "Error adding user", e) }
                callback(null)
            }
        }.addOnFailureListener { e ->
            Log.w(tag, "Error getting user money", e)
            callback(null)
        }
    }

    fun lostMoney(userId: String, amount: Int, callback: (Int?) -> Unit) {
        val userRef = db.collection("totalMoney").document(userId)
        userRef.get().addOnSuccessListener { document ->
            if (document.exists()) {
                val currentMoney = document.getLong("money")?.toInt()
                if (currentMoney != null) {
                    userRef.update("money", currentMoney - amount)
                        .addOnSuccessListener {
                            callback(currentMoney - amount)
                        }
                        .addOnFailureListener { e ->
                            Log.w(tag, "Error updating user money", e)
                            callback(null)
                        }
                } else {
                    callback(null)
                }
            } else {
                val moneyData = hashMapOf(
                    "money" to 100
                )
                userRef.set(moneyData)
                    .addOnSuccessListener { Log.d(tag, "User added with default money") }
                    .addOnFailureListener { e -> Log.w(tag, "Error adding user", e) }
                callback(null)
            }
        }.addOnFailureListener { e ->
            Log.w(tag, "Error getting user money", e)
            callback(null)
        }
    }

    init {
        initializeUserMoney()
    }
}
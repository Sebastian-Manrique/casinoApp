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
                    updateUserInfoToken(tokenCredential)
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

    private fun updateUserInfoToken(tokenCredential: GoogleIdTokenCredential) {
        userName = tokenCredential.displayName
        userId = tokenCredential.id
        userPhotoUrl = tokenCredential.profilePictureUri.toString()
        println(tag + "usuario $userName, id $userId, foto $userPhotoUrl")
    }

    private fun addUserWithDefaultMoney(userId: String) {
        val userRef = db.collection("users").document(userId)
        userRef.get().addOnSuccessListener { document ->
            if (!document.exists()) {
                val user = hashMapOf(
                    "money" to 100
                )
                userRef.set(user)
                    .addOnSuccessListener { Log.d(tag, "User added with default money") }
                    .addOnFailureListener { e -> Log.w(tag, "Error adding user", e) }
            }
        }.addOnFailureListener { e -> Log.w(tag, "Error getting user", e) }
    }

    fun getUserMoney(userId: String, callback: (Int?) -> Unit) {
        val userRef = db.collection("users").document(userId)
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
            } else {
                println(tag + "User not found or error occurred")
            }
        }
    }

    init {
        initializeUserMoney()
    }
}
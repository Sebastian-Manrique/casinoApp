package com.example.proyectopmdm.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun Dice(navController: NavHostController) {
    var resultNumber by remember { mutableIntStateOf(0) }
    val list = remember { mutableStateListOf<Int>() }
    var isExpanded by remember { mutableStateOf(false) } // The box is open or not

    Column(
        modifier = Modifier
            .fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("amount to bet")

        var text2 by remember { mutableStateOf("") } // Holds the input text
        TextField(
            value = text2, // The current money value
            onValueChange = { newText -> text2 = newText }, // Update text when changed
            label = { Text("Enter the amount") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Text("Currently money $money")
        Button(onClick = {
            resultNumber = randomGenerator(list)
            if (resultNumber == 3) money += 5 else money -= 5
        }) {
            Text("Roll the dices! 🎲")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "The result is $resultNumber")

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color(0xff181a17))
                .clickable { isExpanded = !isExpanded } // Alterna la expansión al hacer clic
                .padding(16.dp)
        ) {
            Column {
                Text(
                    text = if (isExpanded) "Hide Results" else "Show Results",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                if (isExpanded) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {
                        items(list) { item ->
                            Text(
                                text = "Rolled: $item",
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

fun randomGenerator(list: SnapshotStateList<Int>): Int {
    val number = (1..6).random()
    list.add(number)
    return number
}

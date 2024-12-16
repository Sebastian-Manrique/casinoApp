package com.example.proyectopmdm.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun Dice(navController: NavHostController) {
    var resultNumber by remember { mutableIntStateOf(0) }
    val list = remember { mutableStateListOf<Int>() }
    var isExpanded by remember { mutableStateOf(false) } // The box is open or not
    var betNumber by remember { mutableIntStateOf(0) }
    var colorBet: Boolean? by remember { mutableStateOf(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(0xFF007c57)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current

        Text("Choose your lucky number \uD83C\uDF40", fontSize = 20.sp)

        Spacer(Modifier.height(10.dp))

        GamblingNumbersGrid(
            colorBet = colorBet,
            betNumber = betNumber,
            onBetNumberChange = { newBetNumber -> betNumber = newBetNumber },
            onColorBetChange = { newColorBet -> colorBet = newColorBet }
        )

        Spacer(Modifier.height(10.dp))

        Text("amount to bet", fontSize = 20.sp)

        Spacer(Modifier.height(10.dp))

        var moneyBet by remember { mutableStateOf("") } // Holds the input text
        val keyboardController = LocalSoftwareKeyboardController.current
        TextField(
            value = moneyBet, // The current money value
            onValueChange = { newText -> moneyBet = newText }, // Update text when changed
            label = { Text("Enter the amount") }, keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number, imeAction = ImeAction.Done
            ), keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() })
        )
        Spacer(Modifier.height(5.dp))

        Text("Currently money %.2f€".format(money), fontSize = 17.sp)

        Spacer(Modifier.height(5.dp))

        Button(
            onClick = {
                println("Cantidad apostada: $moneyBet, numero elegido: $betNumber")
                if (betNumber == 0) {
                    Toast.makeText(context, "You didn't select any number!", Toast.LENGTH_SHORT)
                        .show()
                } else if (moneyBet.isEmpty()) {
                    Toast.makeText(context, "You didn't bet any money!", Toast.LENGTH_SHORT).show()
                } else if (moneyBet.toDouble() > money) {
                    Toast.makeText(context, "Liar, you don't have that money!", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    resultNumber = randomGenerator(list)
                    betFun(betNumber, moneyBet, context, resultNumber)
                }
            },
            //No se puede simplificar el color.
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF379ca0))
        ) {
            Text("Roll the dices! 🎲")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "The result is $resultNumber", fontSize = 20.sp)

        Spacer(modifier = Modifier.height(16.dp))

        val gradientBrush = Brush.horizontalGradient(
            colors = listOf(Color.Black, Color(0xFF006b4b)),
            startX = 0.0f,
            endX = 1000.0f,
            tileMode = TileMode.Repeated
        )

        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(
                width = 2.dp, brush = gradientBrush, shape = CircleShape
            )
            .clickable { isExpanded = !isExpanded } // Alterna la expansión al hacer clic
            .padding(16.dp), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = if (isExpanded) "Hide Results" else "Show Results",
                    style = MaterialTheme.typography.labelMedium
                )
                if (isExpanded) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {
                        items(list) { item ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth() // Aseguramos que el Box ocupe todo el ancho disponible
                                    .padding(8.dp),
                                contentAlignment = Alignment.Center // Centrar el texto dentro del Box
                            ) {
                                Text(
                                    text = "Rolled: $item",
                                    // modifier = Modifier.align(Alignment.CenterHorizontally), // Centrar el texto
                                    textAlign = TextAlign.Center, // Alinear el texto dentro del Text
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }

                }
            }
        }
    }
}

fun betFun(
    betNumber: Int,
    moneyBet: String,
    context: Context,
    resultNumber: Int
) {
    println("betNumber == $betNumber, resultNumber == $resultNumber")
    if (resultNumber == betNumber) {
        Toast.makeText(context, "You just earn money!! 💲🤑💸", Toast.LENGTH_SHORT).show()
        money += moneyBet.toDouble() * 2
    } else {
        Toast.makeText(context, "You just lost money!! 😢", Toast.LENGTH_SHORT).show()
        money -= moneyBet.toDouble()
    }
}

// Vista previa que solo muestra la cuadrícula
@Preview(showBackground = true)
@Composable
fun Ver() {
    val navController = rememberNavController()
    Dice(navController)
}

@Composable
fun GamblingNumbersGrid(
    colorBet: Boolean?,
    betNumber: Int,
    onBetNumberChange: (Int) -> Unit,
    onColorBetChange: (Boolean) -> Unit
) {
    // Crear un estado mutable para almacenar los colores de fondo de cada celda
    val colors = remember {
        mutableStateListOf<Color>().apply {
            repeat(12) { add(if (it % 2 == 0) Color.Red else Color.Black) }
        }
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(6), // 6 columnas
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 2.dp, color = Color.Black)
    ) {
        items(12) { index -> // 12 elementos (2 columnas * 6 filas)
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .height(100.dp)
                    .size(100.dp) // Tamaño de cada celda
                    .background(colors[index]) // Usar el color específico para cada celda
                    .clickable {
                        handleCellClick(index, colors) { newBet ->
                            onBetNumberChange(newBet) // Notificar el cambio al componente padre
                        }
                    }, contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${index + 1}", color = Color.White, fontSize = 20.sp
                )
            }
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Black)
                .clickable {
                    println("betting on black!")
                    onColorBetChange(false)
                }, contentAlignment = Alignment.Center
        ) {
            Text("")
        }

        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Red)
                .clickable {
                    println("betting on red!")
                    onColorBetChange(true)
                }, contentAlignment = Alignment.Center
        ) {
            Text("")
        }

    }

}


// Función para manejar el clic de una celda
private fun handleCellClick(
    index: Int, colors: SnapshotStateList<Color>, updateBetNumber: (Int) -> Unit
) {
    colors[index] = if (colors[index] == Color.Red || colors[index] == Color.Black) {
        Color(0xFF9c9c9c) // Negro apagado
    } else {
        if (index % 2 == 0) Color.Red else Color.Black // Restaurar al color original
    }
    updateBetNumber(index + 1) // Actualizar el número seleccionado
    println("El número seleccionado es ${index + 1}")
}

fun randomGenerator(list: SnapshotStateList<Int>): Int {
    val number = (1..12).random()
    list.add(number)
    return number
}

@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diceroller.logics.Dice
import com.example.diceroller.ui.theme.DiceRollerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DiceRollerTheme {

                Scaffold(topBar = {
                    TopAppBar(title = {
                        Text(text = "Dice")
                    }, navigationIcon = {
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Localized description"
                            )
                        }
                    }, actions = {
                        var expandDropdownMenu by remember { mutableStateOf(false) }
                        IconButton(onClick = { expandDropdownMenu = true }) {
                            Icon(
                                imageVector = Icons.Filled.MoreVert,
                                contentDescription = "More vert"
                            )
                        }
                        DropdownMenu(expanded = expandDropdownMenu,
                            onDismissRequest = { expandDropdownMenu = false }) {
                            DropdownMenuItem(text = {
                                Text(text = "Item 1")
                            }, onClick = { /*TODO*/ }, trailingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.Favorite,
                                    contentDescription = "Favorite icon"
                                )
                            })
                        }

                    })
                }) { contentPadding ->
                    Surface(modifier = Modifier.padding(contentPadding)) {
                        Greeting()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    var diceNumber by remember { mutableStateOf(0) }
    val dice = Dice(6)

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        RenderDiceImage(diceNumber)

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        Row {
            ElevatedButton(onClick = {
                diceNumber = dice.roll()
            }) {
                Text(text = "Roll".uppercase())
            }
        }
    }
}

@Composable
private fun RenderDiceImage(diceNumber: Int) {
    val luckyNumber = 4

    when (diceNumber) {
        luckyNumber -> Image(
            painter = painterResource(id = R.drawable.dice_4),
            contentDescription = stringResource(
                R.string.dice_four
            )
        )

        1 -> Image(
            painter = painterResource(id = R.drawable.dice_1),
            contentDescription = stringResource(
                R.string.dice_one
            )
        )

        2 -> Image(
            painter = painterResource(id = R.drawable.dice_2),
            contentDescription = stringResource(R.string.dice_two)
        )

        3 -> Image(
            painter = painterResource(id = R.drawable.dice_3),
            contentDescription = stringResource(R.string.dice_three)
        )

        5 -> Image(
            painter = painterResource(id = R.drawable.dice_5),
            contentDescription = stringResource(R.string.dice_five)
        )

        else -> Image(
            painter = painterResource(id = R.drawable.dice_6),
            contentDescription = stringResource(R.string.dice_six)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DiceRollerTheme {
        Greeting()
    }
}
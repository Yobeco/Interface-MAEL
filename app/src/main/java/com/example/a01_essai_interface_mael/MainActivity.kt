package com.example.a01_essai_interface_mael

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a01_essai_interface_mael.ui.theme._01_Essai_Interface_MAELTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _01_Essai_Interface_MAELTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "MAEL",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier
            .width(400.dp)
            .height(400.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row{
            Text(text = "Drapeaux", modifier = modifier)
            Text(text = "$name Scan", modifier = modifier)
            Text(text = "v5.1", modifier = modifier)
            Text(text = "QR", modifier = modifier)
        }
        Row{
            Text(text = "Play / Pause", modifier = modifier)
            Text(text = "Stop", modifier = modifier)
        }

        Column{
            Text(text = "Escanear el codigo QR", modifier = modifier)

            Button(
                modifier = modifier,
                onClick = {
                    Log.d("onClick", "button clicked")
                },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Color.Red,
                    disabledContentColor = Color.Gray,
                    disabledContainerColor = Color.Black
                ),
                enabled = true,
                shape = RoundedCornerShape(10.dp),
            ) {
                androidx.compose.material3.Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Icon Play"
                )
                Text("Click me!")
            }
        }

        Column{
            Text(text = "Volver a escuchar", modifier = modifier)
            Text(text = "BOUTON flèches", modifier = modifier)
        }

        Column{
            Text(text = "EScuchar mas despacio", modifier = modifier)
            Text(text = "BOUTON tortue", modifier = modifier)
        }

        Column{
            Text(text = "...", modifier = modifier)
            Text(text = "...", modifier = modifier)
        }

        Row{
            Text(text = "Logo école", modifier = modifier)
            Text(text = "Auteur - licence", modifier = modifier)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    _01_Essai_Interface_MAELTheme {
        Greeting("Android")
    }
}
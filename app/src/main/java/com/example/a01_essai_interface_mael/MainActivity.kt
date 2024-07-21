package com.example.a01_essai_interface_mael

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a01_essai_interface_mael.ui.theme._01_Essai_Interface_MAELTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _01_Essai_Interface_MAELTheme {
                    ScreenContent(
                        name = "5.1",     // Version
                    )
                }
            }
        }
    }

///////////////////////////////////////////////////////////////
//    Un seul composant paramétrable pour tous les boutons
///////////////////////////////////////////////////////////////
@Composable
fun ImageButton(onClick: () -> Unit,
           hauteur: Int,
           largeur: Int,
           image: Int) {

    Box(
        modifier = Modifier
            .padding(10.dp)
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(20.dp)
            )
            .size(width = largeur.dp, height = hauteur.dp)
            .clickable { onClick() }
        ,
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "",
            modifier = Modifier.size(width = largeur.dp, height = hauteur.dp),
        )
    }

}
//////////////////////////////////////////////
//          Conteneur principale
/////////////////////////////////////////////
@Composable
fun ScreenContent(name: String) {

    Column(    // Colonne qui contient toute l'application
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .systemBarsPadding()    // Ajoute un espacement pour les barres du système
        ,
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column (){
            Row(     // Ligne d'entête avec le titre
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 0.dp, vertical = 1.dp)
                    .background(Color(0xFF1E90FE))
                    .padding(start = 3.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    modifier = Modifier.padding(3.dp),
                    painter = painterResource(id = R.drawable.langues_64px),
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                )

                Text(
                    text = "MAEL Scan",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineSmall
                )

                Row(
                    // Ligne pour unir la version et le petit code QR
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "V$name",
                        color = Color.White,
                        style = MaterialTheme.typography.labelSmall
                    )

                    Image(
                        modifier = Modifier.padding(3.dp),
                        painter = painterResource(id = R.drawable.qrcode_64px),
                        contentDescription = null,
                        contentScale = ContentScale.Inside,
                    )
                }
            }

            Text(     // Zone où s'affichera le contenu du code QR
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 3.dp)
                    .background(color = Color(0xFFE6E6E6))
                    .padding(7.dp)
                // .scrollable()   // Comment faire pour permettre de faire défiler le texte s'il dépasse ?
                ,
                text = "Écouter l'audio. ",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
                // fontFamily = FontFamily.Cursive,
                maxLines = 5,
                minLines = 1,
                overflow = TextOverflow.Ellipsis,   //  Ajoute 3... si ça déborde
                textAlign = TextAlign.Left
            )

        }



        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 3.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
           )
        {

            ImageButton(onClick = { Log.d("onClick", "Lire le mp3") },
                hauteur = 67,
                largeur = 67,
                image = R.drawable.play
            )

            ImageButton(onClick = { Log.d("onClick", "Arrêter le mp3") },
                hauteur = 67,
                largeur = 67,
                image = R.drawable.stop
            )

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)    // Espace au dessus du texte
            ,
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = "Escanear el codigo QR",
            )

        ImageButton(onClick = { Log.d("onClick", "Appeler le Scanner") },
                    hauteur = 130,
                    largeur = 130,
                    image = R.drawable.logo_mael_130px
        )

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)    // Espace au dessus du texte
            ,
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = "Volver a escuchar")

            ImageButton(onClick = { Log.d("onClick", "Lire à nouveau") },
                hauteur = 80,
                largeur = 174,
                image = R.drawable.bouton_repete_80px
            )

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)    // Espace au dessus du texte
            ,
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            Text(text = "Escuchar mas despacio")

            ImageButton(
                onClick = { Log.d("onClick", "Lire plus lentement") },
                hauteur = 80,
                largeur = 174,
                image = R.drawable.bouton_lent_80px
            )

        }

        Text(text = " ",
            modifier = Modifier.padding(25.dp)
            )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center

        ){
            Image(
                modifier = Modifier
                    .padding(3.dp)
                    .size(32.dp),
                painter = painterResource(id = R.drawable.logo_lvh_150px),
                contentDescription = null,
                contentScale = ContentScale.Inside,
            )

            Text(text = "Auteur : Yonnel Bécognée - Licence GNU GPL V2",
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    _01_Essai_Interface_MAELTheme {
        ScreenContent("5.1")
    }
}
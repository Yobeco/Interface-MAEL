package com.example.a01_essai_interface_mael

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
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

        // Variable "Mutable state" contenant le texte
        var text_qr by remember { mutableStateOf("Écouter l'audio. ".repeat(n=25)) }

        Column (){
            Row(     // Ligne d'entête avec le titre + contenu du code QR
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 0.dp, vertical = 0.dp)
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

            val scrollState = rememberScrollState()
            // Pour que la taille de la Box() de text_qr s'adapte au nombre de lignes jusqu'à 5
            // Au dela, il est possible de faire défiler le texte
            val maxLines = 5
            val lineHeight = 32.dp     // à régler en fonction de la taille de la police
            val maxHeight = lineHeight * maxLines

            Box(      // Mise du texte dans une Box() pour pouvoir le faire défiler quand il dépasse 5 lignes
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = maxHeight)      // Variable fixée par la tailel de chaque ligne * nb lignes
                    .verticalScroll(scrollState)
            ){
                Text(     // Zone où s'affichera le contenu du code QR
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 3.dp, vertical = 1.dp)
                        .background(color = Color(0xFFE6E6E6))
                        .padding(7.dp),
                    text = text_qr,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge,
                    // fontFamily = FontFamily.Cursive,
                    // maxLines = 5,
                    minLines = 1,
                    // overflow = TextOverflow.Ellipsis,   //  Ajoute 3 points ... si ça déborde
                    textAlign = TextAlign.Left
                )
            }


        }

        // https://developer.android.com/develop/ui/compose/animation/composables-modifiers?hl=fr#animatedvisibility
        // Déclaration d'une variable reflétant l'état de visibilité, initialement false (invisible)
        var visible by remember { mutableStateOf(false) }      // En utilisant "remember", l'état visible persiste entre les recompositions de "ScreenContent"
        val density = LocalDensity.current // Obtient la densité actuelle de l'écran pour créer des ressources graphiques adaptées à différentes densités pour garantir que les éléments de l'interface utilisateur apparaissent correctement sur tous les types d'écrans.

        // Composant pour animer la visibilité
        AnimatedVisibility(
            visible = visible,                          // Paramètre qui détermine si le contenu est visible
            enter = slideInVertically {                 // Définit l'animation d'entrée par glissement vertical
                with(density) { -40.dp.roundToPx() }    // Glisse depuis 40 dp au-dessus du haut de l'écran
            } + expandVertically(
                expandFrom = Alignment.Top              // Animation d'expansion verticale à partir du haut
            ) + fadeIn(                                 // Animation de fondu entrant avec une alpha initiale de 0.3
                initialAlpha = 0.3f
            ),
            exit = slideOutVertically() + shrinkVertically() + fadeOut()     // Animations de sortie combinée
        ){
            Row(      // Ligne des bouttons lecture / Pause et Stop
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 3.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            )
            {
                ImageButton(
                    onClick = { Log.d("onClick", "Lire le mp3") },
                    hauteur = 67,
                    largeur = 67,
                    image = R.drawable.play
                )
                ImageButton(
                    onClick = { Log.d("onClick", "Arrêter le mp3") },
                    hauteur = 67,
                    largeur = 67,
                    image = R.drawable.stop
                )
            }
        }



        Column(      // Texte + boutton scanner
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)    // Espace au dessus du texte
            ,
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = "Escanear el codigo QR",
            )

        ImageButton(onClick = { visible = !visible},
                    hauteur = 130,
                    largeur = 130,
                    image = R.drawable.logo_mael_130px
        )

        }

        Column(      // Texte + boutton écouter
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)    // Espace au dessus du texte
            ,
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = "Volver a escuchar")

            ImageButton(
                onClick = { text_qr = "Écouter à nouveau." },
                hauteur = 80,
                largeur = 174,
                image = R.drawable.bouton_repete_80px
            )

        }

        Column(      // Texte + boutton plus lent
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)    // Espace au dessus du texte
            ,
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            Text(text = "Escuchar mas despacio")

            ImageButton(
                onClick = { text_qr = "Écouter plus lentement." },
                hauteur = 80,
                largeur = 174,
                image = R.drawable.bouton_lent_80px
            )

        }

        Spacer(modifier = Modifier.height(50.dp))

        Row(      // Pied de page
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
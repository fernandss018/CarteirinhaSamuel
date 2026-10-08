package com.maysa.samuel.feature.home_aluno.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.maysa.samuel.app.navigation.Routes
import br.senai.carteirinha.samuel.R

// ============================================================
// CORES
// ============================================================

private val Fundo = Color(0xFF0D0D0D)
private val Roxo = Color(0xFF7B3FC6)
private val RoxoEscuro = Color(0xFF4D207F)
private val RoxoClaro = Color(0xFFA66BE8)
private val CardEscuro = Color(0xFF18151D)
private val CardEscuro2 = Color(0xFF211B29)
private val Branco = Color(0xFFFFFFFF)
private val Cinza = Color(0xFFBDBDBD)

// ============================================================
// HOME
// ============================================================

@Composable
fun HomeScreen(
    navController: NavController = rememberNavController(),
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Fundo)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 18.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // ====================================================
        // CABEÇALHO
        // ====================================================

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(
                    id = R.drawable.samuel_foto
                ),

                contentDescription = "Foto de Samuel",

                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape),

                contentScale = ContentScale.Crop
            )

            Spacer(
                modifier = Modifier.width(15.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = "Olá, Samuel! 👋",

                    color = Branco,

                    fontSize = 24.sp,

                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(3.dp)
                )

                Text(
                    text = "Estudante de Desenvolvimento de Sistemas",

                    color = RoxoClaro,

                    fontSize = 13.sp,

                    fontWeight = FontWeight.Medium
                )

                Spacer(
                    modifier = Modifier.height(2.dp)
                )

                Text(
                    text = "Tecnologia • Games • Projetos",

                    color = Cinza,

                    fontSize = 12.sp
                )
            }
        }

        Spacer(
            modifier = Modifier.height(28.dp)
        )

        // ====================================================
        // TÍTULO PRINCIPAIS
        // ====================================================

        SectionTitle(
            text = "PRINCIPAIS"
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        // ====================================================
        // CARTEIRINHA
        // ====================================================

        HomeButton(
            title = "Carteirinha Digital",
            subtitle = "Veja sua identificação acadêmica",
            backgroundColor = RoxoEscuro,

            onClick = {
                navController.navigate(
                    Routes.Carteirinha.route
                )
            }
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        // ====================================================
        // UNIDADES CURRICULARES
        // ====================================================

        HomeButton(
            title = "Unidades Curriculares",
            subtitle = "Consulte suas UCs pela API",
            backgroundColor = RoxoEscuro,

            onClick = {
                navController.navigate(
                    Routes.UCAluno.route
                )
            }
        )

        Spacer(
            modifier = Modifier.height(28.dp)
        )

        // ====================================================
        // MEU ESPAÇO
        // ====================================================

        SectionTitle(
            text = "MEU ESPAÇO"
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        // ====================================================
        // GAMES
        // ====================================================

        PersonalCard(
            title = "🎮 Games",
            description = "PlayStation • Free Fire • EA FC • Clash Royale"
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        // ====================================================
        // PROJETOS
        // ====================================================

        PersonalCard(
            title = "💻 Meus Projetos",
            description = "Android • Sites • APIs • Desenvolvimento"
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        // ====================================================
        // SOBRE MIM
        // ====================================================

        PersonalCard(
            title = "👤 Sobre Mim",
            description = "Tecnologia • Criatividade • Games • Projetos"
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        // ====================================================
        // FRASE FINAL
        // ====================================================

        Card(
            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(18.dp),

            colors = CardDefaults.cardColors(
                containerColor = CardEscuro2
            )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),

                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "⚡",

                    fontSize = 28.sp
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Text(
                    text = "Transformando ideias em projetos.",

                    color = Branco,

                    fontSize = 15.sp,

                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = "Samuel Fernandes",

                    color = RoxoClaro,

                    fontSize = 12.sp
                )
            }
        }

        Spacer(
            modifier = Modifier.height(10.dp)
        )
    }
}

// ============================================================
// TÍTULO DE SEÇÃO
// ============================================================

@Composable
private fun SectionTitle(
    text: String
) {

    Text(
        text = text,

        modifier = Modifier.fillMaxWidth(),

        color = RoxoClaro,

        fontSize = 13.sp,

        fontWeight = FontWeight.Bold
    )
}

// ============================================================
// BOTÃO PRINCIPAL
// ============================================================

@Composable
private fun HomeButton(
    title: String,
    subtitle: String,
    backgroundColor: Color,
    onClick: () -> Unit
) {

    Button(

        onClick = onClick,

        modifier = Modifier
            .fillMaxWidth()
            .height(78.dp),

        shape = RoundedCornerShape(18.dp),

        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = Branco
        )
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),

            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = title,

                fontSize = 17.sp,

                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(3.dp)
            )

            Text(
                text = subtitle,

                fontSize = 11.sp,

                color = Color.White.copy(
                    alpha = 0.75f
                )
            )
        }
    }
}

// ============================================================
// CARD PESSOAL
// ============================================================

@Composable
private fun PersonalCard(
    title: String,
    description: String
) {

    Card(

        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(18.dp),

        colors = CardDefaults.cardColors(
            containerColor = CardEscuro
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(

                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(RoxoEscuro),

                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = when {
                        title.contains("Games") -> "🎮"
                        title.contains("Projetos") -> "💻"
                        else -> "👤"
                    },

                    fontSize = 22.sp
                )
            }

            Spacer(
                modifier = Modifier.width(15.dp)
            )

            Column {

                Text(
                    text = title,

                    color = Branco,

                    fontSize = 16.sp,

                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = description,

                    color = Cinza,

                    fontSize = 12.sp
                )
            }
        }
    }
}

// ============================================================
// PREVIEW
// ============================================================

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun HomeScreenPreview() {

    HomeScreen(
        navController = rememberNavController(),

        modifier = Modifier
            .fillMaxSize()
    )
}
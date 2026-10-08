package com.maysa.samuel.feature.carteirinha.presetantion.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import br.senai.carteirinha.samuel.R
import com.maysa.samuel.feature.carteirinha.presetantion.component.PerfilAluno
import com.rafaelcosta.myapplication.QrCode

@Composable
fun CarteirinhaScreen(
    modifier: Modifier = Modifier
) {
    Box {
        Image(
            painter = painterResource(id = R.drawable.fundo),
            contentDescription = "Fundo",
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.5f),
            contentScale = ContentScale.Crop
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
            modifier = modifier.fillMaxSize()

        ) {
            Image(
                painter = painterResource(id = R.drawable.senai),
                contentDescription = "Logo Senai",
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            )
            PerfilAluno(
                nome = "Samuel Fernandes ",
                curso = "Desenvolvimento de Sistemas"
            )
            QrCode(
                conteudo = "90000000001755369450"
            )
        }

    }

}
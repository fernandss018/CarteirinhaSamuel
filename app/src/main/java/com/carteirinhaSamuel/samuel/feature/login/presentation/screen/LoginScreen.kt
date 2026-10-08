package com.maysa.samuel.feature.login.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.maysa.samuel.feature.login.domain.model.UsuarioLogado
import com.maysa.samuel.feature.login.presentation.LoginEvent
import com.maysa.samuel.feature.login.presentation.LoginViewModel

// ============================================================
// CORES
// ============================================================

private val Fundo = Color(0xFF0A0A0A)
private val FundoCard = Color(0xFF151218)
private val Roxo = Color(0xFF8A45D1)
private val RoxoEscuro = Color(0xFF4D207F)
private val RoxoClaro = Color(0xFFB77BEE)
private val Branco = Color(0xFFFFFFFF)
private val Cinza = Color(0xFFAAAAAA)

// ============================================================
// LOGIN
// ============================================================

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    viewModel: LoginViewModel = viewModel(),
    onLoginSucesso: (UsuarioLogado) -> Unit = {}
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var senhaVisivel by remember {
        mutableStateOf(false)
    }

    // ========================================================
    // NAVEGAÇÃO APÓS LOGIN
    // ========================================================

    LaunchedEffect(uiState.usuarioLogado) {

        uiState.usuarioLogado?.let { usuario ->

            viewModel.onEvent(
                LoginEvent.OnNavegacaoRealizada
            )

            onLoginSucesso(usuario)
        }
    }

    // ========================================================
    // TELA
    // ========================================================

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF08080A),
                        Color(0xFF120B19),
                        Color(0xFF08080A)
                    )
                )
            )
            .padding(
                horizontal = 24.dp,
                vertical = 20.dp
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize(),

            horizontalAlignment = Alignment.CenterHorizontally,

            verticalArrangement = Arrangement.Center
        ) {

            // =================================================
            // LOGO
            // =================================================

            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Roxo,
                                RoxoEscuro
                            )
                        ),
                        shape = CircleShape
                    ),

                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "S",

                    color = Branco,

                    fontSize = 52.sp,

                    fontWeight = FontWeight.ExtraBold
                )
            }

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            // =================================================
            // TÍTULO
            // =================================================

            Text(
                text = "Carteirinha Digital",

                color = Branco,

                fontSize = 28.sp,

                fontWeight = FontWeight.Bold,

                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(5.dp)
            )

            Text(
                text = "Samuel Fernandes",

                color = RoxoClaro,

                fontSize = 15.sp,

                fontWeight = FontWeight.Medium
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "Entre para acessar seu perfil",

                color = Cinza,

                fontSize = 13.sp,

                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(32.dp)
            )

            // =================================================
            // CAMPO USUÁRIO
            // =================================================

            OutlinedTextField(

                value = uiState.usuario,

                onValueChange = { value ->

                    viewModel.onEvent(
                        LoginEvent.OnUsuarioChange(value)
                    )
                },

                modifier = Modifier.fillMaxWidth(),

                label = {
                    Text(
                        text = "Email"
                    )
                },

                leadingIcon = {

                    Icon(
                        imageVector = Icons.Default.Person,

                        contentDescription = "Usuário"
                    )
                },

                singleLine = true,

                isError = uiState.erroMessage != null,

                shape = RoundedCornerShape(16.dp),

                colors = OutlinedTextFieldDefaults.colors(

                    focusedBorderColor = Roxo,

                    unfocusedBorderColor = Color(0xFF454047),

                    focusedLabelColor = RoxoClaro,

                    unfocusedLabelColor = Cinza,

                    focusedTextColor = Branco,

                    unfocusedTextColor = Branco,

                    cursorColor = RoxoClaro,

                    focusedLeadingIconColor = RoxoClaro,

                    unfocusedLeadingIconColor = Cinza
                )
            )

            Spacer(
                modifier = Modifier.height(14.dp)
            )

            // =================================================
            // CAMPO SENHA
            // =================================================

            OutlinedTextField(

                value = uiState.senha,

                onValueChange = { value ->

                    viewModel.onEvent(
                        LoginEvent.OnSenhaChange(value)
                    )
                },

                modifier = Modifier.fillMaxWidth(),

                label = {
                    Text(
                        text = "Senha"
                    )
                },

                leadingIcon = {

                    Icon(
                        imageVector = Icons.Default.Lock,

                        contentDescription = "Senha"
                    )
                },

                trailingIcon = {

                    IconButton(
                        onClick = {
                            senhaVisivel = !senhaVisivel
                        }
                    ) {

                        Icon(

                            imageVector =
                                if (senhaVisivel)
                                    Icons.Default.VisibilityOff
                                else
                                    Icons.Default.Visibility,

                            contentDescription =
                                if (senhaVisivel)
                                    "Ocultar senha"
                                else
                                    "Mostrar senha"
                        )
                    }
                },

                visualTransformation =
                    if (senhaVisivel)
                        VisualTransformation.None
                    else
                        PasswordVisualTransformation(),

                singleLine = true,

                isError = uiState.erroMessage != null,

                shape = RoundedCornerShape(16.dp),

                colors = OutlinedTextFieldDefaults.colors(

                    focusedBorderColor = Roxo,

                    unfocusedBorderColor = Color(0xFF454047),

                    focusedLabelColor = RoxoClaro,

                    unfocusedLabelColor = Cinza,

                    focusedTextColor = Branco,

                    unfocusedTextColor = Branco,

                    cursorColor = RoxoClaro,

                    focusedLeadingIconColor = RoxoClaro,

                    unfocusedLeadingIconColor = Cinza,

                    focusedTrailingIconColor = RoxoClaro,

                    unfocusedTrailingIconColor = Cinza
                )
            )

            // =================================================
            // ERRO
            // =================================================

            uiState.erroMessage?.let { error ->

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Text(

                    text = error,

                    color = MaterialTheme.colorScheme.error,

                    fontSize = 13.sp,

                    textAlign = TextAlign.Center,

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                )
            }

            Spacer(
                modifier = Modifier.height(22.dp)
            )

            // =================================================
            // BOTÃO ENTRAR
            // =================================================

            Button(

                onClick = {

                    viewModel.onEvent(
                        LoginEvent.OnEntrarClick
                    )
                },

                enabled = !uiState.isLoading,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),

                shape = RoundedCornerShape(16.dp),

                colors = ButtonDefaults.buttonColors(

                    containerColor = Roxo,

                    contentColor = Branco,

                    disabledContainerColor = RoxoEscuro,

                    disabledContentColor = Branco.copy(
                        alpha = 0.7f
                    )
                )
            ) {

                if (uiState.isLoading) {

                    CircularProgressIndicator(

                        modifier = Modifier.size(24.dp),

                        color = Branco,

                        strokeWidth = 3.dp
                    )

                } else {

                    Text(

                        text = "ENTRAR",

                        fontSize = 15.sp,

                        fontWeight = FontWeight.Bold,

                        letterSpacing = 1.sp
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            // =================================================
            // INFORMAÇÃO DA API
            // =================================================

            Text(

                text = "Sistema integrado à API",

                color = Color(0xFF777777),

                fontSize = 11.sp,

                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(5.dp)
            )

            Text(

                text = "SENAI • Desenvolvimento de Sistemas",

                color = Color(0xFF555555),

                fontSize = 10.sp,

                textAlign = TextAlign.Center
            )
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
fun LoginScreenPreview() {

    LoginScreen(
        navController = rememberNavController()
    )
}
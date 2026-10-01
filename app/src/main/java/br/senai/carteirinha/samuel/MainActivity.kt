package br.senai.carteirinha.samuel

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.carteirinha.samuel.R
import kotlin.OptIn
import kotlinx.coroutines.launch

private val Blue = Color(0xFF0B3D7A)
private val LightBlue = Color(0xFFEAF2FB)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { CarteirinhaApp() }
    }
}

@Composable
fun CarteirinhaApp() {
    val nav = rememberNavController()
    MaterialTheme {
        NavHost(navController = nav, startDestination = "login") {
            composable("login") { LoginScreen(nav) }
            composable("home") { HomeScreen(nav) }
            composable("carteirinha") { CarteirinhaScreen(nav) }
            composable("ucs") { UcsScreen(nav) }
        }
    }
}

@Composable
fun LoginScreen(nav: NavHostController) {
    var login by remember { mutableStateOf("samuel") }
    var senha by remember { mutableStateOf("samuka123") }
    var erro by remember { mutableStateOf("") }
    var carregando by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize().padding(28.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(Icons.Default.Badge, null, tint = Blue, modifier = Modifier.size(72.dp))
            Spacer(Modifier.height(16.dp))
            Text("Carteirinha Digital", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Text("Samuel Fernandes", color = Blue, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(32.dp))
            OutlinedTextField(login, { login = it; erro = "" }, label = { Text("Login") }, leadingIcon = { Icon(Icons.Default.Person, null) }, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(12.dp))
            OutlinedTextField(senha, { senha = it; erro = "" }, label = { Text("Senha") }, leadingIcon = { Icon(Icons.Default.Lock, null) }, visualTransformation = PasswordVisualTransformation(), modifier = Modifier.fillMaxWidth())
            if (erro.isNotBlank()) {
                Spacer(Modifier.height(10.dp)); Text(erro, color = MaterialTheme.colorScheme.error)
            }
            Spacer(Modifier.height(20.dp))
            Button(
                enabled = !carregando,
                onClick = {
                    scope.launch {
                        carregando = true
                        erro = ""
                        try {
                            Log.d("LOGIN_FLOW", "Iniciando login para $login")
                            val result = ApiClient.service.login(LoginRequest(login, senha))
                            Session.user = result
                            Session.token = result.token
                            Log.d("LOGIN_FLOW", "Login OK: ${result.nome} / HTTP 200")
                            nav.navigate("home") { popUpTo("login") { inclusive = true } }
                        } catch (e: Exception) {
                            Log.e("LOGIN_FLOW", "Falha no login", e)
                            erro = "Não foi possível fazer login. Verifique a API e os dados."
                        } finally { carregando = false }
                    }
                }, modifier = Modifier.fillMaxWidth().height(52.dp)
            ) { Text(if (carregando) "Entrando..." else "Entrar") }
            Spacer(Modifier.height(16.dp))
            Text("API: http://10.0.2.2:8080", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(title: String, nav: NavHostController) {
    TopAppBar(title = { Text(title) }, navigationIcon = {
        IconButton(onClick = { nav.navigate("home") { popUpTo("home") { inclusive = false } } }) { Icon(Icons.Default.ArrowBack, "Voltar") }
    })
}

@Composable
fun HomeScreen(nav: NavHostController) {
    val user = Session.user
    Scaffold(topBar = { TopAppBarSimple("Home") }) { pad ->
        Column(modifier = Modifier.fillMaxSize().padding(pad).padding(20.dp)) {
            Text("Olá, ${user?.nome ?: "Samuel"}!", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Text("Confira seus dados acadêmicos.", color = Color.Gray)
            Spacer(Modifier.height(24.dp))
            HomeCard("Carteirinha Digital", "Veja sua identificação acadêmica", Icons.Default.Badge) { nav.navigate("carteirinha") }
            Spacer(Modifier.height(14.dp))
            HomeCard("Lista de UCs", "Consultar unidades curriculares pela API", Icons.Default.Book) { nav.navigate("ucs") }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarSimple(title: String) {
    TopAppBar(title = { Text(title) })
}

@Composable
fun HomeCard(title: String, subtitle: String, icon: androidx.compose.ui.graphics.vector.ImageVector, onClick: () -> Unit) {
    Card(onClick = onClick, modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = LightBlue)) {
        Row(modifier = Modifier.padding(20.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, null, tint = Blue, modifier = Modifier.size(42.dp))
            Spacer(Modifier.width(16.dp))
            Column { Text(title, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium); Text(subtitle, color = Color.Gray) }
        }
    }
}

@Composable
fun CarteirinhaScreen(nav: NavHostController) {
    val user = Session.user
    Scaffold(topBar = { AppTopBar("Carteirinha Digital", nav) }) { pad ->
        Column(modifier = Modifier.fillMaxSize().padding(pad).padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(22.dp), colors = CardDefaults.cardColors(containerColor = Blue)) {
                Column(modifier = Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painterResource(br.senai.carteirinha.samuel.R.drawable.samuel_foto), "Foto de Samuel", modifier = Modifier.size(120.dp).clip(RoundedCornerShape(60.dp)), contentScale = ContentScale.Crop)
                    Spacer(Modifier.height(14.dp))
                    Text("SENAI", color = Color.White, fontWeight = FontWeight.Bold)
                    Text(user?.nome ?: "Samuel Fernandes", color = Color.White, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(14.dp))
                    InfoLine("Matrícula", user?.matricula ?: "NÃO INFORMADA")
                    InfoLine("Curso", user?.curso ?: "Desenvolvimento de Sistemas")
                    InfoLine("Turma", user?.turma ?: "2DEVEST-A")
                }
            }
        }
    }
}

@Composable
fun InfoLine(label: String, value: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp)) {
        Text(label.uppercase(), color = Color.White.copy(alpha = .7f), style = MaterialTheme.typography.labelSmall)
        Text(value, color = Color.White, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun UcsScreen(nav: NavHostController) {
    var ucs by remember { mutableStateOf<List<UnidadeCurricular>>(emptyList()) }
    var erro by remember { mutableStateOf("") }
    var carregando by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        try {
            Log.d("UC_FLOW", "Buscando UCs: GET /unidades-curriculares")
            ucs = ApiClient.service.listarUcs()
            Log.d("UC_FLOW", "UCs recebidas: ${ucs.size} - HTTP 200")
        } catch (e: Exception) {
            Log.e("UC_FLOW", "Falha ao buscar UCs", e)
            erro = "Não foi possível carregar as UCs. Verifique se a API está ativa."
        } finally { carregando = false }
    }

    Scaffold(topBar = { AppTopBar("Lista de UCs", nav) }) { pad ->
        if (carregando) {
            Box(Modifier.fillMaxSize().padding(pad), contentAlignment = Alignment.Center) { Text("Carregando UCs...") }
        } else if (erro.isNotBlank()) {
            Box(Modifier.fillMaxSize().padding(pad).padding(20.dp), contentAlignment = Alignment.Center) { Text(erro) }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(pad).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(ucs) { uc ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(Modifier.padding(16.dp)) {
                            Text(uc.nome, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                            Text(uc.professor, color = Blue)
                            Spacer(Modifier.height(8.dp))
                            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text("Nota 1: ${"%.1f".format(uc.nota1)}")
                                Text("Nota 2: ${"%.1f".format(uc.nota2)}")
                                Text("Média: ${"%.1f".format(uc.media)}", fontWeight = FontWeight.Bold)
                            }
                            Text("Faltas: ${uc.faltas}", color = Color.Gray)
                        }
                    }
                }
            }
        }
    }
}

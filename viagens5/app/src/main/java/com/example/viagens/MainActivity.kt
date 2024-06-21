package com.example.viagens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.text.SimpleDateFormat
import java.util.*

data class Viagem(val nome: String, val destino: String, val valor: Double, val data: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViagensApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViagensApp() {
    val darkTheme = isSystemInDarkTheme()

    val lightColors = lightColorScheme(
        primary = Color(0xFF6200EA),
        onPrimary = Color.White,
        secondary = Color(0xFF03DAC5),
        onSecondary = Color.Black,
        background = Color(0xFFF1F1F1),
        onBackground = Color.Black,
        surface = Color.White,
        onSurface = Color.Black,
    )

    val darkColors = darkColorScheme(
        primary = Color(0xFFBB86FC),
        onPrimary = Color.Black,
        secondary = Color(0xFF03DAC5),
        onSecondary = Color.Black,
        background = Color(0xFF121212),
        onBackground = Color.White,
        surface = Color(0xFF1E1E1E),
        onSurface = Color.White,
    )

    val typography = Typography(
        bodyLarge = TextStyle(
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ),
        titleMedium = TextStyle(
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp
        ),
        headlineMedium = TextStyle(
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
    )

    MaterialTheme(
        colorScheme = if (darkTheme) darkColors else lightColors,
        typography = typography
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.logo_viagens),
                                contentDescription = "Logo",
                                modifier = Modifier.size(150.dp)
                            )
                        }
                    }
                )
            },
            content = { paddingValues ->
                MainScreen(Modifier.padding(paddingValues))
            }
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier, viewModel: ViagensViewModel = viewModel()) {
    val viagens by viewModel.viagens.collectAsState()
    val totalHoje by viewModel.totalHoje.collectAsState()
    val totalMes by viewModel.totalMes.collectAsState()

    var nome by remember { mutableStateOf("") }
    var destino by remember { mutableStateOf("") }
    var valor by remember { mutableStateOf("") }
    var showMessage by remember { mutableStateOf(false) }

    LaunchedEffect(showMessage) {
        if (showMessage) {
            delay(2000)  // Mostra a mensagem por 2 segundos
            showMessage = false
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            AnimatedVisibility(
                visible = showMessage,
                enter = fadeIn(animationSpec = tween(300)),
                exit = fadeOut(animationSpec = tween(300))
            ) {
                Text(
                    "Viagens adicionadas",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Green
                    ),
                    modifier = Modifier.padding(8.dp)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFF6200EA),
                                Color(0xFF1D3557)
                            )
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(17.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    InfoCard(title = "Hoje", value = totalHoje, modifier = Modifier.weight(1f))

                    Divider(
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(1.dp)
                            .padding(vertical = 8.dp)
                    )

                    InfoCard(title = "Este Mês", value = totalMes, modifier = Modifier.weight(1f))
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            Column {
                OutlinedTextField(
                    value = nome,
                    onValueChange = { nome = it },
                    label = { Text("Nome do Passageiro") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = destino,
                    onValueChange = { destino = it },
                    label = { Text("Destino") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = valor,
                    onValueChange = { valor = it },
                    label = { Text("Valor da Viagem") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        if (nome.isNotEmpty() && destino.isNotEmpty() && valor.isNotEmpty()) {
                            viewModel.adicionarViagem(nome, destino, valor.toDouble())
                            nome = ""
                            destino = ""
                            valor = ""
                            showMessage = true  // Mostra a mensagem quando uma viagem é adicionada
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Adicionar Viagem")
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        items(viagens) { viagem ->
            ViagemItem(viagem)
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ViagemItem(viagem: Viagem) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        visible = true
    }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(initialAlpha = 0.3f, animationSpec = tween(durationMillis = 300)),
        content = {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(viagem.nome, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                        Text(viagem.destino, fontSize = 18.sp, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onSurface)
                        Text(viagem.data, fontSize = 16.sp, fontWeight = FontWeight.Light, color = MaterialTheme.colorScheme.onSurface)
                    }
                    Text("R$ ${viagem.valor}", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                }
            }
        }
    )
}

@Composable
fun InfoCard(title: String, value: Double, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(0.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Box(
            modifier = Modifier
                .background(Color.Transparent)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    String.format("R$ %.2f", value),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White
                )
            }
        }
    }
}

class ViagensViewModel : ViewModel() {
    private val _viagens = MutableStateFlow<List<Viagem>>(emptyList())
    val viagens: StateFlow<List<Viagem>> get() = _viagens

    private val _totalHoje = MutableStateFlow(0.0)
    val totalHoje: StateFlow<Double> get() = _totalHoje

    private val _totalMes = MutableStateFlow(0.0)
    val totalMes: StateFlow<Double> get() = _totalMes

    fun adicionarViagem(nome: String, destino: String, valor: Double) {
        val novaViagem = Viagem(nome, destino, valor, obterDataAtual())
        val listaAtualizada = _viagens.value + novaViagem
        _viagens.value = listaAtualizada
        atualizarTotais(listaAtualizada)
    }

    private fun obterDataAtual(): String {
        val formatoData = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return formatoData.format(Date())
    }

    private fun atualizarTotais(viagens: List<Viagem>) {
        val dataAtual = obterDataAtual()
        val totalHoje = viagens.filter { it.data == dataAtual }.sumOf { it.valor }
        _totalHoje.value = totalHoje

        val mesAtual = SimpleDateFormat("MM/yyyy", Locale.getDefault()).format(Date())
        val totalMes = viagens.filter {
            SimpleDateFormat("MM/yyyy", Locale.getDefault()).format(SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse(it.data)) == mesAtual
        }.sumOf { it.valor }
        _totalMes.value = totalMes
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ViagensApp()
}

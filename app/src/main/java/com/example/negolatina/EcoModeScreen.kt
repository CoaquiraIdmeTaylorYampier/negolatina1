package com.example.negolatina

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateDpAsState

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EcoModeScreen(navController: NavController, ecoViewModel: EcoViewModel = viewModel()) {
    val currentPoints by ecoViewModel.currentPoints
    val currentLevel by ecoViewModel.currentLevel
    val treeProgress by ecoViewModel.treeProgress
    val pointsForNextLevel = ecoViewModel.pointsForNextLevel

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Modo Ecológico") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                },
                actions = {
                    IconButton(onClick = { /* Handle menu */ }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menú")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFDFFFE0)
                )
            )
        },
        containerColor = Color(0xFFDFFFE0),
        floatingActionButton = {
            FloatingActionButton(onClick = { ecoViewModel.addPoints(10) }) {
                Icon(
                    painter = painterResource(id = R.drawable.regadera),
                    contentDescription = "Añadir Puntos",
                    modifier = Modifier.size(36.dp),
                    tint = Color.Unspecified // Evita que el FAB tiña la imagen
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfileHeader()
            Spacer(modifier = Modifier.height(16.dp))
            TreeSection(treeProgress, currentLevel)
            Spacer(modifier = Modifier.height(16.dp))
            ProgressSection(currentPoints, pointsForNextLevel)
            Spacer(modifier = Modifier.height(16.dp))
            RecommendationsSection()
            Spacer(modifier = Modifier.height(16.dp))
            DetailsSection()
        }
    }
}

@Composable
fun ProfileHeader() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0x80C8E6C9))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text("Taylor Smith Watson", fontWeight = FontWeight.Bold)
                Text("Smith23@gmail.com")
                Text("+51 900691234")
            }
        }
    }
}
@Composable
fun TreeSection(progress: Float, currentLevel: Int) {
    val minSize = 30.dp // Tamaño base para la animación
    val maxSize = 300.dp
    val animatedSize by animateDpAsState(targetValue = minSize + (maxSize - minSize) * progress, label = "")

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("¡Tu árbol te necesita!", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("BIKTELIO", fontSize = 22.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 4.dp))
        Text("Nivel: $currentLevel", fontSize = 18.sp)

        Box(
            modifier = Modifier
                .height(maxSize + 58.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.suelo),
                contentDescription = "Suelo de pasto",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                contentScale = ContentScale.FillWidth
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 35.dp)
            ) {
                Crossfade(targetState = currentLevel, label = "tree-evolution") { level ->
                    val imageRes = when {
                        level in 1..2 -> R.drawable.semilla
                        level in 3..5 -> R.drawable.germinacion
                        level in 6..8 -> R.drawable.brote
                        level in 9..10 -> R.drawable.arbol_joven
                        level in 11..12 -> R.drawable.arbol_crecimiento
                        level in 13..14 -> R.drawable.arbol_maduro
                        else -> R.drawable.arbol_ecologico // Nivel 15
                    }

                    val isTreeStage = level >= 9

                    val imageModifier = when {
                        level in 1..2 -> Modifier
                            .size(animatedSize / 2)
                            .offset(y = 10.dp) // Semilla enterrada
                        isTreeStage -> Modifier
                            .size(animatedSize)
                            .clip(CircleShape)
                        else -> Modifier.size(animatedSize)
                    }

                    val contentScale = if (isTreeStage) ContentScale.Crop else ContentScale.Fit

                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = "Planta en crecimiento",
                        modifier = imageModifier,
                        contentScale = contentScale,
                        alignment = Alignment.BottomCenter
                    )
                }
            }
        }
    }
}


@Composable
fun ProgressSection(currentPoints: Int, pointsForNextLevel: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinearProgressIndicator(
            progress = (currentPoints.toFloat() / pointsForNextLevel.toFloat()),
            modifier = Modifier.fillMaxWidth()
        )
        Text("Puntos verdes: $currentPoints/$pointsForNextLevel")
    }
}

@Composable
fun RecommendationsSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1B5E20))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            RecommendationItem("Mantente Hidratado")
            RecommendationItem("Separa la basura")
            RecommendationItem("Apoya a agricultores locales")
        }
    }
}

@Composable
fun RecommendationItem(text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text, color = Color.White)
    }
}

@Composable
fun DetailsSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Detalles", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Descubre a tu nuevo mejor amigo en las compras: ¡la Mascota Interactiva que crece contigo! Cada compra que realizas te acerca más a tu mascota. Gana puntos con tus transacciones y conviértelos en recompensas exclusivas para tu compañera.")
        }
    }
}

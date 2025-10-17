package com.example.negolatina

import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove // ⭐️ CORRECCIÓN: Referencia 'Remove'
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.negolatina.ui.theme.NegolatinaTheme
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.layout.ContentScale


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NegolatinaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NegolatinaTheme {
        Greeting("Android")
    }
}


@OptIn(ExperimentalMaterial3Api::class)
//@Preview
@Composable
fun productomanzana() {
    var count by remember { mutableStateOf(0) }

    NegolatinaTheme {
        Scaffold(
            topBar = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                        .background(Color.Red)
                ) {}
            },
            bottomBar = {
                BottomAppBar(
                    modifier = Modifier.height(70.dp),
                    containerColor = Color(0xFFF0F0F0)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(start = 16.dp)
                        ) {
                            Text("Cantidad", modifier = Modifier.padding(end = 16.dp))

                            IconButton(
                                onClick = { if (count > 0) count-- },
                                modifier = Modifier.size(36.dp)
                            ) {
                                Icon(Icons.Default.Remove, contentDescription = "Restar")
                            }

                            Text(count.toString(), modifier = Modifier.padding(horizontal = 8.dp))

                            IconButton(
                                onClick = { count++ },
                                modifier = Modifier.size(36.dp)
                            ) {
                                Icon(Icons.Default.Add, contentDescription = "Sumar")
                            }
                        }

                        Button(
                            onClick = { },
                            modifier = Modifier
                                .height(56.dp)
                                .weight(1f)
                                .padding(end = 8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Red
                            )
                        ) {
                            Text("Añadir al carrito")
                        }
                    }
                }
            }
        ) { paddingValue ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValue)
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .background(Color(0xFFF2DCD8)),
                        contentAlignment = Alignment.Center
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.manzana),
                            contentDescription = "Ima de Manzana",
                            modifier = Modifier.height(300.dp)
                        )
                    }
                    Box(Modifier.fillMaxWidth().weight(1f).background(Color(0xFFF2DCD8))) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(350.dp)
                            ,colors = CardDefaults.cardColors(
                                containerColor = Color.White),
                            shape = RoundedCornerShape(32.dp)
                        ) {
                            Box(Modifier.padding(horizontal = 130.dp, vertical = 15.dp)){
                                Text("MANZANA",
                                    style = TextStyle(
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }
                            Box(Modifier.padding(horizontal = 25.dp, vertical = 5.dp)){
                                Text("Fruto del manzano, de forma globosa algo\n" +
                                        "hundida   por   los  extremos  del   eje, de\n" +
                                        "epicarpio  delgado,  liso y  de  color  verde\n" +
                                        "claro  ,   amarillo   pálido   o    encarnado,\n" +
                                        "mesocarpio    con     sabor    acíduLo     o\n" +
                                        "ligeramente      azucarado,    y   semillas\n" +
                                        "pequeñas, de color   de caoba, encerradas en\n" +
                                        "un endocarpio coriáceo.\n"+
                                        " \n"+
                                        "  VALORACION                           ⭐\uFE0F ⭐\uFE0F ⭐\uFE0F ⭐\uFE0F")
                            }
                        }
                    }
                }
                Surface(
                    onClick = {},
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 5.dp, top = 0.dp)
                        .height(40.dp)
                        .width(40.dp)
                        .clip(CircleShape),
                    color = Color(0xFFF2DCD8)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text("⬅️")
                    }
                }
            }
        }
    }
}
@Preview
@Composable
fun bebidas() {

    NegolatinaTheme {
        Scaffold(
            topBar = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.encabezado),
                        contentDescription = "Encabezado",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            Modifier.weight(1f),
                            contentAlignment = Alignment.CenterStart
                        ){
                            Image(
                                painter = painterResource(id = R.drawable.menu),
                                contentDescription = "menu",
                                modifier = Modifier
                                    .height(20.dp).width(20.dp),
                            )
                        }
                        Box(
                            Modifier, contentAlignment = Alignment.Center
                        ){
                            Text(
                                "Bebidas",
                                style = TextStyle(color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                            )}
                        Box(
                            Modifier.weight(1f),
                            contentAlignment = Alignment.CenterEnd
                        ){
                            Image(
                                painter = painterResource(id = R.drawable.lupa),
                                contentDescription = "lupa",
                                modifier = Modifier
                                    .height(20.dp).width(20.dp),
                            )}

                    }
                }
            },

            ) { paddingValue ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValue)
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                    {

                        Box(
                            Modifier.background(Color.White).weight(1f),
                            contentAlignment = Alignment.BottomCenter

                        )
                        {
                            Image(
                                painter = painterResource(id = R.drawable.smirnoff),
                                contentDescription = "smirnoff",
                                modifier = Modifier.height(250.dp).padding(bottom = 20.dp)
                            )
                            Text("SMIRNOFF",color = Color.Black, fontWeight = FontWeight.Bold)
                        }

                        Box(
                            Modifier.background(Color.White).weight(1f),
                            contentAlignment = Alignment.BottomCenter
                        )
                        {
                            Image(
                                painter = painterResource(id = R.drawable.cola),
                                contentDescription = "cocacola",
                                modifier = Modifier.height(250.dp).padding(bottom = 20.dp)
                            )
                            Text("COCA COLA",color = Color.Black, fontWeight = FontWeight.Bold)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                    {

                        Box(
                            Modifier.background(Color.White).weight(1f),
                            contentAlignment = Alignment.BottomCenter
                        )
                        {
                            Image(
                                painter = painterResource(id = R.drawable.power),
                                contentDescription = "pOWER",
                                modifier = Modifier.height(250.dp).padding(bottom = 25.dp)
                            )
                            Text("POWER",color = Color.Black, fontWeight = FontWeight.Bold)
                        }

                        Box(
                            Modifier.background(Color.White).weight(1f),
                            contentAlignment = Alignment.BottomCenter
                        )
                        {
                            Image(
                                painter = painterResource(id = R.drawable.bacardi),
                                contentDescription = "bacardi",
                                modifier = Modifier.height(250.dp).padding(bottom = 20.dp)
                            )
                            Text("BACARDI",color = Color.Black, fontWeight = FontWeight.Bold)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                    {

                        Box(
                            Modifier.background(Color.White).weight(1f),
                            contentAlignment = Alignment.BottomCenter
                        )
                        {
                            Image(
                                painter = painterResource(id = R.drawable.jack),
                                contentDescription = "jack",
                                modifier = Modifier.height(250.dp).padding(bottom = 20.dp)
                            )
                            Text("JACK DANIELS",color = Color.Black, fontWeight = FontWeight.Bold)
                        }

                        Box(
                            Modifier.background(Color.White).weight(1f),
                            contentAlignment = Alignment.BottomCenter,

                        )
                        {
                            Image(
                                painter = painterResource(id = R.drawable.yogurt),
                                contentDescription = "yogurt",
                                modifier = Modifier.height(250.dp).padding(bottom = 20.dp)

                            )
                            Text("YOGURT",color = Color.Black, fontWeight = FontWeight.Bold)
                        }
                    }
                }
                Surface(
                    onClick = {},
                    modifier = Modifier

                        .padding(start = 5.dp, top = 0.dp)
                        .height(40.dp)
                        .width(40.dp)
                        .clip(CircleShape),
                    color = Color(0xFFF2DCD8)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text("⬅️")
                    }
                }
            }
        }
    }
}
package com.example.negolatina

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.negolatina.ui.theme.Black
import com.example.negolatina.ui.theme.Breesefir
import com.example.negolatina.ui.theme.Popins

@Preview
@Composable
fun AppleProductScreen() {
    var count by remember { mutableStateOf(0) }

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
                        Text("Cantidad", modifier = Modifier.padding(end = 10.dp), fontFamily = Breesefir, fontSize = 15.sp)

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
                        Text("Añadir al carrito", fontSize = 16.sp, fontFamily = Breesefir)
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
                        modifier = Modifier.height(300.dp).width(250.dp)
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 16.dp, end = 16.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.White)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "S/. 5.40 x Kg",
                            color = Color.Black,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
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
                        Box(Modifier.padding(horizontal = 120.dp, vertical = 5.dp)){
                            Text("\nMANZANA",
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    fontFamily = Black
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
                                    "pequeñas,de color de caoba, encerradas \n" +
                                    "en un endocarpio coriáceo.\n"+
                                    " \n\n VALORACION                           ⭐\uFE0F ⭐\uFE0F ⭐\uFE0F ⭐\uFE0F", fontSize = 16.sp,fontFamily = Popins, fontWeight = FontWeight.Normal
                            )
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
                    .width(200.dp),
                    color = Color(0xFFF2DCD8)

            ) {
                    Box(Modifier.background(Color(0xFFF2DCD8))) {
                        Text(" \uD83D\uDC48", fontSize = 20.sp)
                        }
                }

        }
    }
}
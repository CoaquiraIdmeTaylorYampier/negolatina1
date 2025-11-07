package com.example.negolatina

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
fun ShoppingCartScreen() {

    var countBacardi by remember { mutableStateOf(0) }
    var countCheetos by remember { mutableStateOf(0) }

    val priceBacardi = 37.00
    val priceCheetos = 2.00
    val total = countBacardi * priceBacardi + countCheetos * priceCheetos
    val context = LocalContext.current

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
                                .height(30.dp).width(30.dp),
                        )
                    }
                    Box(
                        Modifier, contentAlignment = Alignment.Center
                    ){
                        Text(
                            "Carrito de compras",
                            style = TextStyle(color = Color.White, fontFamily = Breesefir,fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        )
                    }
                    Box(
                        Modifier.weight(1f),
                        contentAlignment = Alignment.CenterEnd
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.lupa),
                            contentDescription = "lupa",
                            modifier = Modifier
                                .height(30.dp).width(30.dp),
                        )}

                }
            }
        },

    bottomBar = {
        Column {
            Row(Modifier.height(45.dp)
                .background(Color(0xFFD4D0CF))
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Box(Modifier.weight(1f),
                    contentAlignment = Alignment.Center

                ) {
                    Text("TOTAL", fontFamily = Black, fontSize = 15.sp)
                }
                Box(Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(String.format("S/. %.2f", total))
                }
            }
                BottomAppBar(
                    modifier = Modifier.height(70.dp),
                    containerColor = Color(0xFFF0F0F0)
                ) {
                        Row(
                            Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Button(
                                onClick = { context.startActivity(Intent(context, CheckoutLegacyActivity::class.java)) },
                                modifier = Modifier
                                .height(56.dp)
                                .width(300.dp)
                                .padding(end = 8.dp),
                                colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Red
                            )
                        )
                        {
                            Text("Finalizar Compra", fontSize = 20.sp, fontFamily = Breesefir)
                        }
                    }
                }
            }
        }
        ) { paddingValue ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValue)
                .background(Color.White)
        )
            {
            Row(Modifier
                .background(Color(0xFFE3E3E3))
                .fillMaxWidth()
                .height(170.dp))
            {
                Box(Modifier.fillMaxHeight().weight(1f), contentAlignment = Alignment.Center){
                    Image(
                        painter = painterResource(id = R.drawable.bacardi),
                        contentDescription = "bacardi",
                        modifier = Modifier.height(180.dp)
                    )
                }
                Box(Modifier.fillMaxHeight().weight(2f)) {
                    Column(Modifier.fillMaxWidth()) {
                        Box(
                            Modifier.fillMaxWidth().padding(10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Bacardi Dorado", fontFamily = Black, fontSize = 17.sp)
                        }
                        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center)
                        {
                            Text(
                                "       Bacardí Dorado (Carta Oro) es \n" +
                                        "      un ron de color dorado, suave y\n" + "" +
                                        "      de sabores a vainilla, caramelo\n" +
                                        "      y notas de roble.", fontFamily = Popins
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Box() {
                            Row(
                                Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            )
                            {
                                IconButton(
                                    onClick = { if (countBacardi > 0) countBacardi-- },
                                    modifier = Modifier.size(15.dp)
                                )
                                {
                                    Icon(Icons.Default.Remove, contentDescription = "Restar")
                                }

                                Text(
                                    countBacardi.toString(),
                                    modifier = Modifier.padding(horizontal = 8.dp)
                                )

                                IconButton(
                                    onClick = { countBacardi++},
                                    modifier = Modifier.size(15.dp)
                                ) {
                                    Icon(Icons.Default.Add, contentDescription = "Sumar")
                                }
                                Spacer(modifier = Modifier.width(15.dp))

                                Box(Modifier.clip(RoundedCornerShape(10.dp)).padding(6.dp))
                                {
                                    Text(
                                        text = "S/. 37.00(ud.)",
                                        color = Color.Black,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold
                                    )

                                }
                                Spacer(modifier = Modifier.width(15.dp))
                                Button(
                                    onClick = { },
                                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                                    shape = RoundedCornerShape(30.dp),
                                    modifier = Modifier.height(40.dp).width(40.dp),
                                    contentPadding = PaddingValues(0.dp)
                                ) {
                                    Text(
                                        text = "X",
                                        color = Color.White,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }
            }
                Spacer(modifier = Modifier.height(10.dp))
                Row(Modifier
                    .background(Color(0xFFE3E3E3))
                    .fillMaxWidth()
                    .height(170.dp))
                {
                    Box(Modifier.fillMaxHeight().weight(1f), contentAlignment = Alignment.Center){
                        Image(
                            painter = painterResource(id = R.drawable.cheetos)
                            ,contentDescription = "cheetos",
                            modifier= Modifier.height(150.dp)
                        )
                    }
                    Box(Modifier.fillMaxHeight().weight(2f)){
                        Column(Modifier.fillMaxWidth()){
                            Box(Modifier.fillMaxWidth().padding(10.dp), contentAlignment = Alignment.Center){
                                Text("Cheetos Flaming Hot", fontFamily = Black, fontSize = 17.sp)
                            }
                            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center)
                            {
                                Text("       Los Cheetos Flamin' Hot son \n"+
                                    "       bocadillos crujientes con sabor\n"+"" +
                                        "       a queso y un característico  \n"+
                                        "       picante especiado.", fontFamily = Popins)
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Box(){
                                Row(Modifier
                                    .fillMaxWidth()
                                    , horizontalArrangement = Arrangement.Center
                                    , verticalAlignment = Alignment.CenterVertically)
                                {
                                        IconButton(
                                            onClick = { if (countCheetos > 0) countCheetos-- },
                                            modifier = Modifier.size(15.dp)
                                        )
                                        {
                                            Icon(Icons.Default.Remove, contentDescription = "Restar")
                                        }

                                        Text(countCheetos.toString(), modifier = Modifier.padding(horizontal = 8.dp))

                                        IconButton(
                                            onClick = { countCheetos++ },
                                            modifier = Modifier.size(15.dp)
                                        ) {
                                            Icon(Icons.Default.Add, contentDescription = "Sumar")
                                        }
                                        Spacer(modifier = Modifier.width(15.dp))

                                        Box(Modifier.clip(RoundedCornerShape(10.dp)).padding(6.dp))
                                        {
                                            Text(
                                                text = "S/. 2.00 (ud.)",
                                                color = Color.Black,
                                                fontSize = 15.sp,
                                                fontWeight = FontWeight.Bold
                                            )

                                        }
                                    Spacer(modifier = Modifier.width(15.dp))
                                    Button(
                                        onClick = { },
                                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                                        shape = RoundedCornerShape(30.dp),
                                        modifier = Modifier.height(40.dp).width(40.dp),
                                        contentPadding = PaddingValues(0.dp)
                                    ) {
                                        Text(
                                            text = "X",
                                            color = Color.White,
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }

                            }
                        }

                    }

                }

        }
    }

}
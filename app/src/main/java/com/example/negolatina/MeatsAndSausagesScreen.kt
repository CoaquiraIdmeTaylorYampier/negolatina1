package com.example.negolatina

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.negolatina.ui.theme.Breesefir


@Preview
@Composable
fun MeatsAndSausagesScreen() {
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
                            "Carnes y Embutidos",
                            style = TextStyle(color = Color.White, fontFamily = Breesefir,fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        )}
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

        ) { paddingValue ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValue)
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Box(
                    modifier = Modifier
                        .width(180.dp)
                        .height(250.dp)
                        .padding(8.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFE3E3E3)),
                    contentAlignment = Alignment.Center

                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.chuletacerdo),
                            contentDescription = "chule",
                            modifier = Modifier.height(130.dp).width(150.dp)
                        )
                        Spacer(modifier = Modifier.height(21.dp))
                        Text(
                            "CHULETA DE CERDO",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .width(180.dp)
                        .height(250.dp)
                        .padding(8.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFE3E3E3)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.pollo),
                            contentDescription = "pollo",
                            modifier = Modifier.height(120.dp).width(140.dp)
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                        Text(
                            "POLLO",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Box(
                    modifier = Modifier
                        .width(180.dp)
                        .height(250.dp)
                        .padding(8.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFE3E3E3)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.hotdog),
                            contentDescription = "salch",
                            modifier = Modifier.height(135.dp)
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            "SALCHICHAS ",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .width(180.dp)
                        .height(250.dp)
                        .padding(8.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFE3E3E3)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.bisteck),
                            contentDescription = "bisteck",
                            modifier = Modifier.height(110.dp).width(150.dp)
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                        Text(
                            "BISTECK",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Box(
                    modifier = Modifier
                        .width(180.dp)
                        .height(250.dp)
                        .padding(8.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFE3E3E3)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.carnemolida),
                            contentDescription = "carnemoli",
                            modifier = Modifier.height(125.dp).width(150.dp)
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            "CARNE MOLIDA",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .width(180.dp)
                        .height(250.dp)
                        .padding(8.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFE3E3E3)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.jamon),
                            contentDescription = "jamon",
                            modifier = Modifier.height(110.dp)
                        )
                        Spacer(modifier = Modifier.height(35.dp))
                        Text(
                            "JAMON",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

        }
    }
}
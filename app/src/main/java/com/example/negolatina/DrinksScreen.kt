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
fun DrinksScreen() {
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
                            "Bebidas",
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
                            painter = painterResource(id = R.drawable.smirnoff),
                            contentDescription = "smirnoff",
                            modifier = Modifier.height(180.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            "SMIRNOFF",
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
                            painter = painterResource(id = R.drawable.cola),
                            contentDescription = "cocacola",
                            modifier = Modifier.height(195.dp).width(150.dp)
                        )

                        Text(
                            "COCA COLA",
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
                            painter = painterResource(id = R.drawable.power),
                            contentDescription = "power",
                            modifier = Modifier.height(170.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            "POWER ADE",
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
                            painter = painterResource(id = R.drawable.bacardi),
                            contentDescription = "bacardi",
                            modifier = Modifier.height(180.dp).width(200.dp)
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            "BACARDI",
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
                            painter = painterResource(id = R.drawable.jack),
                            contentDescription = "jack",
                            modifier = Modifier.height(180.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            "JACK DANIELS",
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
                            painter = painterResource(id = R.drawable.black),
                            contentDescription = "black",
                            modifier = Modifier.height(180.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            "BLACK LABEL",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

        }
    }
}
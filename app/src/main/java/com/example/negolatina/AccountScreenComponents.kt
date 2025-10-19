package com.example.negolatina

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountAppBar(title: String, onBackClicked: () -> Unit, onEditClicked: () -> Unit) {
    TopAppBar(
        title = { Text(title, color = Color.White) },
        navigationIcon = {
            IconButton(onClick = onBackClicked) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
            }
        },
        actions = {
            IconButton(onClick = onEditClicked) {
                Icon(Icons.Default.Edit, contentDescription = "Edit", tint = Color.White)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Red)
    )
}


@Composable
fun ProfileHeader(userName: String, userEmail: String, userRole: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Profile Picture",
            modifier = Modifier.size(80.dp).clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(userName, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Text(userEmail, color = Color.White, fontSize = 14.sp)
        Text(userRole, color = Color.White, fontSize = 12.sp)
    }
}

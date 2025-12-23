package com.negolatina.app

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun RegisterScreen(
    navController: NavController,
    authViewModel: AuthViewModel = viewModel()
) {
    var fullname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember {mutableStateOf(false)}
    var confirmPasswordVisible by remember {mutableStateOf(false)}

    val registrationState by authViewModel.registrationState.collectAsState()
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    LaunchedEffect(registrationState) {
        when (val state = registrationState) {
            is RegistrationState.Success -> {
                Toast.makeText(context, "¡Registro exitoso!", Toast.LENGTH_SHORT).show()
                navController.navigate("home") { popUpTo("welcome") { inclusive = true } }
                authViewModel.resetRegistrationState()
            }
            is RegistrationState.Error -> {
                Toast.makeText(context, "Error: ${state.message}", Toast.LENGTH_LONG).show()
                authViewModel.resetRegistrationState()
            }
            else -> Unit
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 32.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.registrarse),
                contentDescription = "imagen de venta ",
                modifier = Modifier.size(250.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Crear una cuenta",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = fullname,
                onValueChange = { fullname = it },
                label = { Text("Nombre completo") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = {focusManager.moveFocus(FocusDirection.Down)})
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo electrónico") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = {focusManager.moveFocus(FocusDirection.Down)})
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                visualTransformation = if (passwordVisible) VisualTransformation.None
                else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = {focusManager.moveFocus(FocusDirection.Down)}),
                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Filled.Visibility
                    else
                        Icons.Filled.VisibilityOff
                    val description= if (passwordVisible) "Ocultar contraseña"
                    else "mostrar contraseña"
                    IconButton(onClick = { passwordVisible =! passwordVisible})
                    {
                        Icon(imageVector = image, contentDescription = description)
                    }
                }
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirmar contraseña") },
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None
                    else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {focusManager.clearFocus()
                    if (password==confirmPassword){
                        authViewModel.registerUser(email,password,fullname)
                    } else {
                        Toast.makeText(context, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                    }
                }
                ),
                trailingIcon = {
                    val image = if (confirmPasswordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff
                    val description = if (confirmPasswordVisible) "Ocultar contraseña "
                    else "Mostrar contraseña"
                    IconButton(onClick = {confirmPasswordVisible=!confirmPasswordVisible}) {
                        Icon(imageVector = image, contentDescription = description)
                    }
                },

                isError =password.isNotEmpty()&& confirmPassword.isNotEmpty()
                        && password != confirmPassword
            )
            Spacer(modifier = Modifier.height(24.dp))


            Button(
                onClick = {
                    if (password==confirmPassword){
                        authViewModel.registerUser(email, password, fullname)
                    } else{
                        Toast.makeText(context,"Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                    } },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE31E24)),
                modifier = Modifier.fillMaxWidth().height(50.dp),
                enabled = registrationState != RegistrationState.Loading && password == confirmPassword
            ) {
                if (registrationState == RegistrationState.Loading) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                } else {
                    Text("REGISTRARSE", fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = { navController.navigate("login") }) {
                Text("¿Ya tienes una cuenta? Inicia sesión")
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

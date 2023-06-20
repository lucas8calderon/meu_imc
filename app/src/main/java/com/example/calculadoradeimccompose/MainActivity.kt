package com.example.calculadoradeimccompose

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadoradeimccompose.calculo.CalcularImc
import com.example.calculadoradeimccompose.ui.theme.CalculadoraDeImcComposeTheme
import com.example.calculadoradeimccompose.ui.theme.DARK_BLUE
import com.example.calculadoradeimccompose.ui.theme.LIGHT_BLUE
import com.example.calculadoradeimccompose.ui.theme.WHITE

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculadoraDeImcComposeTheme {
                CalculadoraImc()

            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CalculadoraImc() {
    val context = LocalContext.current
    val calcularImc = CalcularImc()

    var peso by remember {
        mutableStateOf("")
    }

    var altura by remember {
        mutableStateOf("")
    }

    var textoResultado by remember {
        mutableStateOf("")
    }
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = LIGHT_BLUE,
                title = {
                    Text(
                        text = "Calculadora de IMC",
                        color = WHITE
                    )
                },
                actions = {
                    IconButton(onClick = {

                        peso = ""
                        altura = ""
                        textoResultado = ""

                    }) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_refresh),
                            contentDescription = "Icone de resetar todos os campos"
                        )
                    }
                }
            )
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Calculadora de IMC",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = LIGHT_BLUE,
                modifier = Modifier.padding(50.dp)
            )
            OutlinedTextField(
                value = peso,
                onValueChange = {
                    peso = it
                },
                label = {
                    Text(text = "Peso (kg)")
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    cursorColor = LIGHT_BLUE,
                    focusedBorderColor = LIGHT_BLUE,
                    textColor = DARK_BLUE,
                    focusedLabelColor = DARK_BLUE
                ),
                textStyle = TextStyle(DARK_BLUE, 18.sp),
                maxLines = 1,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp, 20.dp, 0.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
            OutlinedTextField(
                value = altura,
                onValueChange = {
                    altura = it
                },
                label = {
                    Text(text = "Altura")
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    cursorColor = LIGHT_BLUE,
                    focusedBorderColor = LIGHT_BLUE,
                    textColor = DARK_BLUE,
                    focusedLabelColor = DARK_BLUE
                ),
                textStyle = TextStyle(DARK_BLUE, 18.sp),
                maxLines = 1,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 10.dp, 20.dp, 0.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
            Button(
                onClick = {
                    if (peso.isEmpty() || altura.isEmpty()) {
                        Toast.makeText(context, "Preencha todos os campos!", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        calcularImc.calcularImc(peso, altura)
                        textoResultado = calcularImc.resultadoImc()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = LIGHT_BLUE,
                    contentColor = WHITE
                )
            ) {
                Text(
                    text = "Calcular IMC",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = textoResultado,
                fontSize = 18.sp,
                color = DARK_BLUE,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CalculadoraDeImcComposeTheme {
        CalculadoraImc()
    }
}
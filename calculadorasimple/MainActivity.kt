package com.example.calculadorasimple

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadorasimple.ui.theme.CalculadoraSimpleTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight


//Problemas del código: no se ve el símbolo de la operación (hecho) y cambiar para que solo se actualice el texto de la operación al darle a calcular
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculadoraSimpleTheme {
                Calculadora()
            }
        }
    }
}


@Composable
fun Calculadora() {

    var operacionSeleccionada by remember {
        mutableStateOf<String?>(" ")
    }
    //El estado inicial de los contenedores será estar vacíos
    var texto1 by remember { mutableStateOf("") }
    var texto2 by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    val colorBotonesNumeros = Color(0xFFFFCEF4)
    val colorBotonesOperaciones = Color(0xFFFADAB5)
    val colorBotonCalcular = Color(0xFFF1B795)
    val colorTexto = Color(0xFF474747)
    val colorFondo = Color(0xFFE7D0FF)
//    val colorFondoSeleccionado = Color(0xFFFFE3C9)

    //Esto sirve para saber donde hemos hecho click, si en número 1 o 2
    var campoSeleccionado by remember { mutableStateOf(1) }
    val CenturyGothic = FontFamily(
        Font(R.font.centurygothic, FontWeight.Normal)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .systemBarsPadding()
    ) {
        //Cuadradito del primer número
        TextField(
            value = texto1,
            colors = TextFieldDefaults.colors(
                focusedTextColor = colorTexto,
                unfocusedTextColor = colorTexto,
                focusedContainerColor = colorBotonesOperaciones,
                unfocusedContainerColor = colorFondo,
            ),
            textStyle = LocalTextStyle.current.copy(fontSize = 20.sp, fontFamily = CenturyGothic),
            onValueChange = { texto1 = it },
            label = {Text("Número 1", fontFamily = CenturyGothic)},
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    if (focusState.isFocused) campoSeleccionado = 1
                }
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text("$operacionSeleccionada",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally),
            fontSize = 20.sp,
            color = colorTexto,
            fontFamily = CenturyGothic,
            fontWeight = FontWeight.Bold

        )
        Spacer(modifier = Modifier.height(5.dp))


        //Cuadradito del segundo número
        TextField(
            value = texto2,
            colors = TextFieldDefaults.colors(
                focusedTextColor = colorTexto,
                unfocusedTextColor = colorTexto,
                focusedContainerColor = colorBotonesOperaciones,
                unfocusedContainerColor = colorFondo,
            ),
            textStyle = LocalTextStyle.current.copy(fontSize = 20.sp, fontFamily = CenturyGothic),
            onValueChange = { texto2 = it },
            label = { Text("Número 2", fontFamily = CenturyGothic)},
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    if (focusState.isFocused) campoSeleccionado = 2
                }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Función que añade dígitos al campo activo
        fun appendNumber(digit: String) {
            if (campoSeleccionado == 1) {
                texto1 += digit
            } else {
                texto2 += digit
            }
        }

        //Botones numéricos
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = {
                        texto1 = ""
                        texto2 = ""
                        resultado = ""
                        operacionSeleccionada = " "
                    },

                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(containerColor = colorBotonesOperaciones, contentColor = colorTexto)
                ) {
                    Text("AC", fontFamily = CenturyGothic, fontWeight = FontWeight.Bold)
                    //AC SIGNIFICA "ALL CLEAR"
                }
                Button(
                    onClick = {
                        if (campoSeleccionado == 1) texto1 = texto1.dropLast(1)
                        else texto2 = texto2.dropLast(1)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(containerColor = colorBotonesOperaciones, contentColor = colorTexto)
                ) {
                    Text("DEL", fontFamily = CenturyGothic, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.height(10.dp))


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = { appendNumber("1") },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(containerColor = colorBotonesNumeros, contentColor = colorTexto)
                ) {
                    Text("1", fontFamily = CenturyGothic, fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = { appendNumber("2") },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(containerColor = colorBotonesNumeros, contentColor = colorTexto)
                ) {
                    Text("2", fontFamily = CenturyGothic, fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = { appendNumber("3") },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(containerColor = colorBotonesNumeros, contentColor = colorTexto)
                ) {
                    Text("3", fontFamily = CenturyGothic, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.height(10.dp))


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = { appendNumber("4") },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(containerColor = colorBotonesNumeros, contentColor = colorTexto)
                ) {
                    Text("4", fontFamily = CenturyGothic, fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = { appendNumber("5") },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(containerColor = colorBotonesNumeros, contentColor = colorTexto)
                ) {
                    Text("5", fontFamily = CenturyGothic, fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = { appendNumber("6") },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(containerColor = colorBotonesNumeros, contentColor = colorTexto)
                ) {
                    Text("6", fontFamily = CenturyGothic, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = { appendNumber("7") },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(containerColor = colorBotonesNumeros, contentColor = colorTexto)
                ) {
                    Text("7", fontFamily = CenturyGothic, fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = { appendNumber("8") },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(containerColor = colorBotonesNumeros, contentColor = colorTexto)
                ) {
                    Text("8", fontFamily = CenturyGothic, fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = { appendNumber("9") },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(containerColor = colorBotonesNumeros, contentColor = colorTexto)
                ) {
                    Text("9", fontFamily = CenturyGothic, fontWeight = FontWeight.Bold)
                }//
            }
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = {},
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(containerColor = colorBotonesNumeros, contentColor = colorTexto)
                ) {
                    Text("ʚ♡ɞ", fontFamily = CenturyGothic, fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = { appendNumber("0") },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(containerColor = colorBotonesNumeros, contentColor = colorTexto)
                ) {
                    Text("0", fontFamily = CenturyGothic, fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = {},
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(containerColor = colorBotonesNumeros, contentColor = colorTexto)
                ) {
                    Text("ʚ♡ɞ", fontFamily = CenturyGothic, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        //Botones de operaciones
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = {
                        operacionSeleccionada = "+"
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorBotonesOperaciones,
                        contentColor = colorTexto
                    )
                ) {
                    Text("+", fontFamily = CenturyGothic, fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = {
                        operacionSeleccionada = "-"
                    }, modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorBotonesOperaciones,
                        contentColor = colorTexto
                    )
                ) {
                    Text("-", fontFamily = CenturyGothic, fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = {
                        operacionSeleccionada = "*"
                    }, modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorBotonesOperaciones,
                        contentColor = colorTexto
                    )
                ) {
                    Text("*", fontFamily = CenturyGothic, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ){
                Button(
                    onClick = {
                        operacionSeleccionada = "/"
                    }, modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorBotonesOperaciones,
                        contentColor = colorTexto
                    )){
                    Text("/", fontFamily = CenturyGothic, fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = {
                        operacionSeleccionada = "%"
                    }, modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorBotonesOperaciones,
                        contentColor = colorTexto
                    )
                ){
                    Text("%", fontFamily = CenturyGothic, fontWeight = FontWeight.Bold)
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))


        Button(
            onClick = {
                val num1 = texto1.toDoubleOrNull() ?: 0.0
                val num2 = texto2.toDoubleOrNull() ?: 0.0
                resultado = when (operacionSeleccionada) {
                    "+" -> (num1 + num2).toString()
                    "-" -> (num1 - num2).toString()
                    "*" -> (num1 * num2).toString()
                    "/" -> (num1 / num2).toString()
                    "%" -> (num1 % num2).toString()
                    else -> {"♡ Error ♡\nPor favor seleccione que operación desea realizar."}
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
            containerColor = colorBotonCalcular,
            contentColor = colorTexto
            )
        ) {
            Text("Calcular", fontFamily = CenturyGothic, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Resultado de $texto1 $operacionSeleccionada $texto2 = $resultado", fontFamily = CenturyGothic,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}
//
//@Composable
//fun TextoErrorPorNoSeleccionarOperacion(
//    modifier: Modifier = Modifier
//){
//    Text(
//        text = "",
//        fontSize = 15.sp,
//        color = Color.Red,
//        modifier = modifier
//
//    )
//}
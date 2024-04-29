import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import java.sql.*


fun main() = application {

    val url = "jdbc:mysql://localhost:3306/studentdb"
    val usuario = "studentuser"
    val contraseña = "password"

    val estado = rememberWindowState(size = DpSize(1200.dp,1200.dp))

    try {
        Class.forName("com.mysql.cj.jdbc.Driver")
        val conexion = DriverManager.getConnection(url, usuario, contraseña)
        println("Conexión exitosa")
        conexion.close()
    } catch (e: SQLException) {
        println("Error en la conexión: ${e.message}")
    } catch (e: ClassNotFoundException) {
        println("No se encontró el driver JDBC: ${e.message}")
    }

    ventanaPrincipal(onCloseReq = ::exitApplication, estado = estado)

}

@Composable
fun ventanaPrincipal(onCloseReq: () -> Unit, estado:WindowState, ){
    Window(
        onCloseRequest = onCloseReq , //rarete, me lo dijo el intelliJ
        title = "VentanaPrincipal",
        state = estado

    ){
        Box(modifier = Modifier
            .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Base de datos iniciada correctamente!")
        }
    }
}


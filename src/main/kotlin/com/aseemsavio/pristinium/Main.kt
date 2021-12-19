package com.aseemsavio.pristinium

import androidx.compose.desktop.DesktopMaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

@Composable
@Preview
fun App() {
    var directories by remember { mutableStateOf("Hello, World!") }

    DesktopMaterialTheme {
        Column {
            Button(onClick = {
                val runtime = Runtime.getRuntime()
                val response = runtime.exec("ls /Users/aseemsavio/Library/Caches").inputStream

                try {
                    val br = BufferedReader(
                        InputStreamReader(response)
                    )
                    var s: String? = null
                    while (br.readLine().also { s = it } != null) {
                        directories += s + "\n"
                    }
                } catch (ioe: IOException) {
                    ioe.printStackTrace()
                }
            }) {
                Text("Show Cache Contents")
            }

            val scroll = rememberScrollState(0)
            Text(
                text = directories,
                modifier = Modifier.verticalScroll(scroll)
            )

            val text = remember { mutableStateOf("Hello!") }
            TextField(
                value = text.value,
                onValueChange = { text.value = it },
                label = { Text(text = "Input") }
            )
        }
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Pristinum for Mac",
    ) {
        App()
    }
}

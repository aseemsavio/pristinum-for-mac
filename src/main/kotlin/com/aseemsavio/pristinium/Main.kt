package com.aseemsavio.pristinium

import androidx.compose.desktop.DesktopMaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.aseemsavio.pristinium.cache.getAllCacheFiles

@Composable
@Preview
fun App() {

    var textContent by remember { mutableStateOf("") }

    DesktopMaterialTheme {
        Column {
            Button(onClick = {
                val files = getAllCacheFiles()
                textContent =
                    "Pristinum scanned ${files.directoryCount} folders and ${files.fileCount} files, totaling ${files.sizeInGb} GB of data."
            }) {
                Text("Scan for Cache")
            }

            Text("")
            Text(textContent)
            Text("")

            Button(onClick = {}) {
                Text("Clear Application Cache")
            }
        }
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Pristinum for Mac"
    ) {
        App()
    }
}

package com.aseemsavio.pristinium

import androidx.compose.desktop.DesktopMaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.aseemsavio.pristinium.cache.getAllCacheFiles

@Composable
@Preview
fun App() {

    var textContent by remember { mutableStateOf("") }
    var cacheClearButtonVisibility by remember { mutableStateOf(false) }

    DesktopMaterialTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Button(onClick = {
                val files = getAllCacheFiles()
                textContent =
                    "Pristinum scanned ${files.directoryCount} folders and ${files.fileCount} files, totaling ${files.sizeInGb} GB of data."
                cacheClearButtonVisibility = true
            }) {
                Text("Scan for Cache")
            }

            Text("")
            Text(textContent)
            Text("")

            if (cacheClearButtonVisibility) {
                Button(onClick = {}) {
                    Text("Clear Application Cache")
                }
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

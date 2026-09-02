package com.madhugfxpro.app

import android.os.Bundle
import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Bg = Color(0xFF090A0F)
private val Card = Color(0xFF14161D)
private val Accent = Color(0xFF7C4DFF)
private val Muted = Color(0xFF9EA3B0)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MadhGFXProApp() }
    }
}

@Composable
fun MadhGFXProApp() {
    MaterialTheme(
        colorScheme = darkColorScheme(
            background = Bg,
            surface = Card,
            primary = Accent
        )
    ) {
        GfxHome()
    }
}

@Composable
private fun GfxHome() {
    var fps by remember { mutableStateOf(60) }
    var graphics by remember { mutableStateOf("Smooth") }
    var antiAliasing by remember { mutableStateOf(true) }
    var shadows by remember { mutableStateOf(false) }
    val context = LocalContext.current

    fun openLink(url: String) {
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    Surface(modifier = Modifier.fillMaxSize(), color = Bg) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(18.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Bolt,
                        contentDescription = null,
                        tint = Accent,
                        modifier = Modifier.size(34.dp)
                    )
                    Spacer(Modifier.width(10.dp))
                    Column {
                        Text("MadhGFXPro", fontSize = 26.sp, fontWeight = FontWeight.Bold)
                        Text("Gaming Graphics Helper", color = Muted, fontSize = 13.sp)
                    }
                }
            }

            item {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Card)
                ) {
                    Column(Modifier.padding(18.dp)) {
                        Text("Performance Mode", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text(
                            "Choose a balanced preset for supported games.",
                            color = Muted,
                            fontSize = 13.sp
                        )
                        Spacer(Modifier.height(14.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            listOf("Balanced", "Performance", "Quality").forEach { mode ->
                                FilterChip(
                                    selected = mode == "Performance",
                                    onClick = { },
                                    label = { Text(mode) }
                                )
                            }
                        }
                    }
                }
            }

            item {
                SettingCard("FPS", "Target frame-rate preference") {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf(30, 60, 90, 120).forEach { value ->
                            FilterChip(
                                selected = fps == value,
                                onClick = { fps = value },
                                label = { Text("$value") }
                            )
                        }
                    }
                }
            }

            item {
                SettingCard("Graphics", "Visual preset") {
                    listOf("Smooth", "Balanced", "HD", "Ultra").forEach { value ->
                        Row(
                            Modifier.fillMaxWidth().padding(vertical = 3.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = graphics == value,
                                onClick = { graphics = value }
                            )
                            Text(value)
                        }
                    }
                }
            }

            item {
                SettingCard("Advanced", "Optional visual features") {
                    SwitchRow("Anti-aliasing", antiAliasing) { antiAliasing = it }
                    SwitchRow("Shadows", shadows) { shadows = it }
                }
            }

            item {
                Button(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth().height(54.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Icon(Icons.Default.CheckCircle, null)
                    Spacer(Modifier.width(8.dp))
                    Text("SAVE PRESET", fontWeight = FontWeight.Bold)
                }
            }

            item {
                SettingCard("Follow Me", "Stay connected with MadhGFXPro") {
                    SocialButton(
                        icon = Icons.Default.CameraAlt,
                        title = "Instagram",
                        handle = "@____madhu_patel____"
                    ) {
                        openLink("https://www.instagram.com/____madhu_patel____")
                    }
                    Spacer(Modifier.height(8.dp))
                    SocialButton(
                        icon = Icons.Default.PlayArrow,
                        title = "YouTube",
                        handle = "@youtuberzx"
                    ) {
                        openLink("https://www.youtube.com/@youtuberzx")
                    }
                }
            }

            item {
                Text(
                    "MadhGFXPro changes preferences only inside this app. It does not modify protected game files or bypass game security.",
                    color = Muted,
                    fontSize = 11.sp,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }
        }
    }
}

@Composable
private fun SettingCard(
    title: String,
    subtitle: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Card)
    ) {
        Column(Modifier.padding(18.dp)) {
            Text(title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(subtitle, color = Muted, fontSize = 13.sp)
            Spacer(Modifier.height(10.dp))
            content()
        }
    }
}

@Composable
private fun SwitchRow(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        Modifier.fillMaxWidth().padding(vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, Modifier.weight(1f))
        Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
}

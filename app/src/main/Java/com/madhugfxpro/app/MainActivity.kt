package com.madhugfxpro.app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Bg = Color(0xFF09DA0F)
private val Card = Color(0xFF14161D)
private val Accent = Color(0xFF7C4DFF)
private val Muted = Color(0xFF9EA3B0)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MadhGFXProApp()
        }
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
fun GfxHome() {
    var fps by remember { mutableStateOf(60) }
    var graphics by remember { mutableStateOf("Smooth") }
    var antiAliasing by remember { mutableStateOf(true) }
    var shadows by remember { mutableStateOf(false) }

    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Bg
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(18.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // =========================
            // HEADER
            // =========================
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Bolt,
                        contentDescription = null,
                        tint = Accent,
                        modifier = Modifier.size(38.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "MadhGFXPro",
                            fontSize = 27.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Gaming Graphics Helper",
                            color = Muted,
                            fontSize = 13.sp
                        )
                    }
                }
            }

            // =========================
            // PERFORMANCE
            // =========================
            item {
                SettingCard(
                    title = "Performance Mode",
                    subtitle = "Choose your preferred performance preset."
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf(
                            "Balanced",
                            "Performance",
                            "Quality"
                        ).forEach { mode ->
                            FilterChip(
                                selected = mode == "Performance",
                                onClick = {},
                                label = {
                                    Text(mode)
                                }
                            )
                        }
                    }
                }
            }

            // =========================
            // FPS
            // =========================
            item {
                SettingCard(
                    title = "FPS",
                    subtitle = "Target frame-rate preference"
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf(
                            30, 60, 90, 120
                        ).forEach { value ->
                            FilterChip(
                                selected = fps == value,
                                onClick = { fps = value },
                                label = { Text("$value FPS") }
                            )
                        }
                    }
                }
            }

            // =========================
            // GRAPHICS
            // =========================
            item {
                SettingCard(
                    title = "Graphics",
                    subtitle = "Select visual preset"
                ) {
                    listOf(
                        "Smooth",
                        "Balanced",
                        "HD",
                        "Ultra"
                    ).forEach { value ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 3.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = graphics == value,
                                onClick = { graphics = value }
                            )
                            Text(text = value)
                        }
                    }
                }
            }

            // =========================
            // ADVANCED
            // =========================
            item {
                SettingCard(
                    title = "Advanced",
                    subtitle = "Optional visual features"
                ) {
                    SwitchRow(
                        title = "Anti-aliasing",
                        checked = antiAliasing
                    ) {
                        antiAliasing = it
                    }

                    SwitchRow(
                        title = "Shadows",
                        checked = shadows
                    ) {
                        shadows = it
                    }
                }
            }

            // =========================
            // SAVE BUTTON
            // =========================
            item {
                Button(
                    onClick = {
                        // Save preset action
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "SAVE PRESET",
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // =========================
            // FOLLOW ME
            // =========================
            item {
                SettingCard(
                    title = "Follow Me",
                    subtitle = "Stay connected with MadhGFXPro"
                ) {
                    SocialButton(
                        icon = Icons.Default.CameraAlt,
                        title = "Instagram",
                        handle = "@____madhu_patel____"
                    ) {
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://www.instagram.com/____madhu_patel____")
                        )
                        context.startActivity(intent)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    SocialButton(
                        icon = Icons.Default.PlayArrow,
                        title = "YouTube",
                        handle = "@youtuberzx"
                    ) {
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://www.youtube.com/@youtuberzx")
                        )
                        context.startActivity(intent)
                    }
                }
            }

            // =========================
            // FOOTER
            // =========================
            item {
                Text(
                    text = "MadhGFXPro • Version 1.0",
                    color = Muted,
                    fontSize = 11.sp,
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 5.dp)
                )

                Text(
                    text = "Settings helper only. Does not modify protected game files or bypass game security.",
                    color = Muted,
                    fontSize = 11.sp,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }
        }
    }
}

@Composable
fun SettingCard(
    title: String,
    subtitle: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Card
        )
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = subtitle,
                color = Muted,
                fontSize = 13.sp
            )
            Spacer(modifier = Modifier.height(12.dp))
            content()
        }
    }
}

@Composable
fun SwitchRow(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            fontSize = 15.sp
        )
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}

@Composable
fun SocialButton(
    icon: ImageVector,
    title: String,
    handle: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp),
        shape = RoundedCornerShape(14.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = handle,
                color = Muted,
                fontSize = 11.sp
            )
        }
        Icon(
            imageVector = Icons.Default.OpenInNew,
            contentDescription = null
        )
    }
}

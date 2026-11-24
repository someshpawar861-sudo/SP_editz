package com.spios27.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Ink = Color(0xFF080B16)
private val Glass = Color(0xB5222B43)
private val Line = Color(0x55FFFFFF)
private val Cyan = Color(0xFF8ADFFF)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { SPiOS27App() }
    }
}

data class QuickTile(val title: String, val icon: ImageVector)

@Composable
fun SPiOS27App() {
    var wifi by remember { mutableStateOf(true) }
    var bluetooth by remember { mutableStateOf(true) }
    var airplane by remember { mutableStateOf(false) }
    var focus by remember { mutableStateOf(false) }
    var brightness by remember { mutableFloatStateOf(.72f) }
    var volume by remember { mutableFloatStateOf(.55f) }
    var showInfo by remember { mutableStateOf(false) }

    val tiles = listOf(
        Triple("Wi-Fi", Icons.Default.Wifi, wifi),
        Triple("Bluetooth", Icons.Default.Bluetooth, bluetooth),
        Triple("Airplane", Icons.Default.Flight, airplane),
        Triple("Focus", Icons.Default.DoNotDisturbOn, focus),
        Triple("Torch", Icons.Default.FlashlightOn, false),
        Triple("Calculator", Icons.Default.Calculate, false),
        Triple("Camera", Icons.Default.CameraAlt, false),
        Triple("Screen", Icons.Default.ScreenShare, false)
    )

    Box(Modifier.fillMaxSize().background(
        Brush.linearGradient(listOf(Color(0xFF101A35), Color(0xFF080B16), Color(0xFF241A3D)))
    )) {
        // Decorative ambient glass orbs
        Box(Modifier.size(230.dp).offset(x = 190.dp, y = 60.dp).blur(65.dp)
            .background(Color(0x665C8DFF), CircleShape))
        Box(Modifier.size(210.dp).offset(x = (-70).dp, y = 430.dp).blur(65.dp)
            .background(Color(0x664BCBDB), CircleShape))

        Column(
            Modifier.fillMaxSize().statusBarsPadding().navigationBarsPadding()
                .padding(horizontal = 18.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                Column {
                    Text("SP iOS 27", color = Color.White, fontSize = 25.sp, fontWeight = FontWeight.Bold)
                    Text("LIQUID GLASS • PREMIUM", color = Cyan, fontSize = 10.sp, letterSpacing = 2.sp)
                }
                IconButton(onClick = { showInfo = !showInfo }) {
                    Icon(Icons.Default.Info, null, tint = Color.White)
                }
            }

            GlassPanel {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {
                    Column {
                        Text("Tuesday, November 24", color = Color.LightGray, fontSize = 13.sp)
                        Text("9:30", color = Color.White, fontSize = 42.sp, fontWeight = FontWeight.Light)
                    }
                    Icon(Icons.Default.Cloud, null, tint = Cyan, modifier = Modifier.size(42.dp))
                }
                Text("Your phone, your style.", color = Color(0xFFCFD8EA), fontSize = 13.sp)
            }

            GlassPanel {
                Text("Connectivity", color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 17.sp)
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    BigToggle("Wi-Fi", Icons.Default.Wifi, wifi) { wifi = !wifi }
                    BigToggle("Bluetooth", Icons.Default.Bluetooth, bluetooth) { bluetooth = !bluetooth }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    BigToggle("Airplane", Icons.Default.Flight, airplane) { airplane = !airplane }
                    BigToggle("Focus", Icons.Default.DoNotDisturbOn, focus) { focus = !focus }
                }
            }

            GlassPanel {
                Text("Quick Controls", color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 17.sp)
                LazyVerticalGrid(columns = GridCells.Fixed(4), modifier = Modifier.height(154.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    items(tiles) { item ->
                        Column(Modifier.clip(RoundedCornerShape(16.dp))
                            .clickable {
                                when(item.first) {
                                    "Wi-Fi" -> wifi = !wifi
                                    "Bluetooth" -> bluetooth = !bluetooth
                                    "Airplane" -> airplane = !airplane
                                    "Focus" -> focus = !focus
                                }
                            }.padding(vertical = 5.dp),
                            horizontalAlignment = Alignment.CenterHorizontally) {
                            Box(Modifier.size(42.dp).clip(CircleShape)
                                .background(if (item.third) Color(0xFF477FBE) else Color(0x553D4A66)),
                                contentAlignment = Alignment.Center) {
                                Icon(item.second, null, tint = Color.White)
                            }
                            Spacer(Modifier.height(4.dp))
                            Text(item.first, color = Color.White, fontSize = 10.sp)
                        }
                    }
                }
            }

            GlassPanel {
                SliderBlock("Brightness", Icons.Default.Brightness6, brightness) { brightness = it }
                SliderBlock("Volume", Icons.Default.VolumeUp, volume) { volume = it }
            }

            AnimatedVisibility(showInfo) {
                GlassPanel {
                    Text("About this build", color = Color.White, fontWeight = FontWeight.Bold)
                    Text("This is an ad-free visual prototype. Tiles change their appearance inside this app; they do not yet control Android system radios or settings.", color = Color(0xFFD3D9E8), fontSize = 12.sp)
                }
            }
            Spacer(Modifier.weight(1f))
            Text("SP EDITZ • CONCEPT BUILD 1.0", color = Color(0x88FFFFFF),
                fontSize = 9.sp, letterSpacing = 1.5.sp, modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
}

@Composable
fun GlassPanel(content: @Composable ColumnScope.() -> Unit) {
    Column(
        Modifier.fillMaxWidth().clip(RoundedCornerShape(26.dp))
            .background(Brush.linearGradient(listOf(Color(0xCC303D59), Color(0x99202A40))))
            .border(1.dp, Line, RoundedCornerShape(26.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp), content = content
    )
}

@Composable
fun BigToggle(label: String, icon: ImageVector, enabled: Boolean, onClick: () -> Unit) {
    Row(Modifier.weight(1f).height(58.dp).clip(RoundedCornerShape(17.dp))
        .background(if (enabled) Color(0xFF376FA8) else Color(0x55434E66))
        .clickable(onClick = onClick).padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Icon(icon, null, tint = Color.White, modifier = Modifier.size(22.dp))
        Text(label, color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun SliderBlock(label: String, icon: ImageVector, value: Float, onChange: (Float) -> Unit) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, null, tint = Cyan, modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(8.dp))
            Text(label, color = Color.White, fontSize = 13.sp)
            Spacer(Modifier.weight(1f))
            Text("${(value * 100).toInt()}%", color = Color.LightGray, fontSize = 11.sp)
        }
        Slider(value = value, onValueChange = onChange,
            colors = SliderDefaults.colors(thumbColor = Color.White, activeTrackColor = Cyan,
                inactiveTrackColor = Color(0x665D6A83)))
    }
}

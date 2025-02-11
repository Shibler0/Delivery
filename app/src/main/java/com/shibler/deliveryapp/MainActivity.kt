package com.shibler.deliveryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shibler.deliveryapp.ui.theme.DeliveryAppTheme
import com.shibler.deliveryapp.ui.theme.uberEatsColor
import kotlin.math.pow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DeliveryAppTheme {
                GreetingPreview()
            }
        }
    }
}

@Preview
@Composable
fun GreetingPreview() {

    val font = FontFamily(Font(R.font.afacadflux))

    val topBar = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()

    val transition = rememberInfiniteTransition(label = "")

    val progress by transition.animateFloat(
        initialValue = 0f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing,
                delayMillis = 0),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    val progress2 by transition.animateFloat(
        initialValue = 0f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing,
                delayMillis = 50),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    val progress3 by transition.animateFloat(
        initialValue = 0f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing,
                delayMillis = 100),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .background(uberEatsColor)
                .padding(top = topBar, bottom = 40.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Preparing your order", color = Color.White, fontSize = 25.sp, fontFamily = font, fontWeight = FontWeight.ExtraBold, modifier = Modifier.alpha(0.5f))
                Text(text = ".", color = Color.White, fontSize = 25.sp, fontFamily = font, fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .offset {
                            IntOffset(0, progress.toInt())
                        }
                        .alpha(0.5f)
                )
                Text(text = ".", color = Color.White, fontSize = 25.sp, fontFamily = font, fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .offset {
                            IntOffset(0, progress2.toInt())
                        }
                        .alpha(0.5f)
                )
                Text(text = ".", color = Color.White, fontSize = 25.sp, fontFamily = font, fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .offset {
                            IntOffset(0, progress3.toInt())
                        }
                        .alpha(0.5f)
                )
            }
            
            //Text(text = "\uD83D\uDD52 Deliver in 15 min", color = Color.White, fontSize = 25.sp, fontFamily = font, fontWeight = FontWeight.ExtraBold, modifier = Modifier.alpha(0.5f).padding(top = 10.dp))
        }

        Spacer(modifier = Modifier.padding(top = 100.dp))
        Pan()
        PanAndIngredients()

        Order()

    }
}

@Composable
fun PanAndIngredients() {
    val transition = rememberInfiniteTransition(label = "")

    val progress by transition.animateFloat(
        initialValue = 0f,
        targetValue = 0.95f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 1700
                0.5f at 300 using CubicBezierEasing(0.57f, 0f, 1f, 0.53f)
                0.95f at 1500 using CubicBezierEasing(0.93f, 0f, 1f, 0.01f)
                0.95f at 1700 using LinearEasing
            },
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    val startX = 80f
    val endX = 600f
    val maxHeight = 450f

    val x = startX + (endX - startX) * progress

    val yOffset = if (progress <= 0.5f) {
        val normalized = progress / 0.5f
        maxHeight * (1 - (1 - normalized).pow(2))
    } else {
        val normalized = (progress - 0.5f) / 0.5f
        maxHeight * (1 - normalized.pow(2))
    }

    Canvas(modifier = Modifier
        .fillMaxWidth()){
        val baseLine = size.height

        drawOval(
            color = Color.Black,
            topLeft = Offset(x + 0f,  baseLine - yOffset - 40f),
            size = Size(50f, 50f)
        )
    }



}
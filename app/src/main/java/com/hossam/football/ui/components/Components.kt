package com.hossam.football.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.imageLoader

fun Modifier.shimmerLoadingAnimation(
    widthOfShadowBrush: Int = 500,
    angleOfAxisY: Float = 270f,
    durationMillis: Int = 1000,
): Modifier {
    return composed {

        val shimmerColors = listOf(
            Color.White.copy(alpha = 0.3f),
            Color.White.copy(alpha = 0.5f),
            Color.White.copy(alpha = 1.0f),
            Color.White.copy(alpha = 0.5f),
            Color.White.copy(alpha = 0.3f),
        )

        val transition = rememberInfiniteTransition(label = "")

        val translateAnimation = transition.animateFloat(
            initialValue = 0f,
            targetValue = (durationMillis + widthOfShadowBrush).toFloat(),
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = durationMillis,
                    easing = LinearEasing,
                ),
                repeatMode = RepeatMode.Restart,
            ),
            label = "Shimmer loading animation",
        )

        this.background(
            brush = Brush.linearGradient(
                colors = shimmerColors,
                start = Offset(x = translateAnimation.value - widthOfShadowBrush, y = 0.0f),
                end = Offset(x = translateAnimation.value, y = angleOfAxisY),
            ),
        )
    }
}
@Composable
@NonRestartableComposable
fun MyImageLoading(
    url: Any,
    modifier: Modifier,

    contentScale: ContentScale = ContentScale.Fit,
    shape: Shape? = null,
    success: (() -> Unit)? = null,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    colorError: Color = Color.Unspecified,
) {

    val m = if (shape != null) Modifier.then(
        Modifier.clip(
            shape
        )
    ) else Modifier


    SubcomposeAsyncImage(model = url,
        imageLoader = LocalContext.current.imageLoader,
        contentScale = contentScale,
        modifier = modifier,
        contentDescription = null,
        filterQuality = FilterQuality.High,
        alpha = alpha,
        colorFilter = colorFilter,
        loading = {

            if (shape != null) {

                Box(
                    m
                        .background(color = Color.LightGray, shape = shape)
                        .shimmerLoadingAnimation()
                )
            } else {
                Box(
                    m
                        .background(color = Color.LightGray)
                        .shimmerLoadingAnimation()
                )

            }


        },
        success = {
            SubcomposeAsyncImageContent(
                m
            )
            success?.invoke()
        },
        error = {
            Icon(
                Icons.Default.Info,
                "",
                modifier = modifier.then(m),
                tint = colorError,
            )
        })
}

@Composable
fun MySpacer() {
    Spacer(modifier = Modifier.padding(5.dp))
}

@Composable
fun MySpacerSmall() {
    Spacer(modifier = Modifier.padding(2.5.dp))
}

@Composable
fun TitleValue(title: String, value: Any?) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(title, style = MaterialTheme.typography.titleMedium)
        Text(value?.toString() ?: "-")
    }
    MySpacer()
}

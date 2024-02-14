package com.example.footbal360.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.footbal360.R
import com.example.footbal360.ui.theme.Green
import com.example.footbal360.ui.theme.MainTextColor
import com.example.footbal360.ui.theme.boldTypeFace
import com.example.footbal360.ui.theme.videoDurationTextLineHeight
import com.example.footbal360.ui.theme.videoDurationTextSize

@Composable
fun VideoDuration(
    modifier: Modifier = Modifier,
    hours: Int = 0,
    minutes: Int = 0,
    seconds: Int = 0
) {
    val doubleDigitMinutes = if (minutes<10) "0$minutes" else minutes
    val doubleDigitSeconds = if (seconds<10) "0$seconds" else seconds
    val videoDurationText = if (hours == 0) {
        "$doubleDigitMinutes:$doubleDigitSeconds"
    } else "$hours:$doubleDigitMinutes:$doubleDigitSeconds"

    val hindiNumeralVideoDurationText = videoDurationText
        .replace("1","۱")
        .replace("2","۲")
        .replace("3","۳")
        .replace("4","۴")
        .replace("5","۵")
        .replace("6","۶")
        .replace("7","۷")
        .replace("8","۸")
        .replace("9","۹")
        .replace("0","۰")



    Box(
        modifier = Modifier
            .padding(6.dp)
            .clip(RoundedCornerShape(2.dp))

    ) {
        Row {
            Box(modifier = Modifier
                .background(Green)
                .width(16.dp)
                .aspectRatio(1f)) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Play Video",
                    tint = Color.White
                )
            }
            Text(
                modifier = Modifier
                    .background(Color.White)
                    .height(16.dp)
                    .padding(2.dp),
                text = hindiNumeralVideoDurationText,
                color = MainTextColor,
                fontFamily = boldTypeFace,
                fontSize = videoDurationTextSize,
                lineHeight = videoDurationTextLineHeight
            )
        }
    }
}










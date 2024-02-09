package com.example.footbal360.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.footbal360.R
import com.example.footbal360.data.model.story.Result
import com.example.footbal360.ui.theme.Typography
import com.example.footbal360.ui.theme.startingPadding
import com.example.footbal360.ui.theme.storyPadding

@Composable
fun Story(
    story: Result
) {
    Box(
        modifier = Modifier
            .height(220.dp)
            .width(120.dp)
            .padding(storyPadding)
    ) {
        val storyImageState = rememberAsyncImagePainter(
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(story.last_story.image)
                .size(Size.ORIGINAL)
                .build()
        ).state
        val storyIconState = rememberAsyncImagePainter(
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(story.icon)
                .size(Size.ORIGINAL)
                .build()
        ).state

        if (storyImageState is AsyncImagePainter.State.Loading) {
            Box(
                modifier = Modifier
                    .width(150.dp)
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        if (storyImageState is AsyncImagePainter.State.Error) {
            Box(
                modifier = Modifier
                    .width(150.dp)
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Loading error!",
                    color = Color.LightGray,
                    fontSize = 8.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
        if (storyImageState is AsyncImagePainter.State.Success) {
            Image(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(120.dp)
                    .clip(RoundedCornerShape(10.dp)),
                painter = storyImageState.painter,
                contentDescription = story.title,
                contentScale = ContentScale.FillHeight
            )
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clip(RoundedCornerShape(10.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Black.copy(alpha = 0f),
                        Color.Black.copy(alpha = 0f),
                        Color.Black.copy(alpha = 0.3f)

                    )
                )
            )

        )
        Column(
            Modifier
                .fillMaxSize()
                .padding(6.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Box(
                    modifier = Modifier
                        .width(28.dp)
                        .height(28.dp)
                        .clip(CircleShape)
                        .background(Color.Green),
                    contentAlignment = Alignment.Center
                ) {
                    if (storyIconState is AsyncImagePainter.State.Success) {
                        Image(
                            modifier = Modifier
                                .width(24.dp)
                                .height(24.dp)
                                .clip(CircleShape),
                            painter = storyIconState.painter,
                            contentDescription = "Story Icon",
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = story.title,
                color = Color.White,
                fontSize = 16.sp,
                maxLines = 2,
                fontFamily = FontFamily(Font(R.font.iran_sansx_bold)),
                textAlign = TextAlign.End,
                letterSpacing = (-0.05).sp
            )
        }
    }
}
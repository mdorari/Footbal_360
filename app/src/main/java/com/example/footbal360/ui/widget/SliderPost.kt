package com.example.footbal360.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.footbal360.R
import com.example.footbal360.data.model.sections.Post
import com.example.footbal360.ui.theme.sliderPadding
import com.example.footbal360.ui.theme.titleLineHeight
import com.example.footbal360.ui.theme.titleTextSize
import com.example.footbal360.ui.theme.titleTopAndBottomPadding

@Composable
fun SliderPost(
    post: Post,
    width:Dp,
    textSize:TextUnit,
    textLineHeight: TextUnit,
    cornerRadius:Dp
) {

    val imageState = rememberAsyncImagePainter(
        model = ImageRequest
            .Builder(LocalContext.current)
            .data(post.primary_media.thumbnail)
            .size(Size.ORIGINAL)
            .build()
    ).state
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(width),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(sliderPadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (imageState is AsyncImagePainter.State.Loading) {
                Box(
                    modifier = Modifier
                        .width(200.dp)
                        .height(200.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            if (imageState is AsyncImagePainter.State.Error) {
                Box(
                    modifier = Modifier
                        .width(200.dp)
                        .height(200.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Loading error!",
                        color = Color.LightGray,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
            if (imageState is AsyncImagePainter.State.Success) {
                Box(
                    modifier = Modifier
                        .aspectRatio(1.78f)
                        .height(200.dp)
                        .clip(RoundedCornerShape(cornerRadius)),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = imageState.painter,
                        contentDescription = post.primary_media.title,
                        contentScale = ContentScale.Crop
                    )
                    if (post.primary_media.duration != null){
                        VideoDuration(
                            hours = post.primary_media.hour_duration,
                            minutes = post.primary_media.minute_duration,
                            seconds = post.primary_media.second_duration
                        )
                    }
                }
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = titleTopAndBottomPadding,
                        bottom = titleTopAndBottomPadding
                    ),
                text = post.title,
                fontSize = textSize,
                fontFamily = FontFamily(Font(R.font.iran_sansx_bold)),
                fontStyle = FontStyle.Normal,
                lineHeight = textLineHeight,
                color = Color.Black,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.End
            )
//                Text(text = post.sub_title)
        }
    }
}
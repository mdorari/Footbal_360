package com.example.footbal360.ui.widget

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.footbal360.R
import com.example.footbal360.data.model.sections.Data
import com.example.footbal360.ui.theme.MainTextColor
import com.example.footbal360.ui.theme.storyPadding
import com.example.footbal360.ui.theme.subtitleLineHeight
import com.example.footbal360.ui.theme.subtitleTextSize
import com.example.footbal360.ui.theme.titleLineHeight
import com.example.footbal360.ui.theme.titleTextSize
import com.example.footbal360.ui.theme.titleTopAndBottomPadding
import com.example.footbal360.util.timeDifference

@Composable
fun BottomSection(data: Data) {
    val posts = data.posts
    val bannerPost = posts[0]

    val bannerImageState = rememberAsyncImagePainter(
        model = ImageRequest
            .Builder(LocalContext.current)
            .data(bannerPost.primary_media.thumbnail)
            .size(Size.ORIGINAL)
            .build()
    ).state


    Column(modifier = Modifier.padding(storyPadding)) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = titleTopAndBottomPadding,
                    top = titleTopAndBottomPadding
                ),
            text = data.title,
            fontFamily = FontFamily(Font(R.font.iran_sansx_bold)),
            fontSize = titleTextSize,
            lineHeight = titleLineHeight,
            color = MainTextColor,
            textAlign = TextAlign.Start,
            maxLines = 2,
            letterSpacing = (-0.05).sp,
            style = TextStyle(textDirection = TextDirection.Content)
        )
        if (bannerImageState is AsyncImagePainter.State.Loading) {
            Box(
                modifier = Modifier
                    .height(220.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .shimmerEffect()
            )
        }
        if (bannerImageState is AsyncImagePainter.State.Error) {
            Box(
                modifier = Modifier
                    .height(220.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Error")
            }
        }
        if (bannerImageState is AsyncImagePainter.State.Success) {
//            Image(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .aspectRatio(1.78f)
//                    .clip(RoundedCornerShape(10.dp)),
//                painter = bannerImageState.painter,
//                contentDescription = bannerPost.title,
//                contentScale = ContentScale.Crop
//            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.78f)
                    .clip(RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.BottomStart
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = bannerImageState.painter,
                    contentDescription = bannerPost.primary_media.title,
                    contentScale = ContentScale.Crop
                )
                if (bannerPost.primary_media.duration != null){
                    VideoDuration(
                        hours = bannerPost.primary_media.hour_duration,
                        minutes = bannerPost.primary_media.minute_duration,
                        seconds = bannerPost.primary_media.second_duration
                    )
                }
            }
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = titleTopAndBottomPadding,
                    top = titleTopAndBottomPadding
                ),
            text = bannerPost.title,
            fontFamily = FontFamily(Font(R.font.iran_sansx_demi_bold)),
            fontSize = titleTextSize,
            color = MainTextColor,
            textAlign = TextAlign.Start,
            style = TextStyle(textDirection = TextDirection.Content),
            maxLines = 2,
            letterSpacing = (-0.05).sp
        )
        Spacer(
            modifier = Modifier
                .height(10.dp)
                .fillMaxWidth()
        )
        Column {
            posts.subList(1, 5).forEach { post ->
                val postImageState = rememberAsyncImagePainter(
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(post.primary_media.thumbnail)
                        .size(Size.ORIGINAL)
                        .build()
                ).state

                Row {
                    if (postImageState is AsyncImagePainter.State.Loading) {
                        Box(
                            modifier = Modifier
                                .width(130.dp)
                                .aspectRatio(1.78f)
                                .clip(RoundedCornerShape(4.dp))
                                .shimmerEffect()
                        )
                    }
                    if (postImageState is AsyncImagePainter.State.Error) {
                        Box(
                            modifier = Modifier
                                .width(130.dp)
                                .aspectRatio(1.78f)
                                .background(Color.White)
                                .clip(RoundedCornerShape(4.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "Error")
                        }
                    }
                    if (postImageState is AsyncImagePainter.State.Success) {
//                        Image(
//                            modifier = Modifier
//                                .width(130.dp)
//                                .aspectRatio(1.78f)
//                                .clip(RoundedCornerShape(4.dp)),
//                            painter = postImageState.painter,
//                            contentDescription = post.title,
//                            contentScale = ContentScale.Crop
//                        )

                        Box(
                            modifier = Modifier
                                .width(130.dp)
                                .aspectRatio(1.78f)
                                .clip(RoundedCornerShape(4.dp)),
                            contentAlignment = Alignment.BottomStart
                        ) {
                            Image(
                                modifier = Modifier.fillMaxSize(),
                                painter = postImageState.painter,
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

                    Column (modifier = Modifier.fillMaxWidth()){
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = post.title,
                            textAlign = TextAlign.Start,
                            style = TextStyle(textDirection = TextDirection.Content),
                            fontFamily = FontFamily(Font(R.font.iran_sansx_demi_bold)),
                            color = MainTextColor,
                            fontSize = subtitleTextSize,
                            lineHeight = subtitleLineHeight,
                            maxLines = 3,
                            letterSpacing = (-0.05).sp
                        )
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = timeDifference(post.published_at.toLong()),
                                textAlign = TextAlign.Start,
                                style = TextStyle(textDirection = TextDirection.Content),
                                fontFamily = FontFamily(Font(R.font.iran_sansx_demi_bold)),
                                color = Color.DarkGray,
                                fontSize = subtitleTextSize/2,
                                lineHeight = subtitleLineHeight,
                                maxLines = 3,
                                letterSpacing = (-0.05).sp
                            )
                        }

                    }
                }
                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}
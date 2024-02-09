package com.example.footbal360.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.text.style.TextAlign
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
            textAlign = TextAlign.End,
            maxLines = 2,
            letterSpacing = (-0.05).sp
        )
        if (bannerImageState is AsyncImagePainter.State.Loading) {
            Box(
                modifier = Modifier
                    .height(220.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
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
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.78f)
                    .clip(RoundedCornerShape(10.dp)),
                painter = bannerImageState.painter,
                contentDescription = bannerPost.title,
                contentScale = ContentScale.Crop
            )
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
            textAlign = TextAlign.End,
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
                                .width(150.dp)
                                .aspectRatio(1.78f),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                    if (postImageState is AsyncImagePainter.State.Error) {
                        Box(
                            modifier = Modifier
                                .width(150.dp)
                                .aspectRatio(1.78f)
                                .background(Color.White)
                                .clip(RoundedCornerShape(4.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "Error")
                        }
                    }
                    if (postImageState is AsyncImagePainter.State.Success) {
                        Image(
                            modifier = Modifier
                                .width(150.dp)
                                .aspectRatio(1.78f)
                                .clip(RoundedCornerShape(4.dp)),
                            painter = postImageState.painter,
                            contentDescription = post.title,
                            contentScale = ContentScale.Crop
                        )
                    }

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = post.title,
                        textAlign = TextAlign.Right,
                        fontFamily = FontFamily(Font(R.font.iran_sansx_demi_bold)),
                        color = MainTextColor,
                        fontSize = subtitleTextSize,
                        lineHeight = subtitleLineHeight,
                        maxLines = 3,
                        letterSpacing = (-0.05).sp
                    )
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

//                items(posts) { post ->
//                    val postImageState = rememberAsyncImagePainter(
//                        model = ImageRequest
//                            .Builder(LocalContext.current)
//                            .data(post.primary_media)
//                            .size(Size.ORIGINAL)
//                            .build()
//                    ).state
//                    if (postImageState is AsyncImagePainter.State.Success) {
//                        Row {
//                            Image(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .height(50.dp)
//                                    .clip(RoundedCornerShape(2.dp)),
//                                painter = postImageState.painter,
//                                contentDescription = post.title,
//                                contentScale = ContentScale.Crop
//                            )
//                            Text(modifier = Modifier.fillMaxWidth().weight(1f),
//                                text = post.title,
//                                textAlign = TextAlign.End,
//                                fontFamily = FontFamily(Font(R.font.iran_sansx_demi_bold)),
//                                color = MainTextColor,
//                                fontSize = subtitleTextSize)
//                        }
//                    }
//
//                }


//fun LazyListScope.bottomLazyColumn(posts: List<Post>) {
//    items(posts) { post ->
//        val postImageState = rememberAsyncImagePainter(
//            model = ImageRequest
//                .Builder(LocalContext.current)
//                .data(post.primary_media)
//                .size(Size.ORIGINAL)
//                .build()
//        ).state
//        if (postImageState is AsyncImagePainter.State.Success) {
//            Row {
//                Image(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(50.dp)
//                        .clip(RoundedCornerShape(2.dp)),
//                    painter = postImageState.painter,
//                    contentDescription = post.title,
//                    contentScale = ContentScale.Crop
//                )
//                Text(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .weight(1f),
//                    text = post.title,
//                    textAlign = TextAlign.End,
//                    fontFamily = FontFamily(Font(R.font.iran_sansx_demi_bold)),
//                    color = MainTextColor,
//                    fontSize = subtitleTextSize
//                )
//            }
//        }
//    }
//}
package com.example.footbal360

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.footbal360.data.FootballRepositoryImpl
import com.example.footbal360.data.RetrofitInstance
import com.example.footbal360.data.model.sections.AllPosts
import com.example.footbal360.data.model.sections.Post
import com.example.footbal360.ui.screens.FootballViewModel
import com.example.footbal360.ui.theme.Footbal360Theme
import kotlinx.coroutines.flow.collectLatest

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<FootballViewModel>(factoryProducer = {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return FootballViewModel(FootballRepositoryImpl(RetrofitInstance.api))
                        as T
            }
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Footbal360Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val explorerData = viewModel.allPosts.collectAsState().value
                    val context = LocalContext.current


                    LaunchedEffect(key1 = viewModel.showErrorToastChannel) {
                        viewModel.showErrorToastChannel.collectLatest { show ->
                            if (show) {
                                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }



                    if (explorerData.data.isEmpty()) {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    } else {
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            val sliderPosts = explorerData.data[0].posts
                            val jamemelatha2Posts = explorerData.data[1].posts
                            val jamemelathaPosts = explorerData.data[2].posts
                            val kolaseJameasia = explorerData.data[3].posts
                            val videocast = explorerData.data[4].posts

                            val lazyColumnList : List<List<Post>> = listOf(
                                sliderPosts,
                                jamemelatha2Posts,
                                jamemelathaPosts,
                                kolaseJameasia,
                                videocast
                            )

                            items(lazyColumnList){posts->
                                LazyRow(modifier = Modifier.height(300.dp),
                                    reverseLayout = true) {
                                    items(posts) { post ->
                                        PostItem(post = post)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun PostItem(post: Post) {
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
                .width(380.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
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
                        CircularProgressIndicator()
                    }
                }
                if (imageState is AsyncImagePainter.State.Success) {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        painter = imageState.painter,
                        contentDescription = post.primary_media.title,
                        contentScale = ContentScale.Crop
                    )
                }
                Text(
                    text = post.title,
                    fontSize = 24.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontStyle = FontStyle.Normal,
                    lineHeight = 28.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.End
                )
//                Text(text = post.sub_title)
            }
        }
    }
}


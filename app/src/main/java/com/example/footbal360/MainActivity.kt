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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
import com.example.footbal360.data.model.sections.Post
import com.example.footbal360.ui.screens.BottomSheetPostsViewModel
import com.example.footbal360.ui.screens.ChipsViewModel
import com.example.footbal360.ui.screens.FootballViewModel
import com.example.footbal360.ui.screens.MainScreen
import com.example.footbal360.ui.screens.StoriesViewModel
import com.example.footbal360.ui.theme.Background
import com.example.footbal360.ui.theme.Footbal360Theme
import kotlinx.coroutines.flow.collectLatest

class MainActivity : ComponentActivity() {

    private val footballViewModel by viewModels<FootballViewModel>(factoryProducer = {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return FootballViewModel(FootballRepositoryImpl(RetrofitInstance.api))
                        as T
            }
        }
    })

    private val storiesViewModel by viewModels<StoriesViewModel>(factoryProducer = {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return StoriesViewModel(FootballRepositoryImpl(RetrofitInstance.api))
                        as T
            }
        }
    })

    private val chipsViewModel by viewModels<ChipsViewModel>(factoryProducer = {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ChipsViewModel(FootballRepositoryImpl(RetrofitInstance.api))
                        as T
            }
        }
    })

    private val bottomSheetPostsViewModel by viewModels<BottomSheetPostsViewModel>(factoryProducer = {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return BottomSheetPostsViewModel(FootballRepositoryImpl(RetrofitInstance.api))
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
                    color = Background
                ) {

//                    val explorerData = footballViewModel.allPosts.collectAsState().value
                    val context = LocalContext.current

                    Scaffold(
                        topBar = {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                            ) {
                                Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search"
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Image(
                                    painter = painterResource(id = R.drawable.new_desktop_logo),
                                    contentDescription = "desktop logo"
                                )
                            }
                        }
                    ) { paddingValues ->
                        LaunchedEffect(key1 = footballViewModel.showErrorToastChannel) {
                            footballViewModel.showErrorToastChannel.collectLatest { show ->
                                if (show) {
                                    Toast.makeText(
                                        context,
                                        "Error loading Slider",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                        LaunchedEffect(key1 = storiesViewModel.showErrorToastChannel) {
                            storiesViewModel.showErrorToastChannel.collectLatest { show ->
                                if (show) {
                                    Toast.makeText(
                                        context,
                                        "Error loading stories",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                        MainScreen(
                            footballViewModel = footballViewModel,
                            storiesViewModel = storiesViewModel,
                            chipsViewModel = chipsViewModel,
                            bottomSheetPostsViewModel = bottomSheetPostsViewModel,
                            paddingValues = paddingValues
                        )
                    }
                }
            }
        }
    }
}


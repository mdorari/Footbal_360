package com.example.footbal360

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import com.example.footbal360.data.FootballRepositoryImpl
import com.example.footbal360.data.RetrofitInstance
import com.example.footbal360.ui.screens.MyNavigation
import com.example.footbal360.ui.screens.getBottomNavigationItems
import com.example.footbal360.ui.screens.mainScreen.BottomSheetPostsViewModel
import com.example.footbal360.ui.screens.mainScreen.ChipsViewModel
import com.example.footbal360.ui.screens.mainScreen.FootballViewModel
import com.example.footbal360.ui.screens.mainScreen.StoriesViewModel
import com.example.footbal360.ui.screens.videos.VideosViewModel
import com.example.footbal360.ui.theme.Background
import com.example.footbal360.ui.theme.Footbal360Theme
import com.example.footbal360.ui.theme.MainTextColor
import com.example.footbal360.ui.theme.boldTypeFace
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

    private val videosViewModel by viewModels<VideosViewModel>(factoryProducer = {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return VideosViewModel(FootballRepositoryImpl(RetrofitInstance.api))
                        as T
            }
        }
    })

    @OptIn(ExperimentalMaterial3Api::class)
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
                    val bottomNavigationItems = getBottomNavigationItems()
                    var selectedItemIndex by rememberSaveable {
                        mutableIntStateOf(4)
                    }
                    val navController = rememberNavController()



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
                        },
                        bottomBar = {
                            NavigationBar {
                                bottomNavigationItems.forEachIndexed { index, item ->
                                    val currentSelectedItem =
                                        bottomNavigationItems[selectedItemIndex]
                                    Log.d("Mehrdad", "onCreate: $currentSelectedItem")
                                    NavigationBarItem(
                                        selected = selectedItemIndex == index,
                                        onClick = {
//                                            navController.popBackStack(route = item.route,inclusive = true)
                                            navController.navigate(
                                                route = item.route,
                                                navOptions = NavOptions.Builder()
                                                    .setPopUpTo(
                                                        currentSelectedItem.route,
                                                        inclusive = true
                                                    )
                                                    .build()
                                            )
                                            selectedItemIndex = index
                                        },
                                        label = {
                                            Text(text = item.title, fontFamily = boldTypeFace)
                                        },
                                        icon = {
                                            BadgedBox(
                                                badge = {
                                                    if (item.badgeCount != null) {
                                                        Badge {
                                                            Text(text = item.badgeCount.toString())
                                                        }
                                                    } else if (item.hasNews) {
                                                        Badge()
                                                    }
                                                }
                                            ) {
                                                Text(
                                                    text = if (index == selectedItemIndex) {
                                                        item.selectedIcon
                                                    } else item.unselectedIcon,
                                                    fontFamily = FontFamily(Font(R.font.f360_icomoon)),
                                                    fontSize = 24.sp,
                                                    color = MainTextColor
                                                )
//                                                Icon(
//                                                    imageVector = if (index == selectedItemIndex) {
//                                                        item.selectedIcon
//                                                    } else item.unselectedIcon,
//                                                    contentDescription = item.title
//                                                )
                                            }
                                        })
                                }

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
                        MyNavigation(
                            navController = navController,
                            paddingValues = paddingValues,
                            footballViewModel = footballViewModel,
                            storiesViewModel = storiesViewModel,
                            bottomSheetPostsViewModel = bottomSheetPostsViewModel,
                            chipsViewModel = chipsViewModel,
                            videosViewModel = videosViewModel
                        )
//                        MainScreen(
//                            footballViewModel = footballViewModel,
//                            storiesViewModel = storiesViewModel,
//                            chipsViewModel = chipsViewModel,
//                            bottomSheetPostsViewModel = bottomSheetPostsViewModel,
//                            paddingValues = paddingValues
//                        )
                    }
                }
            }
        }
    }
}


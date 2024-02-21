package com.example.footbal360.ui.screens.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.footbal360.R
import com.example.footbal360.ui.screens.Screens
import com.example.footbal360.ui.theme.MainTextColor
import com.example.footbal360.ui.theme.h2LetterSpacing
import com.example.footbal360.ui.theme.h2TextSize
import com.example.footbal360.ui.theme.startingPadding
import com.example.footbal360.ui.theme.storyPadding
import com.example.footbal360.ui.theme.titleLineHeight
import com.example.footbal360.ui.theme.titleTextSize
import com.example.footbal360.ui.widget.BottomSection
import com.example.footbal360.ui.widget.Chip
import com.example.footbal360.ui.widget.SliderPost
import com.example.footbal360.ui.widget.Story


@Composable
fun MainScreen(
    paddingValues: PaddingValues,
    mainScreenViewModel: MainScreenViewModel,
    navController: NavHostController
) {
    val sliderData = mainScreenViewModel.sliderPosts.collectAsState().value
    val storiesData = mainScreenViewModel.stories.collectAsState().value
    val chipsData = mainScreenViewModel.chips.collectAsState().value
    val bottomSheetData = mainScreenViewModel.bottomPosts.collectAsState().value

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        if (sliderData.data.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyRow(modifier = Modifier
                .height(270.dp)
                .padding(end = startingPadding),
                reverseLayout = true) {
                val sliderPosts = sliderData.data.filter { it.key == "slider" }.first().posts
                items(sliderPosts) { post ->
                    SliderPost(post = post,
                        width = 320.dp,
                        textSize = titleTextSize,
                        textLineHeight = titleLineHeight,
                        cornerRadius = 12.dp
                    ){
                        navController.navigate(route = Screens.VIDEO_POST.name + "/${it.code}")
                    }
                }
            }
        }
        
        Text(
            modifier = Modifier
                .padding(top = 10.dp, bottom = 10.dp, end = startingPadding * 2)
                .fillMaxWidth(),
            text = "سر ضرب",
            fontFamily = FontFamily(Font(R.font.iran_sansx_bold)),
            fontSize = h2TextSize,
            letterSpacing = h2LetterSpacing,
            color = MainTextColor,
            textAlign = TextAlign.Start,
            style = TextStyle(textDirection = TextDirection.Content))

        if (storiesData.results.isEmpty()){
            Box(modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center){
                CircularProgressIndicator()
            }
        }else{
            LazyRow(modifier = Modifier
                .height(200.dp)
                .padding(end = startingPadding),
                reverseLayout = true) {
                val stories = storiesData.results
                items(stories) { story ->
                    Story(story)
                }
            }
        }

        Text(
            modifier = Modifier
                .padding(top = 20.dp, bottom = 10.dp, end = startingPadding * 2)
                .fillMaxWidth(),
            text = "دسته ها",
            fontFamily = FontFamily(Font(R.font.iran_sansx_bold)),
            fontSize = h2TextSize,
            color = MainTextColor,
            textAlign = TextAlign.Start,
            style = TextStyle(textDirection = TextDirection.Content)
        )

        if (chipsData.data.isNotEmpty()){
            LazyRow(modifier = Modifier.height(40.dp),
                reverseLayout = true,
                contentPadding = PaddingValues(2.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
                ){
                val chips = chipsData.data.sortedBy { it.order }
                items(chips){chip->
                    Chip(chip)
                }
            }
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.White))

        if (bottomSheetData.data.isNotEmpty()){
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(storyPadding)){
                val bottomSheetPosts = bottomSheetData.data.subList(1,5)
                bottomSheetPosts.forEach { data->
                    BottomSection(data = data)
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun MainScreenPreview() {
//    MainScreen(
//        paddingValues = PaddingValues(),
//        FootballViewModel(FootballRepositoryImpl(RetrofitInstance.api)),
//        StoriesViewModel(FootballRepositoryImpl(RetrofitInstance.api)),
//        BottomSheetPostsViewModel(FootballRepositoryImpl(RetrofitInstance.api)),
//        ChipsViewModel(FootballRepositoryImpl(RetrofitInstance.api)),
//        navController = rememberNavController()
//    )
//}















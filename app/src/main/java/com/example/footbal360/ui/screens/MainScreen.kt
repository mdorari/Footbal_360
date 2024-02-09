package com.example.footbal360.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.footbal360.R
import com.example.footbal360.ui.theme.MainTextColor
import com.example.footbal360.ui.theme.startingPadding
import com.example.footbal360.ui.theme.storyPadding
import com.example.footbal360.ui.widget.BottomSection
import com.example.footbal360.ui.widget.Chip
import com.example.footbal360.ui.widget.SliderPost
import com.example.footbal360.ui.widget.Story


@Composable
fun MainScreen(
    paddingValues: PaddingValues,
    footballViewModel: FootballViewModel,
    storiesViewModel: StoriesViewModel,
    bottomSheetPostsViewModel: BottomSheetPostsViewModel,
    chipsViewModel: ChipsViewModel
) {
    val sliderData = footballViewModel.sliderPosts.collectAsState().value
    val storiesData = storiesViewModel.stories.collectAsState().value
    val chipsData = chipsViewModel.chips.collectAsState().value
    val bottomSheetData = bottomSheetPostsViewModel.bottomPosts.collectAsState().value

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
                .height(300.dp)
                .padding(end = startingPadding),
                reverseLayout = true) {
                val sliderPosts = sliderData.data.filter { it.key == "slider" }.first().posts
                items(sliderPosts) { post ->
                    SliderPost(post = post)
                }
            }
        }
        
        Text(
            modifier = Modifier
                .padding(top = 20.dp, bottom = 10.dp, end = startingPadding * 2)
                .fillMaxWidth(),
            text = "سر ضرب",
            fontFamily = FontFamily(Font(R.font.iran_sansx_bold)),
            fontSize = 22.sp,
            color = MainTextColor,
            textAlign = TextAlign.Right)

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
            fontSize = 22.sp,
            color = MainTextColor,
            textAlign = TextAlign.Right)

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
            Column(modifier = Modifier.fillMaxWidth().padding(storyPadding)){
                val bottomSheetPosts = bottomSheetData.data.subList(1,5)
                bottomSheetPosts.forEach { data->
                    BottomSection(data = data)
                }
//                BottomSection(data = bottomSheetPosts)
//                items(bottomSheetPosts){section->
//                    BottomSection(bottomSheetPosts)
//                }
            }
        }
    }
}

//@Preview
//@Composable
//fun MainScreenPreview() {
//    MainScreen(
//        modifier = Modifier,
//        viewModel = FootballViewModel(FootballRepositoryImpl(RetrofitInstance.api))
//    )
//}















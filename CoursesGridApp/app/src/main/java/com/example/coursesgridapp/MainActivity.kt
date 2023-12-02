package com.example.coursesgridapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coursesgridapp.data.DataSource
import com.example.coursesgridapp.model.Topic
import com.example.coursesgridapp.ui.theme.CoursesGridAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoursesGridAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CourseApp()
                }
            }
        }
    }
}
@Composable
fun CourseApp() {
    val leftColumnTopics = DataSource.topics.filterIndexed { index, _ ->
        index % 2 == 0
    }

    val rightColumnTopics = DataSource.topics.filterIndexed { index, _ ->
        index % 2 != 0
    }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceEvenly

        ) {
            CourseListColumn(
                courseList = leftColumnTopics,
                modifier = Modifier.padding(start = 8.dp, top = 8.dp, end = 0.dp)
            )
            CourseListColumn(
                courseList = rightColumnTopics,
                modifier = Modifier.padding(start = 8.dp, top = 8.dp, end = 8.dp)
            )
        }

}

@Composable
fun CardCourse(
    courseTopic: Topic,
    modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier.height(68.dp).padding(bottom = 8.dp).width(192.dp)
    ) {
        Row(
            modifier = Modifier.width(192.dp)
        ) {
            Image(
                painter = painterResource(id = courseTopic.imageResourceId),
                contentDescription = null,
                modifier = Modifier
                    .size(width = 68.dp, height = 68.dp)
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    .width(92.dp)
                    .height(52.dp)
            ) {
                Text(
                    text = stringResource(id = courseTopic.name),
                    fontSize = 12.sp,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Row (
                    modifier = Modifier.padding(bottom = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.ic_grain),
                        contentDescription = null)
                    Text(
                        text = courseTopic.associatedCourse.toString(),
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(start = 8.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun CourseListColumn(courseList: List<Topic>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(courseList) { course ->
            CardCourse(
                courseTopic = course,
                modifier = modifier
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CourseAppPreview() {
    CoursesGridAppTheme {
//        CardCourse(
//            Topic(R.string.architecture, 58, R.drawable.architecture)
//        )
        CourseApp()
    }
}
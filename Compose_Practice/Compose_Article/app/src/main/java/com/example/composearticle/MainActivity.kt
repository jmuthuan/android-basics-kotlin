package com.example.composearticle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composearticle.ui.theme.ComposeArticleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeArticleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting("Android")
                    Article(
                        stringResource(id = R.string.article_title),
                        stringResource(id = R.string.article_brief),
                        stringResource(id = R.string.article_description)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun Article(title: String, brief: String, description: String, modifier: Modifier = Modifier) {
    Column {
        Image(
            painter = painterResource(id = R.drawable.bg_compose_background),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
            )
        ContentArticle(title = title , brief = brief , description = description, modifier = modifier)
    }
}

@Composable
fun ContentArticle(title: String, brief: String, description: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        modifier = Modifier.padding(16.dp),
        fontSize = 24.sp
    )

    Text(text = brief,
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp),
        textAlign = TextAlign.Justify
    )

    Text(
        text = description,
        modifier = Modifier
            .padding(16.dp),
        textAlign = TextAlign.Justify
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeArticleTheme {
        //Greeting("Android")
        Article(
            stringResource(id = R.string.article_title),
            stringResource(id = R.string.article_brief),
            stringResource(id = R.string.article_description)
        )
    }
}
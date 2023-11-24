package com.example.composequadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composequadrant.ui.theme.ComposeQuadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeQuadrantTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting("Android")
                    Quadrant(
                        textTitle = stringResource(id = R.string.text),
                        textDescription = stringResource(id = R.string.text_description),
                        imageTitle = stringResource(id = R.string.image),
                        imageDescription = stringResource(id = R.string.image_description),
                        rowTitle = stringResource(id = R.string.row),
                        rowDescription = stringResource(id = R.string.row_description),
                        columnTitle = stringResource(id = R.string.column),
                        columnDescription = stringResource(id = R.string.column_description)
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
fun SingleQuadrant(
    title: String,
    description: String,
    color: Color,
    modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
            )
        Text(
            text = description,
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
fun Quadrant(textTitle: String,
             textDescription: String,
             imageTitle: String,
             imageDescription: String,
             rowTitle: String, 
             rowDescription: String,
             columnTitle: String,
             columnDescription: String,
             modifier: Modifier = Modifier) {
    Column(Modifier.fillMaxWidth()){
        Row (Modifier.weight(1F)){
            SingleQuadrant(
                title = textTitle,
                description = textDescription,
                color = colorResource(id = R.color.quadrant1),
                modifier = Modifier.weight(1F))
            SingleQuadrant(
                title = imageTitle,
                description = imageDescription,
                color = colorResource(id = R.color.quadrant2),
                modifier = Modifier.weight(1F))
        }
        Row (Modifier.weight(1F)) {
            SingleQuadrant(
                title = rowTitle,
                description = rowDescription,
                color = colorResource(id = R.color.quadrant3),
                modifier = Modifier.weight(1F))
            SingleQuadrant(
                title = columnTitle,
                description = columnDescription,
                color = colorResource(id = R.color.quadrant4),
                modifier = Modifier.weight(1F))
        }
    }
    
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeQuadrantTheme {
        //Greeting("Android")
        Quadrant(
            textTitle = stringResource(id = R.string.text),
            textDescription = stringResource(id = R.string.text_description),
            imageTitle = stringResource(id = R.string.image),
            imageDescription = stringResource(id = R.string.image_description),
            rowTitle = stringResource(id = R.string.row),
            rowDescription = stringResource(id = R.string.row_description),
            columnTitle = stringResource(id = R.string.column),
            columnDescription = stringResource(id = R.string.column_description),
            modifier = Modifier.fillMaxHeight(1.0F)
        )
    }
}
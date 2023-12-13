package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceAppLayout()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceAppLayout() {
    var pointer by remember { mutableStateOf(1) }

    var image: Int
    var title: Int
    var name: Int

    when(pointer){
        1 -> {
            image = R.drawable.elti_meshau
            title = R.string.artwork_1
            name = R.string.artist_1
        }
        2 -> {
            image = R.drawable.jessica_lewis
            title = R.string.artwork_2
            name = R.string.artist_2
        }
        3 -> {
            image = R.drawable.markus_spiske
            title = R.string.artwork_3
            name = R.string.artist_3
        }
        4 -> {
            image = R.drawable.anthony
            title = R.string.artwork_4
            name = R.string.artist_4
        }
        else -> {
            image = R.drawable.kevin_ku
            title = R.string.artwork_5
            name = R.string.artist_5
        }
    }
    val maxPointerValue = 5
    val minPointerValue = 1

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.background_app))
            .padding(48.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top)
    {
        ArtworkWall(artImage = image, artDescription = title)
        
        Spacer(modifier = Modifier.height(24.dp))
        ArtworkDescription(title = title, artist = name)
        
        Spacer(modifier = Modifier.height(24.dp))
        DisplayController(
            nextHandler = { if (pointer < maxPointerValue) pointer ++ else pointer },
            previousHandler = { if (pointer > minPointerValue) pointer-- else pointer }
        )
    }
}

@Composable
fun ArtworkWall(
    @DrawableRes artImage: Int,
    @StringRes artDescription: Int,
    modifier: Modifier = Modifier) {

    Image(
        painter = painterResource(id = artImage),
        contentDescription = stringResource(id = artDescription),
        modifier = modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.background_image))
            .border(
                width = 2.dp,
                color = colorResource(id = R.color.border_image),
                shape = RectangleShape
            )
            .padding(20.dp)
            .height(400.dp)
    )
}

@Composable
fun ArtworkDescription(
    @StringRes title: Int,
    @StringRes artist: Int,
    modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = colorResource(id = R.color.background_image),
                RoundedCornerShape(8.dp)
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = title),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Artist: " + stringResource(id = artist),
            fontSize = 20.sp,
            modifier = modifier.padding(bottom = 8.dp)
        )
    }

}

@Composable
fun DisplayController(
    nextHandler: () -> Unit,
    previousHandler: () -> Unit,
    modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(onClick = previousHandler,
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = colorResource(id = R.color.background_button)
            )
        )
        {
            Text(
                text = "Previous",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .width(64.dp)
            )
        }
        Button(onClick = nextHandler,
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = colorResource(id = R.color.background_button)
            )
        )
        {
            Text(
                text = "Next",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.width(64.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceAppLayout()
    }
}
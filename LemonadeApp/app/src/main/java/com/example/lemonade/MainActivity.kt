package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting("Android")
                    LemonadeApp()
                }
            }
        }
    }
}

@Composable
fun LemonadeMaker(modifier: Modifier = Modifier) {
    var step by remember { mutableStateOf(1) }
    var squeezeCounter = 0
    var squeezeLimit = (2..4).random()

    val imageResource = when(step){
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val textResource = when(step){
        1 -> R.string.tree_tag
        2 -> R.string.lemon_tag
        3 -> R.string.lemonade_tag
        else -> R.string.emplty_glass_tag
    }

    val imageContentResource = when(step){
        1 -> R.string.tree_content_description
        2 -> R.string.lemon_content_description
        3 -> R.string.glass_lemonade_content_description
        else -> R.string.empty_glass_content_description
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier
                .background(color = colorResource(id = R.color.background_button),
                    RoundedCornerShape(16.dp))
                .padding(24.dp),

            onClick = {
            if(step == 2){
                squeezeCounter++
                if(squeezeCounter == squeezeLimit){
                    squeezeCounter = 0
                    squeezeLimit = (2..4).random()
                    step = stepHandler(step)
                }
            } else{step = stepHandler(step)}
        }) {
            Image(painter = painterResource(id = imageResource),
                contentDescription = stringResource(id = imageContentResource)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = stringResource(id = textResource), fontSize = 18.sp )
    }

}

fun stepHandler(actualStep: Int) : Int {
    return when(actualStep){
        1 -> 2
        2 -> 3
        3 -> 4
        else -> 1
    }
}


@Preview (showBackground = true)
@Composable
fun LemonadeApp() {
    LemonadeMaker(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center))

}
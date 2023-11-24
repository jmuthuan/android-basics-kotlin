package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting("Android")
                    BusinessCard()
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
fun BusinessCard() {
    Column(
        Modifier
            .background(colorResource(id = R.color.background_card))
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        PersonalInformation()
        ContactInformation()
    }
}


@Composable
fun PersonalInformation(modifier: Modifier = Modifier) {
    Column(
        Modifier
            .fillMaxHeight(0.8f)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Image(
            painter = painterResource(id = R.drawable.personal_picture),
            contentDescription = "personal profile picture",
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(20.dp))

        )
        Text(
            text = stringResource(R.string.profile_name),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 24.dp, bottom = 16.dp)
        )
        Text(
            text = stringResource(R.string.profile_title),
            fontSize = 20.sp,
            fontStyle = FontStyle.Italic
        )
    }
}

@Composable
fun ContactInformation(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 80.dp)

        ) {
        Row(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_phone_android_24),
                contentDescription = "phone number contact",
                modifier = Modifier.padding(end = 16.dp))
            Text(
                text = stringResource(R.string.phone_contact),
                fontSize = 20.sp
            )
        }
        Row(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)) {
            Icon(painter = painterResource(id = R.drawable.baseline_people_alt_24),
                contentDescription = "social media contact",
                modifier = Modifier.padding(end = 16.dp))
            Text(
                text = stringResource(R.string.social_media_contact),
                fontSize = 20.sp
                )
        }
        Row(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)) {
            Icon(painter = painterResource(id = R.drawable.baseline_email_24),
                contentDescription = "email contact",
                modifier = Modifier.padding(end = 16.dp))
            Text(
                text = stringResource(R.string.email_contact),
                fontSize = 20.sp
                )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BusinessCardTheme {
        //Greeting("Android")
        BusinessCard()
    }
}
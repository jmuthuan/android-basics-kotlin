package com.example.superheroesapp

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroesapp.model.Hero
import com.example.superheroesapp.model.HeroesRepository

@Composable
fun CardHero(
    hero: Hero,
    modifier: Modifier = Modifier
) {
    Card (
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(id = R.dimen.card_elevation)
        ),
        modifier = modifier
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_medium))
            .height(dimensionResource(id = R.dimen.item_height))
        ) {
            HeroInformation(
                name = hero.name,
                description = hero.description,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_medium)))
            HeroImage(imageResourceId = hero.imageResourceId)
        }
    }
}

@Composable
fun HeroInformation(
    @StringRes name: Int,
    @StringRes description:Int,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier.fillMaxHeight()) {
        Text(
            text = stringResource(id = name),
            style = MaterialTheme.typography.displaySmall
        )
        Text(
            text = stringResource(id = description),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun HeroImage(
    @DrawableRes imageResourceId: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = imageResourceId),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(dimensionResource(R.dimen.image_size))
            .clip(MaterialTheme.shapes.small)
    )
}

@Composable
fun HeroesList(
    modifier: Modifier = Modifier,
    padding: PaddingValues = PaddingValues(0.dp)
) {
        LazyColumn(
            contentPadding = padding,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(HeroesRepository.heroes) {
                CardHero(
                    hero = it,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                )
            }
        }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperheroesTopBarApp(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.displayLarge
            )
        },
        modifier = modifier
    )
}


@Preview(showBackground = true)
@Composable
fun CardHeroPreview() {
    CardHero(hero =
    Hero(
        name = R.string.hero1,
        description = R.string.description1,
        imageResourceId = R.drawable.android_superhero1
    ),
        modifier = Modifier
            .fillMaxWidth()
            //.padding(dimensionResource(id = R.dimen.padding_medium))
    )
}

//@Preview(showBackground = true)
//@Composable
//fun HeroesListPreview(){
//    HeroesList()
//}
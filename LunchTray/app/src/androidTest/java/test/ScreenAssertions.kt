package test

import androidx.navigation.NavController
import org.junit.Assert.assertEquals

fun NavController.assertCurrentScreen(expectedRouteName: String) {
    assertEquals(expectedRouteName, currentBackStackEntry?.destination?.route)
}
package test

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.lunchtray.LunchTrayApp
import com.example.lunchtray.LunchTrayScreen
import com.example.lunchtray.R
import com.example.lunchtray.datasource.DataSource
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LunchTrayScreenNavigationTest {
    @get: Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupLunchTrayNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            LunchTrayApp(navController = navController)
        }
    }

    @Test
    fun lunchTrayNavHost_verifyStartDestination() {
        navController.assertCurrentScreen(LunchTrayScreen.Start.name)
    }

    @Test
    fun lunchTrayNavHost_verifyBackNavigationNotShownOnStartOrderScreen() {
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).assertDoesNotExist()
    }
//**************************************************
    @Test
    fun lunchTrayNavHost_clickStartOrderButton_navigateToEntreeMenuScreen() {
        navigateToEntreeMenuScreen()
        navController.assertCurrentScreen(LunchTrayScreen.Entree.name)
    }

    @Test
    fun lunchTrayNavHost_clickCancelButtonOnEntreeMenuScreen_navigateToStartScreen() {
        navigateToEntreeMenuScreen()
        clickOnCancelButton()
        navController.assertCurrentScreen(LunchTrayScreen.Start.name)
    }

    @Test
    fun lunchTrayNavHost_clickUpButtonOnEntreeMenuScreen_navigateToStartScreen() {
        navigateToEntreeMenuScreen()
        clickOnUpButton()
        navController.assertCurrentScreen(LunchTrayScreen.Start.name)
    }

//**************************************************
    @Test
    fun lunchTrayNanHost_clickOnNextButtonOnEntreeMenuScreen_navigateToSideMenuScreen() {
        navigateToSideDishMenuScreen()
        navController.assertCurrentScreen(LunchTrayScreen.SideDish.name)
    }

    @Test
    fun lunchTrayNavHost_clickCancelButtonOnSideMenuScreen_navigateToStartScreen() {
        navigateToSideDishMenuScreen()
        clickOnCancelButton()
        navController.assertCurrentScreen(LunchTrayScreen.Start.name)
    }

    @Test
    fun lunchTrayNavHost_clickUpButtonOnSideMenuScreen_navigateToEntreeScreen() {
        navigateToSideDishMenuScreen()
        clickOnUpButton()
        navController.assertCurrentScreen(LunchTrayScreen.Entree.name)
    }

//**************************************************
    @Test
    fun lunchTrayNanHost_clickOnNextButtonOnSideMenuScreen_navigateToAccompanimentMenuScreen() {
        navigateToAccompanimentMenuScreen()
        navController.assertCurrentScreen(LunchTrayScreen.Accompaniment.name)
    }

    @Test
    fun lunchTrayNavHost_clickCancelButtonOnAccompanimentMenuScreen_navigateToStartScreen() {
        navigateToAccompanimentMenuScreen()
        clickOnCancelButton()
        navController.assertCurrentScreen(LunchTrayScreen.Start.name)
    }

    @Test
    fun lunchTrayNavHost_clickUpButtonOnAccompanimentMenuScreen_navigateToSideMenuScreen() {
        navigateToAccompanimentMenuScreen()
        clickOnUpButton()
        navController.assertCurrentScreen(LunchTrayScreen.SideDish.name)
    }

//**************************************************

    @Test
    fun lunchTrayNanHost_clickOnNextButtonOnAccompanimentMenuScreen_navigateToCheckOutScreen() {}

    @Test
    fun lunchTrayNavHost_clickCancelButtonOnCheckOutScreen_navigateToStartScreen() {
        navigateToCheckOutScreen()
        clickOnCancelButton()
        navController.assertCurrentScreen(LunchTrayScreen.Start.name)
    }

    @Test
    fun lunchTrayNavHost_clickUpButtonOnCheckOutScreen_navigateToAccompanimentScreen() {
        navigateToCheckOutScreen()
        clickOnUpButton()
        navController.assertCurrentScreen(LunchTrayScreen.Accompaniment.name)
    }





    private fun navigateToEntreeMenuScreen() {
        composeTestRule.onNodeWithStringId(R.string.start_order)
            .performClick()
    }

    private fun navigateToSideDishMenuScreen() {
        val nextText = composeTestRule.activity.getString(R.string.next).uppercase()
        navigateToEntreeMenuScreen()
        composeTestRule.onNodeWithText(DataSource.entreeMenuItems[0].name)
            .performClick()
        composeTestRule.onNodeWithText(nextText)
            .performClick()
    }

    private fun navigateToAccompanimentMenuScreen() {
        val nextText = composeTestRule.activity.getString(R.string.next).uppercase()
        navigateToSideDishMenuScreen()
        composeTestRule.onNodeWithText(DataSource.sideDishMenuItems[0].description)
            .performClick()
        composeTestRule.onNodeWithText(nextText)
            .performClick()
    }

    private fun navigateToCheckOutScreen() {
        val nextText = composeTestRule.activity.getString(R.string.next).uppercase()
        navigateToAccompanimentMenuScreen()
        composeTestRule.onNodeWithText(DataSource.accompanimentMenuItems[0].name)
            .performClick()
        composeTestRule.onNodeWithText(nextText)
            .performClick()
    }

    private fun clickOnCancelButton() {
        val cancelText = composeTestRule.activity.getString(R.string.cancel)
        composeTestRule.onNodeWithText(cancelText, ignoreCase = true)
            .performClick()
    }

    private fun clickOnUpButton() {
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText)
            .performClick()
    }
}
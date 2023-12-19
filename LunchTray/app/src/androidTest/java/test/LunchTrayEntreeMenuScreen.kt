package test

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.lunchtray.R
import com.example.lunchtray.datasource.DataSource
import com.example.lunchtray.ui.EntreeMenuScreen
import org.junit.Rule
import org.junit.Test

class LunchTrayEntreeMenuScreen {
    @get: Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun selectEntreeMenuScreen_verifyContent() {
        val options = DataSource.entreeMenuItems

        composeTestRule.setContent {
            EntreeMenuScreen(
                options = options,
                onCancelButtonClicked = { },
                onNextButtonClicked = { },
                onSelectionChanged = { }
            )
        }
        //This test also includes SideDish and AccompanimentMenu screens, due to all these use
        //BaseMenu Composable component

        //Test visibility of all options
        options.forEach { menu ->
            composeTestRule.onNodeWithText(menu.name).assertIsDisplayed()
            composeTestRule.onNodeWithText(menu.description).assertIsDisplayed()
        }
        //Test Cancel Button displayed
        val cancelText = composeTestRule.activity.getString(R.string.cancel)
        composeTestRule.onNodeWithText(cancelText, ignoreCase = true)
            .assertIsDisplayed()
            .assertIsEnabled()

        //Test Next Button Disabled
        val nextText = composeTestRule.activity.getString(R.string.next)
        composeTestRule.onNodeWithText(nextText, ignoreCase = true)
            .assertIsDisplayed()
            .assertIsNotEnabled()
    }
}

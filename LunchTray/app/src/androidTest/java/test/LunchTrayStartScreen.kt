package test

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.lunchtray.R
import com.example.lunchtray.ui.StartOrderScreen
import org.junit.Rule
import org.junit.Test

class LunchTrayStartScreen {
    @get: Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun selectStartScreen_verifyContent() {
        composeTestRule.setContent {
            StartOrderScreen(onStartOrderButtonClicked = { })
        }

        //Test Start Order Button
        composeTestRule.onNodeWithStringId(R.string.start_order).assertIsDisplayed()
    }

}
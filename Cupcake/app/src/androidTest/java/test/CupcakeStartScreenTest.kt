package test

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.cupcake.R
import com.example.cupcake.data.DataSource
import com.example.cupcake.ui.StartOrderScreen
import org.junit.Rule
import org.junit.Test

class CupcakeStartScreenTest {
    @get: Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun selectStartScreen_verifyContent() {

        val quantityOptions = DataSource.quantityOptions

        composeTestRule.setContent {
            StartOrderScreen(
                quantityOptions = quantityOptions,
                onNextButtonClicked = {}
            )
        }

        //Image displayed
        composeTestRule.onNodeWithTag("Cupcake Image").assertIsDisplayed()

        //Order Text displayed
        composeTestRule.onNodeWithStringId(R.string.order_cupcakes).assertIsDisplayed()

        //Buttons options displayed
        quantityOptions.forEach { option ->
            composeTestRule.onNodeWithStringId(option.first).assertIsDisplayed()
        }
    }
}
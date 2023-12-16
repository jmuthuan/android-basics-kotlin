package test

import android.icu.util.Calendar
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.cupcake.R
import com.example.cupcake.data.OrderUiState
import com.example.cupcake.ui.OrderSummaryScreen
import org.junit.Rule
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Locale

class CupcakeSummaryScreenTest {
    @get: Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val uiState = OrderUiState(
        quantity = 6,
        date = getFormattedDate(),
        flavor = "Vanilla",
        price = "$150"
    )
    @Test
    fun selectSummaryScreen_verifyContent() {
        composeTestRule.setContent {
            OrderSummaryScreen(
                orderUiState = uiState,
                onCancelButtonClicked = { /*TODO*/ },
                onSendButtonClicked = { subject: String, summary: String -> }
            )
        }

        //Text QUANTITY, FLAVOR, PICKUP DATE displayed
        composeTestRule.onNodeWithText("QUANTITY").assertIsDisplayed()
        composeTestRule.onNodeWithText("FLAVOR").assertIsDisplayed()
        composeTestRule.onNodeWithText("PICKUP DATE").assertIsDisplayed()

        //Subtotal is displayed
        val subtotal = composeTestRule.activity.getString(R.string.subtotal_price, uiState.price)
        composeTestRule.onNodeWithText(subtotal).assertIsDisplayed()

        //Test Data is displayed
        composeTestRule.onNodeWithText(uiState.flavor).assertIsDisplayed()
        composeTestRule.onNodeWithText("${uiState.quantity} cupcakes").assertIsDisplayed()
        composeTestRule.onNodeWithText(uiState.date).assertIsDisplayed()

        //Test buttons are displayed
        composeTestRule.onNodeWithStringId(R.string.cancel)
        composeTestRule.onNodeWithStringId(R.string.send)
    }


    private fun getFormattedDate(): String {
        val calendar = Calendar.getInstance()
        calendar.add(java.util.Calendar.DATE, 1)

        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        return formatter.format(calendar.time)
    }

}
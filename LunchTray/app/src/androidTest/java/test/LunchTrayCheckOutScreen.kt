package test

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.lunchtray.datasource.DataSource
import com.example.lunchtray.model.OrderUiState
import com.example.lunchtray.ui.CheckoutScreen
import org.junit.Rule
import org.junit.Test
import com.example.lunchtray.R
import java.text.NumberFormat

class LunchTrayCheckOutScreen {
    @get: Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val uiState = OrderUiState(
        entree = DataSource.entreeMenuItems[0],
        sideDish = DataSource.sideDishMenuItems[0],
        accompaniment = DataSource.accompanimentMenuItems[0],
        itemTotalPrice = 15.00,
        orderTax = 1.00,
        orderTotalPrice = 16.00
    )

    @Test
    fun selectCheckOutScreen_verifyContent() {
        composeTestRule.setContent {
            CheckoutScreen(
                orderUiState = uiState,
                onNextButtonClicked = { },
                onCancelButtonClicked = { })
        }

        //Test buttons Cancel and Submits displayed and Enabled
        val cancelText = composeTestRule.activity.getString(R.string.cancel)
        composeTestRule.onNodeWithText(cancelText, ignoreCase = true)
            .assertIsDisplayed()
            .assertIsEnabled()
        val submitText = composeTestRule.activity.getString(R.string.submit)
        composeTestRule.onNodeWithText(submitText, ignoreCase = true)
            .assertIsDisplayed()
            .assertIsEnabled()

        //Test title "Order Summary"
        composeTestRule.onNodeWithStringId(R.string.order_summary).assertIsDisplayed()
        //Test Menus details displayed
        composeTestRule.onNodeWithText(uiState.entree?.name!!).assertIsDisplayed()
        composeTestRule.onNodeWithText(uiState.sideDish?.name!!).assertIsDisplayed()
        composeTestRule.onNodeWithText(uiState.accompaniment?.name!!).assertIsDisplayed()

        //Test Subtotal, Tax and Total displayed
        val subtotal = composeTestRule.activity.getString(
            R.string.subtotal,
            formattedNumber(uiState.itemTotalPrice)
        )
        composeTestRule.onNodeWithText(subtotal).assertIsDisplayed()

        val tax = composeTestRule.activity.getString(
            R.string.tax,
            formattedNumber(uiState.orderTax)
        )
        composeTestRule.onNodeWithText(tax).assertIsDisplayed()

        val total = composeTestRule.activity.getString(
            R.string.total,
            formattedNumber(uiState.orderTotalPrice)
        )
        composeTestRule.onNodeWithText(total).assertIsDisplayed()


    }

    fun formattedNumber(price: Double): String {
        return NumberFormat.getCurrencyInstance().format(price)
    }
}
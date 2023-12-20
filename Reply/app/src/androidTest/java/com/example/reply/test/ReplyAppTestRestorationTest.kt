package com.example.reply.test

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.assertAny
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasAnyDescendant
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.reply.R
import com.example.reply.data.local.LocalEmailsDataProvider
import com.example.reply.ui.ReplyApp
import org.junit.Rule
import org.junit.Test

class ReplyAppTestRestorationTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    @TestCompactWidth
    fun compactDevice_selectedEmailEmailRetained_afterConfigChange() {
        // Setup compact window
        val stateRestorationTest = StateRestorationTester(composeTestRule)

        stateRestorationTest.setContent {
            ReplyApp(windowSize = WindowWidthSizeClass.Compact)
        }

        // Given third email is displayed
        val emailBody = composeTestRule.activity.getString(
            LocalEmailsDataProvider.allEmails[2].body
        )
        composeTestRule.onNodeWithText(emailBody).assertIsDisplayed()

        // Open detailed page
        val emailSubject = composeTestRule.activity.getString(
            LocalEmailsDataProvider.allEmails[2].subject
        )
        composeTestRule.onNodeWithText(emailSubject).performClick()

        // Verify that it shows the detailed screen for the correct email
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.navigation_back)
            .assertExists()
        composeTestRule.onNodeWithText(emailBody)
            .assertExists()

        // Simulate a config change
        stateRestorationTest.emulateSavedInstanceStateRestore()

        // Verify that it still shows the detailed screen for the same email
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.navigation_back)
            .assertExists()
        composeTestRule.onNodeWithText(emailBody)
            .assertExists()
    }

    @Test
    @TestExpandedWidth
    fun expandedDevice_selectedEmailEmailRetained_afterConfigChange() {
        //Set up expanded window
        val stateRestorationTester = StateRestorationTester(composeTestRule)
        stateRestorationTester.setContent {
            ReplyApp(windowSize = WindowWidthSizeClass.Expanded)
        }

        // Given third email is displayed
        val emailBody = composeTestRule.activity.getString(
            LocalEmailsDataProvider.allEmails[2].body
        )
        composeTestRule.onNodeWithText(emailBody)
            .assertIsDisplayed()

        // Select third email
        val emailSubject = composeTestRule.activity.getString(
            LocalEmailsDataProvider.allEmails[2].subject
        )
        composeTestRule.onNodeWithText(emailSubject).performClick()

        // Verify that third email is displayed on the details screen
        composeTestRule.onNodeWithTagForStringId(R.string.details_screen).onChildren()
            .assertAny(hasAnyDescendant(hasText(emailBody))
            )

        // Simulate a config change
        stateRestorationTester.emulateSavedInstanceStateRestore()

        // Verify that third email is still displayed on the details screen
        composeTestRule.onNodeWithTagForStringId(R.string.details_screen).onChildren()
            .assertAny(hasAnyDescendant(hasText(emailBody))
            )
    }
}
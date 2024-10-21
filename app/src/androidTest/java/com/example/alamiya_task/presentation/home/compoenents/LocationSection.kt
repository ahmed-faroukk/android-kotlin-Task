
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.alamiya_task.core.extentions.formatDate
import com.example.alamiya_task.presentation.home.components.LocationSection
import org.junit.Rule
import org.junit.Test
import java.time.LocalDate

class LocationSectionTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testNavigateBetweenDays()  {

        // Set the initial date
        val initialDate = LocalDate.now()
        val nextDate = initialDate.plusDays(1)
        val previousDate = initialDate.minusDays(1)

        // Set up the Composable
        composeTestRule.setContent {
            LocationSection(
                onDateChange = { newDate -> /* Handle date change */ },
                address = "Test Address",
            )
        }


        composeTestRule.onNodeWithText(initialDate.formatDate()).assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription("Left Arrow").performClick()
        composeTestRule.onNodeWithText(previousDate.formatDate()).assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription("Right Arrow").performClick()

        composeTestRule.onNodeWithText(initialDate.formatDate()).assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription("Right Arrow").performClick()

        composeTestRule.onNodeWithText(nextDate.formatDate()).assertIsDisplayed()
    }
}


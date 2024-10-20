import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.material3.MaterialTheme
import com.example.alamiya_task.R

@Composable
fun CustomAppText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: Float? = 14f,
    color: Color? = MaterialTheme.colorScheme.onBackground, // Use theme color for text
    fontWeight: FontWeight? = FontWeight.Normal,
    textDecoration: TextDecoration? = null,
    maxLines: Int = 1,
    textOverflow: TextOverflow = TextOverflow.Ellipsis,
    textAlign: TextAlign? = TextAlign.Start,
    softWrap: Boolean = true,
) {
    val defaultFontSize = fontSize?.sp ?: 14.sp
    val defaultFontWeight = fontWeight ?: FontWeight.Normal

    Text(
        text = text,
        color = color ?: MaterialTheme.colorScheme.onBackground, // Updated to use theme color
        fontSize = defaultFontSize,
        fontFamily = FontFamily(
            Font(R.font.roboto_medium) // Replace with your font file name
        ),
        fontWeight = defaultFontWeight,
        textDecoration = textDecoration,
        modifier = modifier // Use the provided modifier
            .wrapContentSize(Alignment.Center),
        textAlign = textAlign ?: TextAlign.Center,
        maxLines = maxLines ?: Int.MAX_VALUE,
        overflow = textOverflow,
        softWrap = softWrap
    )
}

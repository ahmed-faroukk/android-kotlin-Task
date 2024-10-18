import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.alamiya_task.R

@Composable
fun AzanImage() {
    Row (modifier = Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.Center){
        Image(
            painter = painterResource(id = R.drawable.mosque2), // Replace with your image resource
            contentDescription = "Azan Icon",
            modifier = Modifier.size(80.dp) // Set the size of the icon
        )
    }
}

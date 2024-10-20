import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.alamiya_task.R

@Composable
fun AzanImage() {
    Row (modifier = Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.Center){
        CustomAppImage(
            pathImg = R.drawable.mosque2, // Replace with your image resource
            modifier = Modifier.size(80.dp) // Set the size of the icon
        )
    }
}

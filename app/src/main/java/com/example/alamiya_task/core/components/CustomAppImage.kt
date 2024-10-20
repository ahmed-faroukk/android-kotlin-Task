import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomAppImage(
    modifier: Modifier,
    pathImg: Int ,
    sizeHeight: Dp? = 100.dp,
    sizeWidth: Dp? = 100.dp,
    contentScale: ContentScale = ContentScale.Crop
) {

    val painter  = painterResource(id = pathImg)

    Image(
        painter = painter,
        contentDescription = null,
        modifier = modifier.size(sizeWidth ?: 100.dp, sizeHeight ?: 100.dp),
        contentScale = contentScale
    )
}




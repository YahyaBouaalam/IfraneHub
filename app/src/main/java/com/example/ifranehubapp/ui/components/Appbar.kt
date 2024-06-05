package com.example.ifranehubapp.ui.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.twotone.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ifranehubapp.Greeting
import com.example.ifranehubapp.ui.theme.IfraneHubAppTheme

@Composable
fun IfraneHubLogo() {
    Row(modifier = Modifier.wrapContentSize(), verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Outlined.Home,
            contentDescription = "",
            tint = Color(0xFF7c90b3),
            modifier = Modifier
                .size(50.dp)
                .padding(start = 18.dp, end = 3.dp)
        )
        Text(text = "IfraneHub", color = Color(0xFF7c90b3), fontWeight = FontWeight.ExtraBold)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(openDrawer: () -> Unit) {
    TopAppBar(
        title = { IfraneHubLogo() },
        navigationIcon = {
            Icon(
            imageVector = Icons.TwoTone.Menu,
            contentDescription = "",
            tint = Color(0xFF7c90b3),
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
                .padding(start = 18.dp)
                .clickable {
                    openDrawer()
                }
        )}
        )

}

@Preview(showBackground = true)
@Composable
fun AppBarPreview() {
    IfraneHubAppTheme {
        //IfraneHubLogoBig()
    }
}
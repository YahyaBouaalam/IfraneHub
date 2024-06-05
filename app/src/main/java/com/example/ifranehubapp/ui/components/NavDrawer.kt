package com.example.ifranehubapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ifranehubapp.R
import com.example.ifranehubapp.Route

data class DrawerItem(
    val title : String,
    val onClick: () -> Unit
)

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AuthDrawer() {
//    val items = listOf<DrawerItem>(
//        DrawerItem(title = "Account settings", route = Route.Home),
//        DrawerItem(title = "Sign out", route = Route.Home)
//    )
//
//    ModalDrawerSheet {
//        Box(modifier = Modifier
//            .fillMaxWidth()
//            .wrapContentHeight()
//            .padding(bottom = 10.dp)
//            .background(Color(0xFFE7E7E7)),
//            contentAlignment = Alignment.CenterStart
//        ) {
//            Image(
//                painterResource(R.drawable.ic_launcher_background),
//                contentDescription = null,
//                modifier = Modifier
//                    .fillMaxWidth(),
//                contentScale = ContentScale.Crop
//            )
//        }
//
//
//    }
//
//}
//
//@Composable
//fun NotAuthDrawer() {
//
//    val items = listOf<DrawerItem>(
//        DrawerItem(title = "Sign up", route = Route.Signup),
//        DrawerItem(title = "Log in", route = Route.Login)
//    )
//}



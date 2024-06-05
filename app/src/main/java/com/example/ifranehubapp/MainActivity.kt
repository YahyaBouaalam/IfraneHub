package com.example.ifranehubapp

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.ifranehubapp.roomDB.entities.User
import com.example.ifranehubapp.ui.components.AppBar
import com.example.ifranehubapp.ui.components.DrawerItem
import com.example.ifranehubapp.ui.theme.IfraneHubAppTheme
import com.example.ifranehubapp.view.HomeScreen
import com.example.ifranehubapp.view.ListingPage
import com.example.ifranehubapp.view.Login
import com.example.ifranehubapp.view.SignUp
import com.example.ifranehubapp.viewmodel.AtlasApiViewModel
import com.example.ifranehubapp.viewmodel.DBViewmodel
import dagger.hilt.android.AndroidEntryPoint
import io.realm.kotlin.types.RealmInstant
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val dbViewModel: DBViewmodel by viewModels()
    val atlasViewModel: AtlasApiViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IfraneHubAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    AppNav(context = this, dbViewModel = dbViewModel, atlasViewModel = atlasViewModel,
                        login = {
                            atlasViewModel.loggedinuser.observe(this, Observer {
                                if(!it!!.id.equals("-1")) {
                                    dbViewModel.adduser(it)
                                    dbViewModel.setAuthTrue()
                                }
                            })
                    }, signup = {
                            atlasViewModel.newuser.observe(this, Observer {
                                if(!it!!.id.equals("-1")) {
                                    dbViewModel.adduser(it)
                                    dbViewModel.setAuthTrue()
                                }
                            })
                        })
//                    atlasViewModel.loginEmail("badralloul6@gmail.com", "\$2b\$12\$Ue.Z9Pu9phmVUu2P9s1OxeVkdmN3ln2JTDxc6oHneb2P5i3cYLQba")
//                    val user = atlasViewModel.loginresponse
//                    Button(onClick = {
//                       atlasViewModel.signupEmail("m.bouaalam@auidd.ma", "12er3", "yahya")
//                        atlasViewModel.newuser.observe(this, Observer {
//                            if(!it!!.id.equals("-1"))
//                            dbViewModel.adduser(it)
//                        })
////                        dbViewModel.adduser(User(id = user._id, name = user.name, email = user.email, emailVerified = null, image = null, hashedPassword = user.hashedPassword, createdAt = user.createdAt, updatedAt = user.updatedAt))
//                    }) {
//                        Text(text = "ADD TO DB")
//                    }
//                    Greeting(RealmInstant.now().toString())
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "$name",
        modifier = modifier
    )
}

@SuppressLint("SimpleDateFormat")
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IfraneHubAppTheme {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        val offsetDateTime = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(Date())
 Greeting(Date().toString())
    }
}

enum class Route {
    Home,
    Login,
    Signup,
    Listing
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNav(dbViewModel: DBViewmodel, atlasViewModel: AtlasApiViewModel, login :  () -> Unit, signup: () -> Unit, context : Context) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.Home.name
    ) {
        composable(route = Route.Listing.name,
            enterTransition = {
                scaleIntoContainer()
            },
            exitTransition = {
                scaleOutOfContainer(direction = ScaleTransitionDirection.INWARDS)
            },
            popEnterTransition = {
                scaleIntoContainer(direction = ScaleTransitionDirection.OUTWARDS)
            },
            popExitTransition = {
                scaleOutOfContainer()
            }
        ) {
            var user by remember { mutableStateOf(User()) }
            LaunchedEffect(Unit) {
                user = dbViewModel.getUser()
            }
            val listing = atlasViewModel.getListing()!!
            atlasViewModel.findHost(listing.userId)
            ListingPage(listing = listing, host = atlasViewModel.getHost()!!, back = {navController.popBackStack()}, isauth = dbViewModel.loggedIn.value, reserve =
                { start, end, total ->
                    atlasViewModel.makereservation(start = start, end = end, userId = user.id, totalPrice = total)
                },
                context = context
            )
        }
        composable(route = Route.Login.name,
            enterTransition = {
                scaleIntoContainer()
            },
            exitTransition = {
                scaleOutOfContainer(direction = ScaleTransitionDirection.INWARDS)
            },
            popEnterTransition = {
                scaleIntoContainer(direction = ScaleTransitionDirection.OUTWARDS)
            },
            popExitTransition = {
                scaleOutOfContainer()
            }
        ) {
            Login(atlasViewModel, gotosignup = { navController.navigate(Route.Signup.name) },
                login = {
                    login()
                    navController.navigate(Route.Home.name)
                })
        }

        composable(Route.Signup.name,
            enterTransition = {
                scaleIntoContainer()
            },
            exitTransition = {
                scaleOutOfContainer(direction = ScaleTransitionDirection.INWARDS)
            },
            popEnterTransition = {
                scaleIntoContainer(direction = ScaleTransitionDirection.OUTWARDS)
            },
            popExitTransition = {
                scaleOutOfContainer()
            }
        ) {
            SignUp(atlasViewModel = atlasViewModel, gotologin = {navController.navigate(Route.Login.name)},
                signup = {
                    signup()
                    navController.navigate(Route.Home.name)
                })
        }

        composable(route = Route.Home.name,
            enterTransition = {
                scaleIntoContainer()
            },
            exitTransition = {
                scaleOutOfContainer(direction = ScaleTransitionDirection.INWARDS)
            },
            popEnterTransition = {
                scaleIntoContainer(direction = ScaleTransitionDirection.OUTWARDS)
            },
            popExitTransition = {
                scaleOutOfContainer()
            }
        ) {
            var user by remember { mutableStateOf(User()) }
            LaunchedEffect(Unit) {
                user = dbViewModel.getUser()
            }
            LaunchedEffect(Unit) {
                if(dbViewModel.usercount()>0)
                    dbViewModel.setAuthTrue()
            }
            val items = if (dbViewModel.loggedIn.value) listOf(
                DrawerItem(title = "Account settings",onClick = {}),
                DrawerItem(title = "Sign out", onClick = {
                    dbViewModel.removeUser()
                    dbViewModel.setAuthFalse()
                    atlasViewModel.resetuser()
                    navController.navigate(Route.Home.name)
                })
            ) else listOf(
            DrawerItem(title = "Sign up", onClick = {navController.navigate(Route.Signup.name)}),
            DrawerItem(title = "Log in", onClick = {navController.navigate(Route.Login.name)})
        )
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            ModalNavigationDrawer(
                drawerContent = {
                    ModalDrawerSheet {
                        if(dbViewModel.loggedIn.value) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .padding(bottom = 10.dp)
                                    .background(Color(0xFFE7E7E7)),
                                verticalAlignment = Alignment.Bottom,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                    AsyncImage(
                                        model = user.image,
                                        contentDescription = null,
                                        modifier = androidx.compose.ui.Modifier
                                            .size(100.dp)
                                            .padding(end = 10.dp),
                                        contentScale = ContentScale.Crop,
                                        error = painterResource(R.drawable.placeholderprofile)
                                    )
                                    Text(text = "Hello, ${user.name}")
                                }
                        }
                        else {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .padding(bottom = 10.dp)
                                    .background(Color(0xFFE7E7E7)),
                                verticalAlignment = Alignment.Bottom,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Image(
                                    painterResource(R.drawable.placeholderprofile),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(100.dp)
                                        .padding(end = 10.dp),
                                    contentScale = ContentScale.Crop
                                )
                                Text(text = "")
                            }
                        }
                        items.forEachIndexed { index, drawerItem ->
                            NavigationDrawerItem(
                                label = { Text(drawerItem.title) },
                                selected = false,
                                onClick = drawerItem.onClick)
                        }


                    }
                },
                drawerState = drawerState
            ) {
                Scaffold(
                    topBar = {
                        AppBar(openDrawer = {scope.launch { drawerState.open() }})
                    }
                ) {
                    atlasViewModel.findAllListings()
                    HomeScreen(atlasViewModel.listings, gotolisting = {navController.navigate(Route.Listing.name)}, apiViewModel = atlasViewModel)
                }
            }
        }

    }

}

enum class ScaleTransitionDirection {
    INWARDS,
    OUTWARDS
}


fun scaleIntoContainer(
    direction: ScaleTransitionDirection = ScaleTransitionDirection.INWARDS,
    initialScale: Float = if (direction == ScaleTransitionDirection.OUTWARDS) 0.9f else 1.1f
): EnterTransition {
    return scaleIn(
        animationSpec = tween(220, delayMillis = 90),
        initialScale = initialScale
    ) + fadeIn(animationSpec = tween(220, delayMillis = 90))
}

@OptIn(ExperimentalAnimationApi::class)
fun scaleOutOfContainer(
    direction: ScaleTransitionDirection = ScaleTransitionDirection.OUTWARDS,
    targetScale: Float = if (direction == ScaleTransitionDirection.INWARDS) 0.9f else 1.1f
): ExitTransition {
    return scaleOut(
        animationSpec = tween(
            durationMillis = 220,
            delayMillis = 90
        ), targetScale = targetScale
    ) + fadeOut(tween(delayMillis = 90))
}
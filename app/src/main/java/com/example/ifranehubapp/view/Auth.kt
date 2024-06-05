package com.example.ifranehubapp.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ifranehubapp.ui.components.Button1
import com.example.ifranehubapp.ui.components.Button2
import com.example.ifranehubapp.ui.components.EmailTextField
import com.example.ifranehubapp.ui.components.PasswordTextField
import com.example.ifranehubapp.ui.components.RegularTextField
import com.example.ifranehubapp.ui.theme.IfraneHubAppTheme
import com.example.ifranehubapp.viewmodel.AtlasApiViewModel

@Composable
fun IfraneHubLogoBig() {
    Row(modifier = Modifier.wrapContentSize(), verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Outlined.Home,
            contentDescription = "",
            tint = Color(0xFF7c90b3),
            modifier = Modifier
                .size(100.dp)
                .padding(end = 3.dp)
        )
        Text(text = "IfraneHub", color = Color(0xFF7c90b3), fontWeight = FontWeight.ExtraBold
            , style = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.W900,
                fontSize = 36.sp
            )
        )
    }
}
@Composable
fun Login(atlasViewModel: AtlasApiViewModel, gotosignup :() -> Unit, login : () -> Unit) {
    val password = remember { mutableStateOf(TextFieldValue("")) }
    val email = remember { mutableStateOf(TextFieldValue("")) }
    val annotatedText = buildAnnotatedString {
        //append your initial text
        withStyle(
            style = SpanStyle(
                color = Color.Gray,
            )
        ) {
            append("Don't have an account? ")

        }

        //Start of the pushing annotation which you want to color and make them clickable later
        pushStringAnnotation(
            tag = "SignUp",// provide tag which will then be provided when you click the text
            annotation = "SignUp"
        )
        //add text with your different color/style
        withStyle(
            style = SpanStyle(
                color = Color.Blue,
            )
        ) {
            append("Sign Up")
        }
        // when pop is called it means the end of annotation with current tag
        pop()
    }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {

        Spacer(modifier = Modifier.height(100.dp))
        IfraneHubLogoBig()
        Spacer(modifier = Modifier.height(130.dp))
        EmailTextField(email)
        Spacer(modifier = Modifier.height(20.dp))
        PasswordTextField(password)
        Spacer(modifier = Modifier.height(55.dp))
        Button1 {
            atlasViewModel.loginEmail(
                email.value.text, password.value.text
              //  "badralloul6@gmail.com", "\$2b\$12\$Ue.Z9Pu9phmVUu2P9s1OxeVkdmN3ln2JTDxc6oHneb2P5i3cYLQba"
            )
            login()
        }

        Spacer(modifier = Modifier.height(25.dp))

        ClickableText(
            text = annotatedText,
            onClick = { offset ->
                annotatedText.getStringAnnotations(
                    tag = "SignUp",// tag which you used in the buildAnnotatedString
                    start = offset,
                    end = offset)[0].let { gotosignup() }
            }
        )

    }
}

@Composable
fun SignUp(atlasViewModel: AtlasApiViewModel, gotologin :() -> Unit, signup : () -> Unit) {
    val password = remember { mutableStateOf(TextFieldValue("")) }
    val name = remember { mutableStateOf(TextFieldValue("")) }
    val email = remember { mutableStateOf(TextFieldValue("")) }
    val annotatedText = buildAnnotatedString {
        //append your initial text
        withStyle(
            style = SpanStyle(
                color = Color.Gray,
            )
        ) {
            append("Already have an account? ")

        }

        //Start of the pushing annotation which you want to color and make them clickable later
        pushStringAnnotation(
            tag = "LogIn",// provide tag which will then be provided when you click the text
            annotation = "LogIn"
        )
        //add text with your different color/style
        withStyle(
            style = SpanStyle(
                color = Color.Blue,
            )
        ) {
            append("Log In")
        }
        // when pop is called it means the end of annotation with current tag
        pop()
    }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {

        Spacer(modifier = Modifier.height(100.dp))
        IfraneHubLogoBig()
        Spacer(modifier = Modifier.height(70.dp))
        RegularTextField(text = name, label = "Full Name", placeholder = "ex: John Doe")
        Spacer(modifier = Modifier.height(20.dp))
        EmailTextField(email)
        Spacer(modifier = Modifier.height(20.dp))
        PasswordTextField(password)
        Spacer(modifier = Modifier.height(55.dp))
        Button2 {
            atlasViewModel.signupEmail(email.value.text, password.value.text, name.value.text)
            signup()
        }
        Spacer(modifier = Modifier.height(25.dp))

        ClickableText(
            text = annotatedText,
            onClick = { offset ->
                annotatedText.getStringAnnotations(
                    tag = "LogIn",// tag which you used in the buildAnnotatedString
                    start = offset,
                    end = offset)[0].let { gotologin() }
            }
        )

    }
}

@Preview(showBackground = true)
@Composable
fun ButtonPreview() {
    IfraneHubAppTheme {
        //Login(AtlasApiViewModel(), {})
    }
}



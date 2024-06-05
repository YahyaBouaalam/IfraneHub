package com.example.ifranehubapp.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ifranehubapp.ui.theme.IfraneHubAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailTextField(email : MutableState<TextFieldValue>) {
    OutlinedTextField(
        value = email.value,
        leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "emailIcon") },
        onValueChange = {
            email.value = it
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        label = { Text(text = "Email") },
        placeholder = { Text(text = "ex: example@xyz.com") },
        shape = RoundedCornerShape(22.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegularTextField(text : MutableState<TextFieldValue>, label : String, placeholder: String) {
    OutlinedTextField(
        value = text.value,
        leadingIcon = { Icon(imageVector = Icons.Filled.Person, contentDescription = "personIcon") },
        onValueChange = {
            text.value = it
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
        shape = RoundedCornerShape(22.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(password : MutableState<TextFieldValue>) {
    var passwordVisibility by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = password.value,
        onValueChange = {
            password.value = it
        },
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        label = { Text(text = "Password") },
        placeholder = { Text(text = "Enter your password") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Lock,
                contentDescription = "passwordIcon"
            )
        },
        trailingIcon = {
            IconButton(
                onClick = { passwordVisibility = !passwordVisibility },
                content = {
                    Icon(
                        imageVector = if (passwordVisibility) Icons.Filled.Visibility else Icons.Outlined.VisibilityOff,
                        contentDescription = if (passwordVisibility) "hideIcon" else "unhideIcon"
                    )
                }
            )
        },

        isError = password.value.text.isNotEmpty() && password.value.text.length < 3 && ".*\\d.*".toRegex().matches(password.value.text),
        shape = RoundedCornerShape(22.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun FieldPreview() {
    IfraneHubAppTheme {
        val password = remember  { mutableStateOf (TextFieldValue("")) }
        PasswordTextField(password)
    }
}
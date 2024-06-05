package com.example.ifranehubapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.ifranehubapp.api.data.ListingDocument
import com.example.ifranehubapp.api.data.ListingsResponse

@Composable
fun ListingCard(next : () -> Unit, listing: ListingDocument) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                next()
            },
        //elevation = 4.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        AsyncImage(
                model = listing.imageSrc,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .aspectRatio(16f / 9f)
                    .graphicsLayer {
                        clip = true
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    },
                contentScale = ContentScale.Crop
        )
        Text(listing.title, color = Color(0xFF000000), fontWeight = FontWeight.ExtraBold, modifier = Modifier.padding(start = 5.dp))
        Spacer(modifier = Modifier.height(15.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 5.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "MAD${listing.price}", color = Color(0xFF2E2E2E), fontWeight = FontWeight.Light)
            Text(text = "${listing.guestCount} guests", color = Color(0xFF2E2E2E), fontWeight = FontWeight.Light)
        }
        Spacer(modifier = Modifier.height(7.dp))
    }
}
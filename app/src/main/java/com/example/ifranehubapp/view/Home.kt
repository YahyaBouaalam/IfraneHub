package com.example.ifranehubapp.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.ifranehubapp.api.data.ListingDocument
import com.example.ifranehubapp.roomDB.entities.Listing
import com.example.ifranehubapp.ui.components.ListingCard
import com.example.ifranehubapp.viewmodel.AtlasApiViewModel

@Composable
fun HomeScreen(listings : List<ListingDocument>, gotolisting: () -> Unit, apiViewModel: AtlasApiViewModel) {
        LazyColumn(Modifier.background(color = Color(0xFFFFFFFF))) {
            itemsIndexed(items = listings) { index, item ->
                if(index == 0) {
                    Spacer(modifier = Modifier.height(75.dp))
                }
                ListingCard(next =
                {
                    apiViewModel.setListing(
                        Listing(id = item._id ?: "",
                            title = item.title ?: "",
                            description = item.description ?: "",
                            imageSrc = item.imageSrc ?: "",
                            createdAt = item.createdAt ?: "",
                            category = item.category ?: "",
                            roomCount = item.roomCount ?: 0,
                            bathroomCount = item.bathroomCount ?: 0,
                            guestCount = item.guestCount ?: 0,
                            locationValue = item.location ?: "",
                            userId = item.userId ?: "",
                            name = "",
                            price = item.price)
                    )
                    gotolisting()
                }
                    , listing = item
                )
            }
        }
}
package com.example.ifranehubapp.view

import android.content.Context
import android.icu.text.ListFormatter.Width
import android.widget.CalendarView
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.twotone.Close
import androidx.compose.material.icons.twotone.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ifranehubapp.R
import com.example.ifranehubapp.api.data.HostData
import com.example.ifranehubapp.roomDB.entities.Listing
import com.example.ifranehubapp.roomDB.entities.User
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListingPage(listing : Listing, host: User, back : ()-> Unit, isauth: Boolean, reserve: (Long, Long, Int) -> Unit , context : Context) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val state = rememberDateRangePickerState()

    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally)
    {

        AsyncImage(
            model = listing.imageSrc,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp)
                .aspectRatio(16f / 9f)
                .graphicsLayer {
                    clip = true
                    //shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                },
            contentScale = ContentScale.Crop
        )
        Text(listing.title, color = Color(0xFF000000), fontWeight = FontWeight.ExtraBold, modifier = Modifier.padding(horizontal = 5.dp, vertical = 10.dp), fontSize = 24.sp)
        Spacer(modifier = Modifier.height(20.dp))
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, bottom = 12.dp)
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically)
        {
            AsyncImage(
                model = host.image,
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .size(50.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Column {
                Text(text = host.name, fontWeight = FontWeight.Bold)
                Text("Host")
            }
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 12.dp, bottom = 12.dp)
        )
        Text("${listing.guestCount} guests - ${listing.roomCount} rooms - ${listing.bathroomCount} bathrooms")
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 12.dp, bottom = 12.dp)
        )
        Text(listing.description, fontWeight = FontWeight.W600, modifier = Modifier.padding(start = 15.dp))
    }
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally)
    {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically)
        {
            Box(modifier = Modifier
                .padding(15.dp)
                .wrapContentSize()
                .background(
                    color = Color(0xFFFFFFFF),
                    shape = CircleShape // Use CircleShape to make the box circular
                )) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "",
                    tint = Color(0xFF2E2E2E),
                    modifier = Modifier
                        .padding(5.dp)
                        .width(25.dp)
                        .height(25.dp)
                        .clickable {
                            back()
                        }
                )

            }

        }

    }
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.CenterHorizontally)
    {
        if(showBottomSheet) {
            ModalBottomSheet(onDismissRequest = { showBottomSheet = false }) {
                DateRangePicker(state = state,
                    title = {},
                    headline = {})
            }
        }
        HorizontalDivider(Modifier.width(240.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp, top = 4.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){

            Text("MAD${listing.price}/Night", modifier = Modifier
                .padding(start = 16.dp))
            Text(if(state.selectedStartDateMillis!=null && state.selectedEndDateMillis!=null) "MAD${listing.price*getDaysBetweenDates(state.selectedStartDateMillis!!, state.selectedEndDateMillis!!)} Total" else "MAD-- Total", modifier = Modifier
                .padding(end = 16.dp),
                fontWeight = FontWeight.Bold
            )
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {

                Text(text = if (state.selectedStartDateMillis != null && state.selectedEndDateMillis != null) "${getFormattedDate(state.selectedStartDateMillis!!)} - ${getFormattedDate(state.selectedEndDateMillis!!)}" else "Check-in - Check-out",
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .clickable { showBottomSheet = true },
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(textDecoration = TextDecoration.Underline)

                )
            Button(onClick = {
                if(state.selectedStartDateMillis!=null && state.selectedEndDateMillis!=null) {
                    reserve(state.selectedStartDateMillis!!, state.selectedEndDateMillis!!, listing.price*getDaysBetweenDates(state.selectedStartDateMillis!!, state.selectedEndDateMillis!!))
                    Toast.makeText(context, "Reservation Request Sent!", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(context, "choose check-in/check-out dates first", Toast.LENGTH_SHORT).show()
                }
            }, enabled = isauth, modifier = Modifier.padding(end = 16.dp)) {
                Text("Reserve")
            }
        }
    }
}

fun getFormattedDate(timeInMillis: Long): String{
    val calender = Calendar.getInstance()
    calender.timeInMillis = timeInMillis
    val dateFormat = SimpleDateFormat("dd/MM/yyyy")
    return dateFormat.format(calender.timeInMillis)
}

fun getDaysBetweenDates(startDateMillis: Long, endDateMillis: Long): Int {
    val startDate = Date(startDateMillis)
    val endDate = Date(endDateMillis)

    // Calculate the difference in milliseconds
    val difference = endDate.time - startDate.time

    // Convert the difference to days and return as an Int
    return (difference / (24 * 60 * 60 * 1000)).toInt()
}

package com.example.ticketbookingapp.Activities.SearchResult

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.ticketbookingapp.Domain.FlightModel
import com.example.ticketbookingapp.R
import com.example.ticketbookingapp.ViewModel.MainViewModel

@Composable
fun ItemListScreen(
    from: String,
    to: String,
    viewModel: MainViewModel,
    onBackClick: () -> Unit
) {
    val items by viewModel.loadFiltered(from, to).observeAsState(emptyList())
    var isLoading by remember { mutableStateOf(true) }

    // Cập nhật trạng thái loading
    if (items.isNotEmpty()) {
        isLoading = false
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.darkPurple2))
            .padding(top = 36.dp, start = 16.dp, end = 16.dp)
    ) {
        val (backBtn, headerTitle) = createRefs()

        Image(
            painter = painterResource(R.drawable.back),
            contentDescription = "Back button",
            modifier = Modifier
                .clickable { onBackClick() }
                .constrainAs(backBtn) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )

        Text(
            text = "Search Result",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier
                .padding(start = 8.dp)
                .constrainAs(headerTitle) {
                    start.linkTo(backBtn.end, margin = 8.dp)
                    top.linkTo(backBtn.top)
                    bottom.linkTo(backBtn.bottom)
                }
        )
    }

    // Hiển thị giao diện
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            isLoading -> {
                CircularProgressIndicator()
            }

            items.isEmpty() -> {
                Text(
                    text = "No flights found for $from to $to",
                    color = Color.White,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
            }

            else -> {
                LazyColumn(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 60.dp)
                ) {
                    itemsIndexed(items as List<FlightModel>) { index, item ->
                        FlightItem(item = item, index = index)
                    }
                }
            }
        }
    }
}
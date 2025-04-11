package com.example.ticketbookingapp.Activities.TicketDetail

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.ticketbookingapp.Activities.Splash.StatusTopBarColor
import com.example.ticketbookingapp.Domain.FlightModel

class TicketDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val flight = intent.getSerializableExtra("flight") as FlightModel

        setContent {
            StatusTopBarColor()

            TicketDetailScreen(
                flight = flight,
                onBackClick = { finish() },
                onDownloadTicketClick = {
                }
            )
        }
    }
}
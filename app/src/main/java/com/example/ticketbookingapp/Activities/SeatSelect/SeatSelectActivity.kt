package com.example.ticketbookingapp.Activities.SeatSelect

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.ticketbookingapp.Activities.Splash.StatusTopBarColor
import com.example.ticketbookingapp.Activities.TicketDetail.TicketDetailActivity
import com.example.ticketbookingapp.Domain.FlightModel

class SeatSelectActivity : AppCompatActivity() {
    private lateinit var flight: FlightModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        flight = intent.getSerializableExtra("flight") as FlightModel

        setContent {
            StatusTopBarColor()

            SeatListScreen(
                flight = flight,
                onBackClick = {
                    finish()
                },
                onConfirm = { updatedFlight, selectedSeats, totalPrice ->
                    val intent = Intent(this, TicketDetailActivity::class.java).apply {
                        putExtra("flight", updatedFlight)
                        putExtra("selectedSeats", selectedSeats)
                        putExtra("totalPrice", totalPrice)
                    }
                    startActivity(intent)
                }
            )
        }
    }
}
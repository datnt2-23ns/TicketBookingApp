package com.example.ticketbookingapp.Activities.Splash

import androidx.compose.foundation.background
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ticketbookingapp.R

@Composable
@Preview
fun GradientButton(
    onClick: () -> Unit = {},
    text: String = "Get Started",
    padding: Int = 0
) {
    val interactionSource = remember { MutableInteractionSource() }

    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding.dp)
            .indication(interactionSource, null), // loại bỏ hiệu ứng focus (viền xanh)
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(contentColor = Color.Transparent),
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
        interactionSource = interactionSource,
        contentPadding = PaddingValues(0.dp) // loại bỏ padding mặc định để gradient đẹp hơn
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            colorResource(R.color.purple),
                            colorResource(R.color.pink)
                        )
                    ),
                    shape = RoundedCornerShape(50.dp)
                )
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
}


//package com.example.ticketbookingapp.Activities.Splash
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.Button
//import androidx.compose.material.ButtonDefaults
//import androidx.compose.material.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.ticketbookingapp.R
//
//@Composable
//@Preview
//fun GradientButton(
//    onClick: () -> Unit = {},
//    text: String = "Get Started",
//    padding: Int = 0
//) {
//    Button(
//        onClick = onClick,
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(padding.dp),
//        shape = RoundedCornerShape(16.dp),
//        colors = ButtonDefaults.buttonColors(contentColor = Color.Transparent)
//    ) {
//        Box(
//            modifier = Modifier
//                .background(
//                    brush = Brush.linearGradient(
//                        colors = listOf(
//                            colorResource(R.color.purple),
//                            colorResource(R.color.pink)
//                        )
//                    ),
//                    shape = RoundedCornerShape(50.dp)
//                )
//                .fillMaxWidth()
//                .padding(vertical = 12.dp),
//            contentAlignment = Alignment.Center
//        ) {
//            Text(
//                text = text,
//                color = Color.White,
//                fontWeight = FontWeight.Bold,
//                fontSize = 18.sp
//            )
//        }
//    }
//}
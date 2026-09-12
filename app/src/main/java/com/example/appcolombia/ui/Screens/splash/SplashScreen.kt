package com.example.appcolombia.ui.Screens.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcolombia.ui.theme.ColombiaBlue
import com.example.appcolombia.ui.theme.ColombiaYellow

@Composable
fun SplashScreen() {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "🇨🇴",
            fontSize = 80.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "COLOMBIA",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = ColombiaBlue
        )

        Text(
            text = "EXPLORER",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = ColombiaYellow
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Descubre Colombia a tu manera",
            fontSize = 16.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(32.dp))

        CircularProgressIndicator(
            color = ColombiaYellow
        )
    }
}
package com.example.appcolombia.ui.Screens.splash

import com.example.appcolombia.data.model.Department

import androidx.compose.foundation.clickable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val ColombiaBlue = Color(0xFF003893)
private val ColombiaYellow = Color(0xFFFFD100)
private val BackgroundColor = Color(0xFFF8F9FC)

@Composable
fun DepartmentDetailScreen(
    department: Department,
    onBackClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(20.dp)
    ) {

        // Encabezado

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "‹",
                fontSize = 36.sp,
                color = ColombiaBlue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    onBackClick()
                }
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Departamento",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = ColombiaBlue
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Tarjeta principal

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = ColombiaBlue
            )
        ) {

            Box(
                modifier = Modifier.fillMaxSize()
            ) {

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(20.dp)
                ) {

                    Text(
                        text = department.name.uppercase(),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = ColombiaYellow
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = department.name,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Text(
                        text = "Departamento de Colombia 🇨🇴",
                        fontSize = 14.sp,
                        color = Color.White
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Información",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = ColombiaBlue
        )

        Spacer(modifier = Modifier.height(12.dp))

        InfoCard(
            title = "ID",
            value = department.id.toString()
        )

        Spacer(modifier = Modifier.height(10.dp))

        InfoCard(
            title = "Municipios",
            value = department.municipalities?.toString() ?: "No disponible"
        )

        Spacer(modifier = Modifier.height(10.dp))

        InfoCard(
            title = "Población",
            value = department.population?.toString() ?: "No disponible"
        )

        Spacer(modifier = Modifier.height(10.dp))

        InfoCard(
            title = "Superficie",
            value = department.surface?.toString() ?: "No disponible"
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Descripción",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = ColombiaBlue
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = department.description ?: "No hay descripción disponible.",
            fontSize = 13.sp,
            color = Color.DarkGray,
            lineHeight = 20.sp
        )
    }
}

@Composable
fun InfoCard(
    title: String,
    value: String
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = title,
                fontSize = 13.sp,
                color = Color.Gray
            )

            Text(
                text = value,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = ColombiaBlue
            )
        }
    }
}
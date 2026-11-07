package com.example.appcolombia.ui.Screens.auth

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.appcolombia.data.repository.CloudinaryRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

private val ColombiaBlue = Color(0xFF003893)
private val ColombiaYellow = Color(0xFFFFD100)
private val BackgroundColor = Color(0xFFF5F7FC)

@Composable
fun ProfileScreen(
    userName: String,
    userEmail: String,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var imageUrl by remember {
        mutableStateOf(
            FirebaseAuth.getInstance()
                .currentUser
                ?.photoUrl
                ?.toString()
                .orEmpty()
        )
    }

    var isUploading by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null && !isUploading) {
            scope.launch {
                isUploading = true
                message = "Subiendo tu foto..."
                isError = false

                try {
                    val user = FirebaseAuth.getInstance().currentUser
                        ?: throw Exception("No hay una sesión activa.")

                    val uploadedUrl = CloudinaryRepository.uploadImage(
                        contentResolver = context.contentResolver,
                        imageUri = uri
                    )

                    val profileUpdates =
                        UserProfileChangeRequest.Builder()
                            .setPhotoUri(Uri.parse(uploadedUrl))
                            .build()

                    user.updateProfile(profileUpdates).await()

                    imageUrl = uploadedUrl
                    message = "¡Foto de perfil actualizada!"
                    isError = false
                } catch (exception: Exception) {
                    message = exception.localizedMessage
                        ?: "No se pudo actualizar la foto. Inténtalo de nuevo."
                    isError = true
                } finally {
                    isUploading = false
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(horizontal = 24.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Mi perfil",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = ColombiaBlue
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Tu espacio personal en Colombia Explorer",
            fontSize = 14.sp,
            color = Color.DarkGray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(28.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(132.dp)
                        .clip(CircleShape)
                        .background(ColombiaBlue),
                    contentAlignment = Alignment.Center
                ) {
                    if (imageUrl.isNotBlank()) {
                        AsyncImage(
                            model = imageUrl,
                            contentDescription = "Foto de perfil",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Text(
                            text = userName
                                .trim()
                                .firstOrNull()
                                ?.uppercase()
                                ?: "U",
                            fontSize = 52.sp,
                            fontWeight = FontWeight.Bold,
                            color = ColombiaYellow
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = userName.ifBlank { "Usuario" },
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = ColombiaBlue,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = userEmail.ifBlank { "Correo no disponible" },
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(22.dp))

                Button(
                    onClick = {
                        imagePicker.launch("image/*")
                    },
                    enabled = !isUploading,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ColombiaBlue
                    )
                ) {
                    if (isUploading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            color = Color.White,
                            strokeWidth = 2.dp
                        )

                        Spacer(modifier = Modifier.size(10.dp))

                        Text("Subiendo foto...")
                    } else {
                        Text("Cambiar foto de perfil")
                    }
                }

                if (message.isNotBlank()) {
                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = message,
                        color = if (isError) {
                            Color(0xFFB3261E)
                        } else {
                            Color(0xFF237A3B)
                        },
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        TextButton(
            onClick = onBackClick,
            enabled = !isUploading
        ) {
            Text(
                text = "Volver a Colombia Explorer",
                color = ColombiaBlue,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
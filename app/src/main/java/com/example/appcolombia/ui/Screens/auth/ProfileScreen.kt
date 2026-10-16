
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.appcolombia.data.repository.CloudinaryRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.launch

private val ColombiaBlue = Color(0xFF003893)
private val ColombiaYellow = Color(0xFFFFD100)
private val BackgroundColor = Color(0xFFF8F9FC)

@Composable
fun ProfileScreen(
    userName: String,
    userEmail: String,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val firebaseUser = FirebaseAuth.getInstance().currentUser

    var imageUrl by remember {
        mutableStateOf(firebaseUser?.photoUrl?.toString().orEmpty())
    }
    var isUploading by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            scope.launch {
                isUploading = true
                message = "Subiendo imagen..."
                isError = false

                try {
                    val uploadedUrl = CloudinaryRepository.uploadImage(
                        contentResolver = context.contentResolver,
                        imageUri = uri
                    )

                    val user = FirebaseAuth.getInstance().currentUser
                        ?: throw Exception("No hay una sesión activa")

                    val profileUpdates =
                        UserProfileChangeRequest.Builder()
                            .setPhotoUri(Uri.parse(uploadedUrl))
                            .build()

                    user.updateProfile(profileUpdates)
                        .addOnCompleteListener { task ->
                            isUploading = false

                            if (task.isSuccessful) {
                                imageUrl = uploadedUrl
                                message = "¡Foto de perfil actualizada!"
                                isError = false
                            } else {
                                message = task.exception?.localizedMessage
                                    ?: "La foto subió, pero no se pudo actualizar Firebase"
                                isError = true
                            }
                        }
                } catch (exception: Exception) {
                    isUploading = false
                    message = exception.localizedMessage
                        ?: "No se pudo subir la imagen"
                    isError = true
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Mi perfil",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = ColombiaBlue
        )

        Spacer(modifier = Modifier.height(28.dp))

        Box(
            modifier = Modifier
                .size(120.dp)
                .background(ColombiaBlue, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            if (imageUrl.isNotBlank()) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "Foto de perfil",
                    modifier = Modifier
                        .fillMaxSize()
                        .background(ColombiaBlue, CircleShape),
                    contentScale = ContentScale.Crop
                )
            } else {
                Text(
                    text = userName.firstOrNull()?.uppercase() ?: "U",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    color = ColombiaYellow
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Foto de perfil",
            fontSize = 13.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = { imagePicker.launch("image/*") },
            enabled = !isUploading,
            colors = ButtonDefaults.buttonColors(
                containerColor = ColombiaBlue
            )
        ) {
            Text(
                text = if (isUploading) {
                    "Subiendo..."
                } else {
                    "Cambiar foto"
                },
                color = Color.White
            )
        }

        if (isUploading) {
            CircularProgressIndicator(
                modifier = Modifier.size(28.dp),
                color = ColombiaBlue
            )
        }

        if (message.isNotBlank()) {
            Text(
                text = message,
                color = if (isError) Color.Red else ColombiaBlue,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = "Nombre",
                    fontSize = 13.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = userName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = ColombiaBlue
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Correo electrónico",
                    fontSize = 13.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = userEmail,
                    fontSize = 16.sp,
                    color = ColombiaBlue
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        TextButton(
            onClick = onBackClick,
            enabled = !isUploading
        ) {
            Text(
                text = "Volver a Colombia Explorer",
                color = ColombiaBlue
            )
        }
    }
}

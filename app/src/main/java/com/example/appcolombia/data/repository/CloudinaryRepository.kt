
package com.example.appcolombia.data.repository

import android.content.ContentResolver
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.UUID

object CloudinaryRepository {

    private const val CLOUD_NAME = "ebscfk0r"
    private const val UPLOAD_PRESET = "appcolombia_profile"

    suspend fun uploadImage(
        contentResolver: ContentResolver,
        imageUri: Uri
    ): String = withContext(Dispatchers.IO) {

        val mimeType = contentResolver.getType(imageUri)
            ?: throw Exception("No se pudo identificar el formato de la imagen")

        if (mimeType !in listOf("image/jpeg", "image/png", "image/webp")) {
            throw Exception("Selecciona una imagen JPG, PNG o WebP")
        }

        val imageBytes = contentResolver.openInputStream(imageUri)?.use {
            it.readBytes()
        } ?: throw Exception("No se pudo leer la imagen")

        if (imageBytes.size > 5 * 1024 * 1024) {
            throw Exception("La imagen debe pesar menos de 5 MB")
        }

        val boundary = "Boundary-${UUID.randomUUID()}"
        val connection = URL(
            "https://api.cloudinary.com/v1_1/$CLOUD_NAME/image/upload"
        ).openConnection() as HttpURLConnection

        try {
            connection.requestMethod = "POST"
            connection.connectTimeout = 15000
            connection.readTimeout = 30000
            connection.doOutput = true
            connection.setRequestProperty(
                "Content-Type",
                "multipart/form-data; boundary=$boundary"
            )

            connection.outputStream.use { output ->
                fun writeText(value: String) {
                    output.write(value.toByteArray(Charsets.UTF_8))
                }

                writeText("--$boundary\r\n")
                writeText(
                    "Content-Disposition: form-data; " +
                            "name=\"upload_preset\"\r\n\r\n"
                )
                writeText("$UPLOAD_PRESET\r\n")

                writeText("--$boundary\r\n")
                writeText(
                    "Content-Disposition: form-data; " +
                            "name=\"file\"; filename=\"profile.jpg\"\r\n"
                )
                writeText("Content-Type: $mimeType\r\n\r\n")
                output.write(imageBytes)
                writeText("\r\n--$boundary--\r\n")
            }

            val responseCode = connection.responseCode
            val responseStream = if (responseCode in 200..299) {
                connection.inputStream
            } else {
                connection.errorStream
            }

            val response = responseStream?.bufferedReader()?.use {
                it.readText()
            }.orEmpty()

            if (responseCode !in 200..299) {
                val message = try {
                    JSONObject(response)
                        .optJSONObject("error")
                        ?.optString("message")
                } catch (_: Exception) {
                    null
                }

                throw Exception(
                    message?.takeIf { it.isNotBlank() }
                        ?: "Cloudinary rechazó la subida ($responseCode)"
                )
            }

            val secureUrl = JSONObject(response)
                .optString("secure_url")

            if (secureUrl.isBlank()) {
                throw Exception("Cloudinary no devolvió la URL de la imagen")
            }

            secureUrl
        } finally {
            connection.disconnect()
        }
    }
}

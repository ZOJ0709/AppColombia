
package com.example.appcolombia.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

class AuthRepository {

    private val auth = FirebaseAuth.getInstance()

    fun register(
        name: String,
        email: String,
        password: String,
        onResult: (Boolean, String?) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(
            email.trim(),
            password
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val profile = UserProfileChangeRequest.Builder()
                    .setDisplayName(name.trim())
                    .build()

                auth.currentUser?.updateProfile(profile)
                    ?.addOnCompleteListener { profileTask ->
                        if (profileTask.isSuccessful) {
                            onResult(true, null)
                        } else {
                            onResult(
                                false,
                                profileTask.exception?.localizedMessage
                                    ?: "Cuenta creada, pero no se pudo guardar el nombre."
                            )
                        }
                    }
                    ?: onResult(true, null)
            } else {
                onResult(
                    false,
                    task.exception?.localizedMessage
                        ?: "No se pudo crear la cuenta."
                )
            }
        }
    }

    fun login(
        email: String,
        password: String,
        onResult: (Boolean, String?) -> Unit
    ) {
        auth.signInWithEmailAndPassword(
            email.trim(),
            password
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onResult(true, null)
            } else {
                onResult(
                    false,
                    task.exception?.localizedMessage
                        ?: "No se pudo iniciar sesión."
                )
            }
        }
    }

    fun logout() {
        auth.signOut()
    }

    fun isLoggedIn(): Boolean {
        return auth.currentUser != null
    }
}

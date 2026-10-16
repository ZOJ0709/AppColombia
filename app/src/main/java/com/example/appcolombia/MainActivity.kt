
package com.example.appcolombia

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import com.example.appcolombia.data.model.City
import com.example.appcolombia.data.model.Department
import com.example.appcolombia.data.model.Region
import com.example.appcolombia.data.repository.AuthRepository
import com.example.appcolombia.data.repository.ColombiaRepository
import com.example.appcolombia.ui.Screens.auth.LoginScreen
import com.example.appcolombia.ui.Screens.auth.RegisterScreen
import com.example.appcolombia.ui.Screens.splash.DepartmentDetailScreen
import com.example.appcolombia.ui.Screens.splash.HomeScreen
import com.example.appcolombia.ui.Screens.splash.SplashScreen
import com.example.appcolombia.ui.theme.AppColombiaTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private enum class AppScreen {
    SPLASH,
    LOGIN,
    REGISTER,
    HOME,
    DETAIL
}

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppColombiaTheme {

                var currentScreen by remember {
                    mutableStateOf(AppScreen.SPLASH)
                }

                var selectedDepartment by remember {
                    mutableStateOf<Department?>(null)
                }

                var selectedRegion by remember {
                    mutableStateOf<Region?>(null)
                }

                var selectedCapital by remember {
                    mutableStateOf<City?>(null)
                }

                var departments by remember {
                    mutableStateOf<List<Department>>(emptyList())
                }

                val colombiaRepository = remember {
                    ColombiaRepository()
                }

                val authRepository = remember {
                    AuthRepository()
                }

                val scope = rememberCoroutineScope()

                LaunchedEffect(Unit) {
                    delay(2000)

                    colombiaRepository.getDepartments()
                        .onSuccess {
                            departments = it
                        }

                    currentScreen = if (authRepository.isLoggedIn()) {
                        AppScreen.HOME
                    } else {
                        AppScreen.LOGIN
                    }
                }

                when (currentScreen) {

                    AppScreen.SPLASH -> {
                        SplashScreen()
                    }

                    AppScreen.LOGIN -> {
                        LoginScreen(
                            onLoginClick = { email, password ->

                                authRepository.login(
                                    email,
                                    password
                                ) { success, error ->

                                    if (success) {
                                        currentScreen = AppScreen.HOME
                                    } else {
                                        Toast.makeText(
                                            this@MainActivity,
                                            error ?: "No se pudo iniciar sesión.",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                            },
                            onRegisterClick = {
                                currentScreen = AppScreen.REGISTER
                            }
                        )
                    }

                    AppScreen.REGISTER -> {
                        RegisterScreen(
                            onRegisterClick = { name, email, password ->

                                authRepository.register(
                                    name,
                                    email,
                                    password
                                ) { success, error ->

                                    if (success) {
                                        currentScreen = AppScreen.HOME
                                    } else {
                                        Toast.makeText(
                                            this@MainActivity,
                                            error ?: "No se pudo crear la cuenta.",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                            },
                            onLoginClick = {
                                currentScreen = AppScreen.LOGIN
                            }
                        )
                    }

                    AppScreen.HOME -> {
                        HomeScreen(
                            departments = departments,
                            onDepartmentClick = { department ->

                                selectedDepartment = department
                                selectedRegion = null
                                selectedCapital = null

                                scope.launch {

                                    department.regionId?.let { regionId ->
                                        colombiaRepository
                                            .getRegionById(regionId)
                                            .onSuccess {
                                                selectedRegion = it
                                            }
                                    }

                                    department.cityCapitalId?.let { cityId ->
                                        colombiaRepository
                                            .getCityById(cityId)
                                            .onSuccess {
                                                selectedCapital = it
                                            }
                                    }

                                    currentScreen = AppScreen.DETAIL
                                }
                            }
                        )
                    }

                    AppScreen.DETAIL -> {
                        selectedDepartment?.let { department ->

                            DepartmentDetailScreen(
                                department = department,
                                region = selectedRegion,
                                capital = selectedCapital,
                                onBackClick = {
                                    currentScreen = AppScreen.HOME
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

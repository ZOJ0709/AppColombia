package com.example.appcolombia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.appcolombia.data.model.City
import com.example.appcolombia.data.model.Department
import com.example.appcolombia.data.model.Region
import com.example.appcolombia.data.repository.ColombiaRepository
import com.example.appcolombia.ui.Screens.splash.DepartmentDetailScreen
import com.example.appcolombia.ui.Screens.splash.HomeScreen
import com.example.appcolombia.ui.Screens.splash.SplashScreen
import com.example.appcolombia.ui.theme.AppColombiaTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppColombiaTheme {

                var showSplash by remember {
                    mutableStateOf(true)
                }

                var showDetail by remember {
                    mutableStateOf(false)
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

                val repository = remember {
                    ColombiaRepository()
                }

                val scope = rememberCoroutineScope()

                LaunchedEffect(Unit) {

                    delay(2000)

                    repository.getDepartments()
                        .onSuccess {
                            departments = it
                        }

                    showSplash = false
                }

                when {

                    showSplash -> {

                        SplashScreen()
                    }

                    showDetail && selectedDepartment != null -> {

                        DepartmentDetailScreen(
                            department = selectedDepartment!!,
                            region = selectedRegion,
                            capital = selectedCapital,
                            onBackClick = {
                                showDetail = false
                            }
                        )
                    }

                    else -> {

                        HomeScreen(
                            departments = departments,
                            onDepartmentClick = { department ->

                                selectedDepartment = department

                                selectedRegion = null
                                selectedCapital = null

                                scope.launch {

                                    department.regionId?.let { regionId ->

                                        repository.getRegionById(regionId)
                                            .onSuccess { region ->
                                                selectedRegion = region
                                            }
                                    }

                                    department.cityCapitalId?.let { cityId ->

                                        repository.getCityById(cityId)
                                            .onSuccess { city ->
                                                selectedCapital = city
                                            }
                                    }
                                }

                                showDetail = true
                            }
                        )
                    }
                }
            }
        }
    }
}
package com.example.tmdbapp.splash

import TMDBAppTheme
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.tmdbapp.R
import constant.NavigationConstant.ActivityClass.HOME_ACTIVITY
import dagger.hilt.android.AndroidEntryPoint
import navigator.ActivityNavigator
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {

    @Inject
    lateinit var activityNavigator: ActivityNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            activityNavigator.navigateTo(targetClassName = HOME_ACTIVITY.className)
            finish()
        }, 2000)

        setContent {
            TMDBAppTheme {
                CenteredImageScreen()
            }
        }
    }
}

@Preview
@Composable
fun CenteredImageScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.izi_eats), // Ganti sesuai asset kamu
            contentDescription = "Centered Image",
            modifier = Modifier.size(120.dp)
        )
    }
}
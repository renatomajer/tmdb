package agency.five.tmdb


import agency.five.tmdb.navigation.RootNavigationGraph
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.White
            ) {

                val rootNavController = rememberNavController()

                RootNavigationGraph(rootNavController)
            }
        }
    }
}


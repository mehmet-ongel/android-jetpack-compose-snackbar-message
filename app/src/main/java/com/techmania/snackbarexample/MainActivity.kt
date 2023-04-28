package com.techmania.snackbarexample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.techmania.snackbarexample.ui.theme.SnackbarExampleTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SnackbarExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SnackbarExample()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SnackbarExample() {

    val mySnackbarHostState = remember {
        SnackbarHostState()
    }
    val myCoroutineScope = rememberCoroutineScope()
    val myContext = LocalContext.current

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = mySnackbarHostState
            ){
                Snackbar(
                    snackbarData = it,
                    containerColor = Color.Red,
                    contentColor = Color.White,
                    actionColor = Color.Black,
                    dismissActionContentColor = Color.Blue
                )
            }
        },
        content = { contentPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Button(
                    onClick = {

                        myCoroutineScope.launch {

                            val result = mySnackbarHostState.showSnackbar(
                                message = "This is the snackbar message",
                                actionLabel = "Show Toast",
                                duration = SnackbarDuration.Indefinite,
                                withDismissAction = true
                            )

                            if (result == SnackbarResult.ActionPerformed){
                                Toast.makeText(myContext,"Action Performed",Toast.LENGTH_SHORT).show()
                            }

                        }
                    }
                ) {
                    Text(text = "Show Snackbar Message")
                }

            }
        }
    )

}
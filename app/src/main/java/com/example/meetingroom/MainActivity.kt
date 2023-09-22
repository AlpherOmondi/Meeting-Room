package com.example.meetingroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import io.getstream.video.android.compose.theme.VideoTheme
import io.getstream.video.android.compose.ui.components.call.activecall.CallContent
import io.getstream.video.android.core.StreamVideoBuilder
import io.getstream.video.android.core.call.state.FlipCamera
import io.getstream.video.android.core.call.state.LeaveCall
import io.getstream.video.android.core.call.state.ToggleCamera
import io.getstream.video.android.core.call.state.ToggleMicrophone
import io.getstream.video.android.model.User
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize variables
        val apiKey = "mmhfdzb5evj2"
        val userId = "Natasi_Daala"
        val userToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiTmF0YXNpX0RhYWxhIiwiaXNzIjoicHJvbnRvIiwic3ViIjoidXNlci9OYXRhc2lfRGFhbGEiLCJpYXQiOjE2OTUzMzk0NjcsImV4cCI6MTY5NTk0NDI3Mn0.p4RYSpQ92XtZ7woIwoQpIic0Zab30hEB_8ct8oRknAk"
        val callId = "LYuxTOfAsdFd"

        // Initializing streamVideo.
        val client = StreamVideoBuilder(
            context = applicationContext,
            apiKey = apiKey,
            user = User(
                id = userId,
                name = "Alpha",
                role = "admin",
            ),
            token = userToken,
        ).build()

        // Join a call
        val call = client.call(type = "default", id = callId)
        lifecycleScope.launch {
            call.join(create = true)
        }



        setContent {
            // Video Theme
            VideoTheme {


                CallContent(
                    modifier = Modifier.fillMaxSize(),
                    call = call,
                    onBackPressed = {finish()},
                    onCallAction = { callAction ->
                        when (callAction) {
                            is FlipCamera -> call.camera.flip()
                            is ToggleCamera -> call.camera.setEnabled(callAction.isEnabled)
                            is ToggleMicrophone -> call.microphone.setEnabled(callAction.isEnabled)
                            is LeaveCall -> finish()
                            else -> Unit


                        }
                    },
                )

            }

        }

    }

}


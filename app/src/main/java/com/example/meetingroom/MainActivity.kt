package com.example.meetingroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import io.getstream.video.android.core.StreamVideoBuilder
import io.getstream.video.android.model.User

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize variables
        val apiKey = "wdmunyg7y679"
        val userId = ""
        val userToken = ""
        val callId = ""

        // Initializing streamVideo.
        val client = StreamVideoBuilder(
            context = applicationContext,
            apiKey = apiKey,
            user = User(
                id = userId,
                name = "Meeting room",
                role = "admin",
            ),
            token = userToken,
        ).build()



        setContent {

        }
    }
}
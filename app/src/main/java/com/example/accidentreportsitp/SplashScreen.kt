
package com.example.accidentreportsitp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            // Start the next activity
            val intent = Intent(this@SplashScreen, RoadAndCause::class.java)
            startActivity(intent)
            finish() // Finish the splash activity
        }, 2500)
    }
}

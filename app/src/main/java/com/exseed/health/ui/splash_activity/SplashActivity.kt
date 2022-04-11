package com.exseed.health.ui.splash_activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import com.exseed.health.R
import com.exseed.health.databinding.ActivitySplashBinding
import com.exseed.health.ui.main_activity.MainActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var activitySplashBinding: ActivitySplashBinding
    private lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySplashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(activitySplashBinding.root)
        context = this@SplashActivity
        startAnimations()
        loadScreen()
    }

    /*loadScreen():
    Load next activity after the delay of 3 secs*/
    private fun loadScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            val splashIntent = Intent(context, MainActivity::class.java)
            startActivity(splashIntent)
            finish()
        }, 3000)
    }

    /*Load the xml file layout(constraintLayout) according to the alpha file in anim folder
    * Load the imageview(splashLogo) according to the translate file in anim folder*/
    private fun startAnimations() {
        var animationUtils = AnimationUtils.loadAnimation(context, R.anim.alpha)
        animationUtils.reset()
        activitySplashBinding.constraintLayout.clearAnimation()
        activitySplashBinding.constraintLayout.startAnimation(animationUtils)
        animationUtils = AnimationUtils.loadAnimation(context, R.anim.translate)
        animationUtils.reset()
        activitySplashBinding.splashLogo.clearAnimation()
        activitySplashBinding.splashLogo.startAnimation(animationUtils)
    }
}
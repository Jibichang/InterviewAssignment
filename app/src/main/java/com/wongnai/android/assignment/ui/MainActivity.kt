package com.wongnai.android.assignment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wongnai.android.assignment.R
import com.wongnai.android.assignment.ui.info.CoinInfoFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        supportFragmentManager.beginTransaction().replace(R.id.flContent, MainFragment())
        supportFragmentManager.beginTransaction().replace(R.id.flContent, CoinInfoFragment()).commit()
    }
}

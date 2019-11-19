package com.wongnai.android.assignment.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.wongnai.android.assignment.R
import com.wongnai.android.assignment.ui.info.CoinInfoFragment
import com.wongnai.android.assignment.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(
            R.id.flContent,
            MainFragment()
        ).commit()
    }
}

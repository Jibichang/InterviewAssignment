package com.wongnai.android.assignment.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wongnai.android.assignment.R
import com.wongnai.android.assignment.api.CoinApi
import org.koin.android.ext.android.inject

class MainFragment : Fragment() {
    private val coinApi: CoinApi by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }
}

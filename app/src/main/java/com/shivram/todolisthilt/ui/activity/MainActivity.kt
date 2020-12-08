package com.shivram.todolisthilt.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shivram.todolisthilt.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
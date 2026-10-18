package com.example.paycalculator

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class AboutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // show the back arrow in the action bar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        title = "About"

        setContent {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Full name: Landon Swales  ")
                Text("Student ID: 101549970")
            }
        }
    }

    // runs when something in the action bar is tapped
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
package com.example.paycalculator

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PayScreen()
        }
    }
}

@Composable
fun PayScreen() {
    val context = LocalContext.current

    // what the user types
    var hours by remember { mutableStateOf("") }
    var rate by remember { mutableStateOf("") }
    var taxRate by remember { mutableStateOf("") }

    // the results
    var pay by remember { mutableStateOf(0.0) }
    var overtimePay by remember { mutableStateOf(0.0) }
    var totalPay by remember { mutableStateOf(0.0) }
    var tax by remember { mutableStateOf(0.0) }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = hours,
            onValueChange = { hours = it },
            label = { Text("Hours worked") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        TextField(
            value = rate,
            onValueChange = { rate = it },
            label = { Text("Hourly rate") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        TextField(
            value = taxRate,
            onValueChange = { taxRate = it },
            label = { Text("Tax rate") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Button(onClick = {
            val h = hours.toDoubleOrNull() ?: 0.0
            val r = rate.toDoubleOrNull() ?: 0.0
            val t = taxRate.toDoubleOrNull() ?: 0.0

            if (h <= 40) {
                pay = h * r
                overtimePay = 0.0
            } else {
                pay = 40 * r
                overtimePay = (h - 40) * r * 1.5
            }

            totalPay = pay + overtimePay
            tax = pay * t
        }) {
            Text("Calculate")
        }

        Text("Pay: " + String.format("%.2f", pay))
        Text("Overtime pay: " + String.format("%.2f", overtimePay))
        Text("Total pay: " + String.format("%.2f", totalPay))
        Text("Tax: " + String.format("%.2f", tax))

        Button(onClick = {
            context.startActivity(Intent(context, AboutActivity::class.java))
        }) {
            Text("About")
        }
    }
}
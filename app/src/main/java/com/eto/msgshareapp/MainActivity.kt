package com.eto.msgshareapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val cardSecond = findViewById<CardView>(R.id.GroceryTile)
        cardSecond.setOnClickListener {
            val intent = Intent(this, GroceryListActivity::class.java)
            startActivity(intent)
        }
    }
}
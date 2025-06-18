package com.ios7.wallify

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.android.volley.toolbox.Volley
import java.net.URI
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

// This is an attempt of me trying to learn Kotlin. This activity will very likely never be launchable in regular builds.
// You can't even launch it without a debug build.

class WalldownloadkotlinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.walldownloadkotlin)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.linear1)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var textview1 = findViewById<TextView>(R.id.textview1)
        textview1.setText(intent.getStringExtra("wallpaperName"))
        val linear7 = findViewById<LinearLayout>(R.id.linear7)
        linear7.clipToOutline = true
        val linear9 = findViewById<LinearLayout>(R.id.linear9)
        linear9.clipToOutline = true
        val config = getSharedPreferences("config", MODE_PRIVATE)
        val imageView1 = findViewById<ImageView>(R.id.imageview1)
        val imageView3 = findViewById<ImageView>(R.id.imageview3)
        try {
            Glide.with(this)
                .load(config.getString("repo", "")?.trim()+intent.getStringExtra("wallpaperLink")?.trim())
                .into(imageView1)
            Glide.with(this)
                .load(config.getString("repo", "")?.trim()+intent.getStringExtra("wallpaperLink")?.trim())
                .into(imageView3)
        } catch (e: Exception) {
            Log.e("WalldownloadkotlinActivity", "Error: " + e.toString())
            Log.e("WalldownloadkotlinActivity", "Tried to load:"+ config.getString("repo", "")?.trim()+intent.getStringExtra("wallpaperLink")?.trim())
        }
    }
}

class WallpaperLoader () {
}
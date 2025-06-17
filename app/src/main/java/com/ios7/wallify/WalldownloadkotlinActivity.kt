package com.ios7.wallify

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
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

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "config")

class WalldownloadkotlinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val imageView1 = findViewById<ImageView>(R.id.imageview1)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.walldownloadkotlin)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.linear1)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Glide.with(this)
        //    .load(config.getString("repo", "")+intent.getStringExtra("wallpaperLink"))
        //    .into(imageView1)
    }
}

class WallpaperLoader () {
}
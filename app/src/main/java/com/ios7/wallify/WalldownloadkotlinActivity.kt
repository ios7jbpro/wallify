package com.ios7.wallify

import com.ios7.wallify.MyClasses.EzFade
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
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
        val textView5 = findViewById<TextView>(R.id.textView5)
        textView5.visibility = TextView.GONE
        val textViewCrop = findViewById<TextView>(R.id.textViewCrop)
        textViewCrop.visibility = TextView.GONE
        val textView3 = findViewById<TextView>(R.id.textView3)
        textView3.visibility = TextView.GONE
        val colorpreviews = findViewById<LinearLayout>(R.id.colorpreviews)
        colorpreviews.visibility = LinearLayout.GONE
        val colorpreviewsloading = findViewById<LinearLayout>(R.id.colorpreviewsloading)
        colorpreviewsloading.visibility = LinearLayout.GONE
        val linear7 = findViewById<LinearLayout>(R.id.linear7)
        linear7.clipToOutline = true
        val linear9 = findViewById<LinearLayout>(R.id.linear9)
        linear9.clipToOutline = true
        val linearpreviewcard = findViewById<LinearLayout>(R.id.linearpreviewcard)
        EzFade.fadeIn(linearpreviewcard, 500)
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
        val button1 = findViewById<TextView>(R.id.button1)
        val button2 = findViewById<TextView>(R.id.button2)
        button2.setOnClickListener {
            Toast.makeText(this, "Using legacy applier.\nNew applier doesn't exist in this activity.", Toast.LENGTH_SHORT).show()
            val wallLink = getSharedPreferences("wallLink", MODE_PRIVATE)
            wallLink.edit().putString("wallLink", config.getString("repo", "")?.trim()+intent.getStringExtra("wallpaperLink")?.trim()).apply()
            val intent = Intent(this, Setwall1Activity::class.java)
            startActivity(intent)
        }
    }
}

class WallpaperLoader () {
}
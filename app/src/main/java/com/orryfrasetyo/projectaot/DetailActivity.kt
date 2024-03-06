package com.orryfrasetyo.projectaot

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val imgCharacter: ImageView = findViewById(R.id.img_detail_photo)
        val tvCharacterName: TextView = findViewById(R.id.tv_detail_name)
        val tvCharacterDesc: TextView = findViewById(R.id.tv_detail_description)
        val btnShare : Button = findViewById(R.id.btn_share)


        val dataAot = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("key_aot", Aot::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("key_aot")
        }

        if (dataAot != null) {
            tvCharacterName.text = dataAot.name
            tvCharacterDesc.text = dataAot.description
            Glide.with(applicationContext)
                .load(dataAot.photo)
                .into(imgCharacter)
        }

        btnShare.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "https://attackontitan.fandom.com/wiki/Attack_on_Titan_Wiki")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }
}
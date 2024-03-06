package com.example.androidsubmission

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {
    private lateinit var photoSuku: ImageView
    private lateinit var sukuName: TextView
    private lateinit var sukuDescription: TextView
    companion object{
        const val EXTRA_SUKU = "extra_suku"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        photoSuku = findViewById(R.id.detailImageView)
        sukuName = findViewById(R.id.detailTextTitle)
        sukuDescription= findViewById(R.id.detailTextOverview)
        val suku = if (Build.VERSION.SDK_INT >= 33){
            intent.getParcelableExtra<Suku>(EXTRA_SUKU, Suku::class.java)
        } else{
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Suku>(EXTRA_SUKU)
        }
        if(suku != null){
            Picasso.get().load(suku.photo).into(photoSuku)
            sukuName.text = suku.name
            sukuDescription.text = resources.getStringArray(R.array.detail_suku_array)[suku.id]
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
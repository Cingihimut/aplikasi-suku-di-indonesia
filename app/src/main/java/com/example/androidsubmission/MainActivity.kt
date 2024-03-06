package com.example.androidsubmission

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidsubmission.DetailActivity.Companion.EXTRA_SUKU
import com.example.androidsubmission.ListSukuAdapter as ListSukuAdapter
class MainActivity : AppCompatActivity() {
    private lateinit var rvSuku: RecyclerView
    private var list = ArrayList<Suku>()
    private var profileBtn: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val actionBar = supportActionBar
        actionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        actionBar?.setCustomView(R.layout.action_bar)
        val customActionBar = actionBar?.customView
        profileBtn = customActionBar?.findViewById(R.id.profileBtn)
        profileBtn?.setOnClickListener{
            val target = Intent(this@MainActivity, ProfileActivity::class.java)
            startActivity(target)
        }
        rvSuku = findViewById(R.id.rv_suku)
        rvSuku.setHasFixedSize(true)
        list.addAll(getListSuku())
        showRecyclerList()
    }
    private fun getListSuku(): ArrayList<Suku> {
        val dataName = resources.getStringArray(R.array.suku_array)
        val dataDescription = resources.getStringArray(R.array.detail_suku_array)
        val dataPhoto = resources.obtainTypedArray(R.array.detail_photo)
        val listSuku = ArrayList<Suku>()
        for (i in dataName.indices){
            val suku = Suku(i, dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listSuku.add(suku)
        }
        return listSuku
    }
    private fun showRecyclerList() {
        rvSuku.layoutManager = GridLayoutManager(this, 2)
        val listSukuAdapter = ListSukuAdapter(list)
        rvSuku.adapter = listSukuAdapter
        listSukuAdapter.setOnItemClickCallBack(object : ListSukuAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Suku){
                showSelectedSuku(data)
            }
        })
    }
    private fun showSelectedSuku(suku: Suku) {
        val terget = Intent(this@MainActivity, DetailActivity::class.java)
        terget.putExtra(EXTRA_SUKU, suku)
        startActivity(terget)
    }
}
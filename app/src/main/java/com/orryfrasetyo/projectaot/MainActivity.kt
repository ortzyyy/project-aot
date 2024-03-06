package com.orryfrasetyo.projectaot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvAot: RecyclerView
    private val list = ArrayList<Aot>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvAot = findViewById(R.id.rv_aot)
        rvAot.setHasFixedSize(true)

        list.addAll(getListAot())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rvAot.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rvAot.layoutManager = GridLayoutManager(this, 2)
            }
            R.id.action_about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListAot(): ArrayList<Aot> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listAot = ArrayList<Aot>()
        for (i in dataName.indices) {
            val aot = Aot(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listAot.add(aot)
        }
        return listAot
    }

    private fun showRecyclerList() {
        rvAot.layoutManager = LinearLayoutManager(this)
        val listAotAdapter = ListAotAdapter(list)
        rvAot.adapter = listAotAdapter

        listAotAdapter.setOnItemClickCallback(object : ListAotAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Aot) {
                showSelectedAot(data)
            }
        })
    }

    private fun showSelectedAot(aot: Aot) {
        Toast.makeText(this, "Kamu memilih " + aot.name, Toast.LENGTH_SHORT).show()
    }
}

package com.example.sshop

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ItemsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_items)


        val itemsList: RecyclerView = findViewById(R.id.item_list)
        val items = arrayListOf<Item>()

        items.add(Item(1,"smth","Riden","Genshin","AAAAAAAAAAAAAAAAAAAAAAAAAAAAAa",10000))
        items.add(Item(2,"smth","Riden","Genshin","AAAAAAAAAAAAAAAAAAAAAAAAAAAAAa",10000))
        items.add(Item(3,"smth","Riden","Genshin","AAAAAAAAAAAAAAAAAAAAAAAAAAAAAa",10000))
        items.add(Item(4,"smth","Riden","Genshin","AAAAAAAAAAAAAAAAAAAAAAAAAAAAAa",10000))

        itemsList.layoutManager = LinearLayoutManager(this)
        itemsList.adapter = ItemsAdapter(items, this)
    }
}
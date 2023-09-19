package com.rentalapp.az

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {


    lateinit var btn_borrow: Button
    lateinit var btn_next: Button
    lateinit var title_tv: TextView

    lateinit var des_tv: TextView
    lateinit var im: ImageView
    lateinit var ratingBar: RatingBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ratingBar = findViewById(R.id.ratingBar)

        btn_borrow = findViewById(R.id.btn_borrow)
        btn_next = findViewById(R.id.btn_next)
        im = findViewById(R.id.im)
        title_tv = findViewById(R.id.title_tv)
        des_tv = findViewById(R.id.des_tv)


        val data = ArrayList<ItemsViewModel>()
        for (i in 1..20) {
            data.add(ItemsViewModel(R.drawable.b11, "Item " + i))
        }


        btn_borrow.setOnClickListener {

        }

    }
}
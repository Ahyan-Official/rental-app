package com.rentalapp.az

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {


    lateinit var btn_borrow: Button
    lateinit var btn_next: Button
    lateinit var title_tv: TextView
    lateinit var price_tv: TextView

    lateinit var des_tv: TextView
    lateinit var im: ImageView
    lateinit var ratingBar: RatingBar
    val  data = ArrayList<ItemsViewModel>()
    var counter:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ratingBar = findViewById(R.id.ratingBar)

        btn_borrow = findViewById(R.id.btn_borrow)
        btn_next = findViewById(R.id.btn_next)
        im = findViewById(R.id.im)
        title_tv = findViewById(R.id.title_tv)
        des_tv = findViewById(R.id.des_tv)
        price_tv = findViewById(R.id.price_tv)



        addData()

        Toast.makeText(applicationContext,"dasdsa",Toast.LENGTH_SHORT).show()
        loadData(counter)
        btn_borrow.setOnClickListener {
            val intent = Intent(applicationContext, DaysActivity::class.java)
            intent.putExtra("price",data[counter].price)
            intent.putExtra("counter",counter)

            startActivity(intent)
        }

        btn_next.setOnClickListener {

            counter = counter+1
            if(counter==data.size){
                counter--
            }

            if(counter<data.size){


                loadData(counter)
            }
            Log.e("ppp", "onCreate: "
                    +data.size+"  "+counter)

        }

    }
    fun addData(){

        data.add(ItemsViewModel(R.drawable.b11, "Bike 1 ", "Dec",3.5f,"Borrow",10))
        data.add(ItemsViewModel(R.drawable.b11, "Bike 2 ", "Dec",4.5f,"Borrow",20))



    }

    fun loadData( int: Int){

        title_tv.text = data[int].title
        des_tv.text = data[int].des
        price_tv.text = "$"+data[int].price
        btn_borrow.text = data[int].status

        ratingBar.rating = data[int].rating
        im.setImageResource(data[int].image)

    }
    fun readData(){
        for(i in data){

            title_tv.text = i.title
            des_tv.text = i.des
            ratingBar.rating = i.rating
            im.setImageResource(i.image)
        }
    }

    override fun onResume() {
        super.onResume()

        val sharedPreferences: SharedPreferences = this.getSharedPreferences("sharedPrefFile",
            Context.MODE_PRIVATE)
        val counter = sharedPreferences.getInt("counter",0)
        val dateStr = sharedPreferences.getString("date","Borrow")

       // val dateStr: String = intent.getStringExtra("date").toString()
        //val counter:Int = intent.getIntExtra("counter",0)


        if(dateStr!="Borrow"){
            if (dateStr != null) {
                data[counter].status = dateStr
                loadData(counter)

            };
        }

    }
}
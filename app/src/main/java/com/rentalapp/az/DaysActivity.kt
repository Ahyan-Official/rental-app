package com.rentalapp.az

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

class DaysActivity : AppCompatActivity() {

    lateinit var btn_save: Button
    lateinit var price_tv: TextView
    var total:Int=0
    var saveNotPressed:Boolean = true;
    var output: String = "Borrow"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_days)



        val price:Int = intent.getIntExtra("price",0)
        val counter:Int = intent.getIntExtra("counter",0)

        btn_save = findViewById(R.id.btn_save)
        price_tv = findViewById(R.id.price_tv)

        price_tv.text = "$"+price
        val seek = findViewById<SeekBar>(R.id.seekBar)
        seek.max = 10
        seek?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                // write custom code for progress is changed
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            @RequiresApi(Build.VERSION_CODES.O)
            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped

                total = seek.progress *price

                price_tv.text = "$"+total


                //  Adding days
                val current = LocalDateTime.now()

                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                val formatted = current.format(formatter)

                val dt = formatted // Start date

                val sdf = SimpleDateFormat("yyyy-MM-dd")
                val c: Calendar = Calendar.getInstance()
                try {
                    c.setTime(sdf.parse(dt))
                } catch (e: ParseException) {
                    e.printStackTrace()
                }
                c.add(
                    Calendar.DATE,
                    seek.progress
                ) // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE

                val sdf1 = SimpleDateFormat("MM-dd-yyyy")
                output = sdf1.format(c.getTime())



            }
        })

        btn_save.setOnClickListener {


            if(seek.progress==0){
                val snack = Snackbar.make(it,"Must select some days!",Snackbar.LENGTH_LONG)
                snack.show()
            }else{

                Toast.makeText(this@DaysActivity, "Good Choice " , Toast.LENGTH_SHORT).show()

                val sharedPreferences: SharedPreferences = this.getSharedPreferences("sharedPrefFile", Context.MODE_PRIVATE)
                val editor:SharedPreferences.Editor =  sharedPreferences.edit()
                editor.putString("date","Due back "+output)
                editor.putInt("counter",counter)
                editor.apply()
                editor.commit()
                saveNotPressed = false
                onBackPressed()

            }


        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        if(saveNotPressed){
            Toast.makeText(this@DaysActivity,
                "Keep exploring for the right bike" ,
                Toast.LENGTH_SHORT).show()
        }

    }
}
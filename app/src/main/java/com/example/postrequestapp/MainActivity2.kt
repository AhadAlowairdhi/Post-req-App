package com.example.postrequestapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity2 : AppCompatActivity() {
    lateinit var saveBtn : Button
    lateinit var viewBtn : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val edName = findViewById(R.id.edName) as EditText
        val edLocation = findViewById(R.id.edLoc) as EditText
        saveBtn = findViewById(R.id.svBtn)
        viewBtn = findViewById(R.id.vBtn)

        saveBtn.setOnClickListener {
        val name = edName.text.toString()
        val location = edLocation.text.toString()

            apiInterface!!.addUser(User(name, location)).enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    Toast.makeText(applicationContext, "Save Successful", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(applicationContext, "Not Save Successful", Toast.LENGTH_LONG).show()
                }
            })
        }

        viewBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

}
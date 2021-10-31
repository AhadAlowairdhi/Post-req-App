package com.example.postrequestapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.postrequestapp.R
import com.example.postrequestapp.model.APIClient
import com.example.postrequestapp.model.APIInterface
import com.example.postrequestapp.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity2 : AppCompatActivity() {
    lateinit var saveBtn : Button
    lateinit var viewBtn : Button
    val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
    lateinit var edName : EditText
    lateinit var edLocation : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
      // init UI
        init()

        saveBtn.setOnClickListener {
            saveUinfo()
        }

        viewBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    fun init(){
        edName = findViewById(R.id.edName)
        edLocation = findViewById(R.id.edLoc)
        saveBtn = findViewById(R.id.svBtn)
        viewBtn = findViewById(R.id.vBtn)
    }
    fun saveUinfo(){
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

        //clear all editTexts
        edName.text.clear()
        edName.clearFocus()
        edLocation.text.clear()
        edLocation.clearFocus()
    }

}
package com.example.postrequestapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var rvUsers : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvUsers = findViewById(R.id.RVusers)

        val addNew = findViewById(R.id.addBtn) as Button

        addNew.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }


        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        apiInterface!!.getUsers().enqueue(object : Callback<ArrayList<User>>{
            override fun onResponse(
                call: Call<ArrayList<User>>,
                response: Response<ArrayList<User>>
            ) {
                addNuwer(response.body()!!)
            }

            override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                println("Failed")
            }
        })


    }

    private fun addNuwer(list: ArrayList<User>){
        rvUsers.adapter = rvAdapter(list)
        rvUsers.layoutManager = LinearLayoutManager(this)
    }
}
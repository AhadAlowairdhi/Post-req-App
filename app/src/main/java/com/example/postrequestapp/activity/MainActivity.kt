package com.example.postrequestapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.postrequestapp.R
import com.example.postrequestapp.UserList
import com.example.postrequestapp.model.APIClient
import com.example.postrequestapp.model.APIInterface
import com.example.postrequestapp.classes.rvAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var rvUsers : RecyclerView
    lateinit var listalluser : ArrayList<UserList>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvUsers = findViewById(R.id.RVusers)
        listalluser = ArrayList()

        rvUsers.adapter = rvAdapter(listalluser)
        rvUsers.layoutManager = LinearLayoutManager(this)

        val addNew = findViewById(R.id.addBtn) as Button
        addNew.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }
        val updtUser = findViewById(R.id.updtBtn) as Button
        updtUser.setOnClickListener {
            startActivity(Intent(this, MainActivity3::class.java))
        }
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val call: Call<List<UserList?>> = apiInterface!!.getUsers()


        call?.enqueue(object : Callback<List<UserList?>>{
            override fun onResponse(call: Call<List<UserList?>>, response: Response<List<UserList?>>
            ) {
                val resource: List<UserList?>? = response.body()
                if (resource != null){
                    for (i in resource){
                        listalluser.add(UserList(i!!.pk,i.name,i.location))
                        rvUsers.adapter?.notifyDataSetChanged()
                        rvUsers.scrollToPosition(listalluser.size-1)
                    }
                }
            }

            override fun onFailure(call: Call<List<UserList?>>, t: Throwable) {
                call.cancel()
                Log.d("MainActivity","${t.message}")
            }
        })

    }
}

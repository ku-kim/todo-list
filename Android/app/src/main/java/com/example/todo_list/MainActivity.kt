package com.example.todo_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageButton
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import com.example.todo_list.databinding.ActivityMainBinding
import com.example.todo_list.model.HistoryCard
import com.example.todo_list.model.HistoryReceive
import com.example.todo_list.model.Todos
import com.google.android.material.navigation.NavigationView
import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var navigationView: NavigationView
    lateinit var drawerLayout: DrawerLayout

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        drawerLayout = findViewById(R.id.main_layout)

        binding.btnMenu.setOnClickListener { drawerLayout.openDrawer(GravityCompat.END) }

        navigationView = findViewById(R.id.navi_view)
        navigationView.setNavigationItemSelectedListener(this)

        binding.btnNetwork2?.setOnClickListener {
            HistoryReceive.service.getHistory("histories")?.enqueue(object : Callback<List<HistoryCard>> {

                override fun onResponse(call: Call<List<HistoryCard>>, response: Response<List<HistoryCard>>) {
                    Log.d("onResponse", "${response.body()}")
                    Log.d("onResponse", response.message())

                }

                override fun onFailure(call: Call<List<HistoryCard>>, t: Throwable) {
                    Log.d("onFailure", "통신 실패")
                    Log.d("onFailure", "${t.message}")
                    //<Todos>는 Array(배열)지만 예상한것은 Object(객체)였음
                    // <JsonArray>로 하면 받아지는데 왜? >> <JsonArray>는 객체를 받아서 배열로바꿔줌?.
                }
            })
        }

        binding.btnNetwork?.setOnClickListener {
            HistoryReceive.service.getTodos("todos")?.enqueue(object : Callback<List<Todos>> {

                override fun onResponse(call: Call<List<Todos>>, response: Response<List<Todos>>) {
                    Log.d("onResponse", "${response.body()}")
                    Log.d("onResponse", response.message())
                }

                override fun onFailure(call: Call<List<Todos>>, t: Throwable) {
                    Log.d("onFailure", "통신 실패")
                    Log.d("onFailure", "${t.message}")
                    Log.d("onFailure", "${call}")
                    //<Todos>는 Array(배열)지만 예상한것은 Object(객체)였음
                    // <JsonArray>로 하면 받아지는데 왜? >> <JsonArray>는 객체를 받아서 배열로바꿔줌?.
                }
            })
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return false
    }
}
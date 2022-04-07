package com.example.todo_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo_list.databinding.ActivityMainBinding
import com.example.todo_list.history.HistoryAdapter
import com.example.todo_list.history.HistoryCard
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val itemList = listOf(
            HistoryCard(1, "가나다", "ABC", "1분전"),
            HistoryCard(2, "라마바", "DEF", "2분전"),
            HistoryCard(3, "사아자", "GHI", "3분전"),
            HistoryCard(4, "차카타", "JKL", "4분전"),
        )
        val testLiveData = MutableLiveData<List<HistoryCard>>()

        val adapter = HistoryAdapter()
        binding.recyclerviewHistory.adapter = adapter
        binding.recyclerviewHistory.layoutManager = LinearLayoutManager(this)
        testLiveData.observe(this) {
            adapter.submitList(it)
        }

        testLiveData.value = itemList

        binding.btnMenu.setOnClickListener {
            binding.mainLayout.openDrawer(GravityCompat.END)
        }
        binding.btnClose.setOnClickListener { binding.mainLayout.closeDrawer(GravityCompat.END) }
        binding.naviView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return false
    }
}
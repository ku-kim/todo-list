package com.example.todo_list

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo_list.data.TasksRepository
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.todo_list.databinding.ActivityMainBinding
import com.example.todo_list.history.HistoryAdapter
import com.example.todo_list.history.HistoryViewModel
import com.example.todo_list.todo.TodoAdapter
import com.example.todo_list.todo.data.TodosCard
import com.example.todo_list.todo.ItemTouchHelperCallback
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var historyViewModel: HistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        historyViewModel = ViewModelProvider(this, ViewModelFactory(TasksRepository())).get(HistoryViewModel::class.java)

        val historyAdapter = HistoryAdapter()
        val todosAdapter = TodoAdapter()

        binding.recyclerviewHistory.adapter = historyAdapter
        binding.recyclerviewTodo.adapter = todosAdapter

        binding.recyclerviewTodo.setHasFixedSize(true)

        val touchHelper = ItemTouchHelperCallback(todosAdapter)
        val helper = ItemTouchHelper(touchHelper)

        helper.attachToRecyclerView(binding.recyclerviewTodo)

        historyViewModel = ViewModelProvider(this, ViewModelFactory(TasksRepository())).get(HistoryViewModel::class.java)

        binding.btnMenu.setOnClickListener {
            binding.mainLayout.openDrawer(GravityCompat.END)
            historyViewModel.getHistories()
        }

        binding.btnClose.setOnClickListener { binding.mainLayout.closeDrawer(GravityCompat.END) }
        binding.naviView.setNavigationItemSelectedListener(this)

        historyViewModel.historyList.observe(this) { historyAdapter.submitList(it) }
        historyViewModel.checkLoading.observe(this) {
            if (it) {
                binding.spinner.visibility = View.VISIBLE
                binding.recyclerviewHistory.visibility = View.GONE
            } else {
                binding.spinner.visibility = View.GONE
                binding.recyclerviewHistory.visibility = View.VISIBLE
            }
        }

        val test = TodosCard(
            1,
            "테스트하기1",
            "콘텐츠테스트1",
            "jung",
            "doing",
            "2022-04-06T15:30:00.000+09:00",
            "2022-04-06T15:30:00.000+09:00"
        )

        fun aciton() = test.contents

        val test2 = TodosCard(
            2,
            "테스트하기2",
            "콘텐츠테스트2",
            "park",
            "todo",
            "2022-04-06T15:30:00.000+09:00",
            "2022-04-06T15:30:00.000+09:00"
        )

        todosAdapter.submitList(listOf(test, test2))

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return false
    }
}
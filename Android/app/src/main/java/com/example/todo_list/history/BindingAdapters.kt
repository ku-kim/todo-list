package com.example.todo_list.history

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("setUsername")
fun setUsername(view: TextView, username: String) {
    view.text = username
}

@BindingAdapter("setBody")
fun setBody(view: TextView, body: String) {
    view.text = body
}

@BindingAdapter("setTimeStamp")
fun setTimeStamp(view: TextView, timestamp: String) {
    view.text = timestamp
}

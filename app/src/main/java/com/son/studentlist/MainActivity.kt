package com.son.studentlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.component_button_insert.*


class MainActivity : AppCompatActivity() {
    private lateinit var studentAdapter : StudentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        b_insert_student.setOnClickListener {
            val intent = Intent(this, InsertActivity::class.java)
            startActivity(intent)
        }

        val dataHelper = DataHelper(this)
        Log.d("read", "read semua data")
        val studentlist = dataHelper.getAllStudent()
        studentAdapter = StudentAdapter(this@MainActivity, studentlist)
        rv_student.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = studentAdapter
        }

        refresh_swipe.setOnRefreshListener {
            refresh_swipe.isRefreshing = false
            studentAdapter.getUpdate()
        }
    }

    override fun onResume() {
        super.onResume()
        studentAdapter.getUpdate()
    }
}

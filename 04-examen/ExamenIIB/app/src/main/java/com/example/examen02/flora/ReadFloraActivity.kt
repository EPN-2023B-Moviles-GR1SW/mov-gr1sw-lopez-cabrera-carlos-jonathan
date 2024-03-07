package com.example.examen02.flora

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examen02.R
import com.example.examen02.service.CRUDService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReadFloraActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var sistemasReadAdapter: ReadFloraAdapter
    private lateinit var crudService: CRUDService
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_flora)

        recyclerView = findViewById(R.id.floraRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        crudService = CRUDService(this)

        CoroutineScope(Dispatchers.Main).launch {
            val floraList = crudService.leerFlora()
            sistemasReadAdapter = ReadFloraAdapter(floraList)
            recyclerView.adapter = sistemasReadAdapter
        }
    }
}
package com.example.studentapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentapp.students.CreateAndEdit
import com.example.studentapp.students.adapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory





class MainActivity : AppCompatActivity() {

    private  var studentsRest = mutableListOf<Student>()
    private lateinit var  adapter: adapter

    override fun onResume() {
        super.onResume()
        // refresh here
        allStudent()
    }

    private fun checkValue() {
        val intent = Intent(this, CreateAndEdit::class.java)
        this.startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvCreateStudent.setOnClickListener {
            checkValue()
        }

        initRecycler()
        allStudent()



    }

    private fun initRecycler(){
        rvStudent.layoutManager = LinearLayoutManager(this)
        adapter = adapter(studentsRest)

        rvStudent.adapter = adapter
    }

    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://studentapp58.herokuapp.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchStudentByCI(query:String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIservice::class.java).getStudentByCI("students/detail?ci=$query")
            val student = call.body()
        }
    }

    private fun allStudent(){
        CoroutineScope(Dispatchers.IO).launch {

            val call = getRetrofit().create(APIservice::class.java).getStudentAll("students/all")
            val student = call.body()

            runOnUiThread {
                if (call.isSuccessful){
                    studentsRest.clear()
                    val dataStudent = student?.data?: emptyList()
                    studentsRest.addAll(dataStudent)
                    adapter.notifyDataSetChanged()


                }

            }

        }
    }




}


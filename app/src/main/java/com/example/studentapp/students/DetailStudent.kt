package com.example.studentapp.students

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentapp.APIservice
import com.example.studentapp.R
import com.example.studentapp.record.Record
import com.example.studentapp.record.RecordAdapter
import com.example.studentapp.subject.CreateSubject
import kotlinx.android.synthetic.main.activity_detail_student.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class detailStudent : AppCompatActivity() {


    private  var recordsRest = mutableListOf<Record>()
    private lateinit var  adapter: RecordAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_student)




        val bundle = intent.extras
        val idStudent = bundle?.get("ID_STUDENT").toString()
        val studentName = bundle?.get("INTENT_NAME").toString()


        getUsername()
        initRecycler(studentName)
        allRecordsByStudent(idStudent)

        btnBack.setOnClickListener{ onBackPressed()}
        btnAdd.setOnClickListener { checkValue(idStudent,studentName)}
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://studentapp58.herokuapp.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun allRecordsByStudent(query:String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIservice::class.java).getRecordsByStudent("students/records/all?student_id=$query")
            val records = call.body()

            runOnUiThread {
                if(call.isSuccessful){
                    recordsRest.clear()
                    val dataRecords = records?.data?: emptyList()
                    recordsRest.addAll(dataRecords)
                    adapter.notifyDataSetChanged()

                }
                else{
                    //test

                }


            }

        }
    }

    private  fun checkValue(studentId:String,studentName:String) {
        val intentSent = Intent(this, CreateSubject::class.java)
        intentSent.putExtra("ID_STUDENT",studentId);
        intentSent.putExtra("INTENT_NAME",studentName);

        Toast.makeText(this, studentId, Toast.LENGTH_SHORT).show()
        startActivity(intentSent)
    }

    private fun getUsername(){
        val bundle = intent.extras
        val nameStudent = bundle?.get("INTENT_NAME")
        tvDetail.text = nameStudent.toString()
    }


    private fun initRecycler(studentName:String){
        rvRecord.layoutManager = LinearLayoutManager(this)
        adapter = RecordAdapter(recordsRest,studentName)
        rvRecord.adapter = adapter

    }

}


package com.example.studentapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_detail_student.btnBack
import kotlinx.android.synthetic.main.activity_subject.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SubjectActivity : AppCompatActivity() {

    //private  lateinit var studentRest:StudentDetail
    private  var  subjects = mutableListOf<SubjectRecord>()
    private lateinit var adapter: SubjectAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subject)

        val bundle = intent.extras
        val studentID = bundle?.get("ID_STUDENT").toString()
        val recordID = bundle?.get("ID_RECORD").toString()





        initRecycler()

        //getStudentByCI(studentID)
        getUsername()

        getRecordByIdStudent(recordID)
        btnBack.setOnClickListener{ onBackPressed()}

    }


    private fun getUsername(){
        val bundle = intent.extras
        val studentName = bundle?.get("INTENT_NAME")
        tvDetailStudent.text = studentName.toString()
        }


    private fun initRecycler(){
        rvSubject.layoutManager = LinearLayoutManager(this)
        adapter = SubjectAdapter(subjects)
        rvSubject.adapter = adapter
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://studentapp58.herokuapp.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    /*
    private fun getStudentByCI(query:String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIservice::class.java).getStudentByCI("students/detail?ci=$query")
            val studentResponse = call.body()

            if (call.isSuccessful){
                if (studentResponse != null) {
                    studentRest = studentResponse
                }

            }else{
                //showError()
            }

        }
    }

     */

    private fun showError(data:String) {
        Toast.makeText(this,data, Toast.LENGTH_SHORT).show()
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun getRecordByIdStudent(query:String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIservice::class.java).recordByIDResponse("students/records/detail?record_id=$query")
            val responseRecordDetail = call.body()

            runOnUiThread{
                if (call.isSuccessful){
                    //showError(responseRecordDetail?.data?.subjects.toString())
                    subjects.clear()
                    var dataSubjects = responseRecordDetail?.data?.subjects
                    if (dataSubjects != null) {
                        subjects.addAll(dataSubjects)
                    }
                    adapter.notifyDataSetChanged()

                }

        }

    }
}
}



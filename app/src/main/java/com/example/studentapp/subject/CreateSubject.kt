package com.example.studentapp.subject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentapp.APIservice
import com.example.studentapp.record.CreateRecordAdapter
import com.example.studentapp.R
import com.example.studentapp.record.RecordCreate
import com.example.studentapp.record.RecordTable
import com.example.studentapp.record.Subject
import kotlinx.android.synthetic.main.activity_create_subject.*
import kotlinx.android.synthetic.main.activity_create_subject.btnBack

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CreateSubject : AppCompatActivity() {

    private var recordsRest = mutableListOf<RecordTable>()
    private var recordsTablesRes = mutableListOf<String>()


    private lateinit var  adapter: CreateRecordAdapter
    private  var year:Int = 1;

    private var newRecord = RecordCreate(0,"A",0,Subjects = mutableListOf<Subject>())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_subject)
        btnBack.setOnClickListener{ onBackPressed()}

        radioGroup.setOnCheckedChangeListener{ group, checkedId ->
            year = checkvalue(checkedId)
            searchRecordTableByYear(year.toString())

            initRecycler()

        }

        newRecord.student_id = getStudent()

    }




    private fun getStudent(): Int {
        val bundle = intent.extras
        return bundle?.get("ID_STUDENT").toString().toInt()
    }

    private fun checkvalue(checkedId:Int): Int {
        var year:Int
        if (checkedId == radio1.id){ year = radio1.text.toString().toInt() }
         findViewById<RadioButton>(checkedId).apply {year = this.text.toString().toInt() }

        var text = "Haz seleccionado: $year year escolar"
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        return year
    }

    private  fun recordDoneRead(studentId:Int, idRecord:Int) {
        //Funcion para enviar y leer el nuevo Record Academico
        val intent = Intent(this, SubjectActivity::class.java)
        intent.putExtra("ID_STUDENT",getStudent())
        intent.putExtra("ID_RECORD",idRecord)
        this.startActivity(intent)
    }

    private fun initRecycler(){
        rvRecordEdit.layoutManager = LinearLayoutManager(this)


        adapter = CreateRecordAdapter(year,getStudent(),newRecord)
        rvRecordEdit.adapter = adapter

        btnCreateDone.setOnClickListener{
            //AL pulsar se enviara la peticion
            newRecord.Subjects = adapter.getDataAll
            //records.add(newRecord)
            //recordDoneRead(newRecord.IdStudent,newRecord.idRecord)
           // Toast.makeText(this, adapter.getDataAll.toString(), Toast.LENGTH_SHORT).show()
            sendRecord(newRecord)
        }


    }

    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://studentapp58.herokuapp.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchRecordTableByYear(query:String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIservice::class.java).getRecordsByYears("records/detail?school_year=$query")
            val recordTable = call.body()

            runOnUiThread {

                if (call.isSuccessful){
                    newRecord.Subjects.clear()
                    newRecord.school_year = query.toInt()

                    var dataRecordTable = recordTable?.data?.titles?: emptyList()
                    for(title in dataRecordTable){
                        newRecord.Subjects.add(Subject(title, mutableListOf(0,0,0)))
                    }
                    //recordsTablesRes.addAll(dataRecordTable)
                    //showError(recordsTablesRes.toString())
                    //showError("test peticio $query")
                    adapter.notifyDataSetChanged()

                }

            }

        }
    }

    private fun showError(data:String) {
        Toast.makeText(this,data,Toast.LENGTH_SHORT).show()
    }


    private fun sendRecord(data: RecordCreate){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIservice::class.java).postAddRecord(data)
            val record = call.body()

            runOnUiThread{
                if (call.isSuccessful){

                    if (record != null) {
                        var message = record.message
                        showError("Message: $message")
                        onBackPressed()

                    }
                }else{
                    //showError()
                }
            }
        }
    }


}
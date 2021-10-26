package com.example.studentapp.students

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.example.studentapp.APIservice
import com.example.studentapp.R
import com.example.studentapp.StudentCreate
import kotlinx.android.synthetic.main.activity_create_and_edit.*
import kotlinx.android.synthetic.main.create_edit_student.btnAdd
import kotlinx.android.synthetic.main.create_edit_student.btnBack
import kotlinx.android.synthetic.main.create_edit_student.tvCI
import kotlinx.android.synthetic.main.create_edit_student.tvFirstName
import kotlinx.android.synthetic.main.create_edit_student.tvIsActive
import kotlinx.android.synthetic.main.create_edit_student.tvLastName
import kotlinx.android.synthetic.main.create_edit_student.tvPassword
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CreateAndEdit : AppCompatActivity() {
    private  var student = StudentCreate()

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_and_edit)

        initRecycler()
        btnBack.setOnClickListener { onBackPressed() }

    }

    private fun initRecycler() {

        tvFirstName.doAfterTextChanged{
            student.FirstName = tvFirstName.text.toString()
        }

        tvLastName.doAfterTextChanged {
            student.LastName = tvLastName.text.toString()
        }

        tvEmail.doAfterTextChanged {
            student.Email = tvEmail.text.toString()
        }

        tvCI.doAfterTextChanged {
            student.CI = tvCI.text.toString()
        }

        tvIsActive.doAfterTextChanged {
            student.IsActive = tvIsActive.isChecked
        }

        tvPassword.doAfterTextChanged {
            student.Password = tvPassword.text.toString()
        }


        btnAdd.setOnClickListener {
            sendStudent(student)
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://studentapp58.herokuapp.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun showMessage(data:String) {
        Toast.makeText(this,data, Toast.LENGTH_SHORT).show()
    }

    private fun sendStudent(data: StudentCreate){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIservice::class.java).postAddStudent(data)
            val student = call.body()

            runOnUiThread{
                if (call.isSuccessful){

                    if (student != null) {
                        var message = student.message
                        //showMessage(message.toString())
                        onBackPressed()

                    }

                }else{
                    //showError()
                }
            }
        }
    }

}
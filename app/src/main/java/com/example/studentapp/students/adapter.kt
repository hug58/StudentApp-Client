package com.example.studentapp.students

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.studentapp.R
import com.example.studentapp.Student
import kotlinx.android.synthetic.main.item_student.view.*

class adapter (private val student:List<Student>):
    RecyclerView.Adapter<adapter.StudentHolder>() {
    class StudentHolder (private val view: View):RecyclerView.ViewHolder(view){

        private fun showMessage(view:View){
            Toast.makeText(view.context,"Tienes que darle al boton, crack", Toast.LENGTH_SHORT).show()
        }

        fun checkValue(view: View) {
            val intent = Intent(view.context, detailStudent::class.java)
            intent.putExtra("INTENT_NAME","${view.tvFirstName.text} ${view.tvLastName.text}")
            intent.putExtra("ID_STUDENT",view.tvID.text)


            view.context.startActivity(intent)
        }

        fun render(student: Student){

            view.tvID.text = student.ID.toString()

            view.tvFirstName.text = student.FirstName
            view.tvLastName.text = student.LastName
            view.tvIsActive.isChecked = student.IsActive
            view.tvIsActive.isClickable = false

            view.btnDetail.setOnClickListener{
                //showMessage(view)
                checkValue(view)
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return StudentHolder(layoutInflater.inflate(R.layout.item_student, parent, false))
    }

    override fun onBindViewHolder(holder: StudentHolder, position: Int) {
        holder.render(student[position])
    }

    override fun getItemCount(): Int = student.size



}
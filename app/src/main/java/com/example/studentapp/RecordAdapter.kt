package com.example.studentapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_detail_student.*
import kotlinx.android.synthetic.main.item_record.view.*
import kotlinx.android.synthetic.main.item_student.view.*


class RecordAdapter(private val records: List<Record>,private val studentName:String):
    RecyclerView.Adapter<RecordAdapter.RecordHolder>() {
    class RecordHolder (private val view: View): RecyclerView.ViewHolder(view){

        private  fun checkValue(student_id:Int,studentName:String) {
            val intent = Intent(view.context,SubjectActivity::class.java)
            intent.putExtra("ID_STUDENT",student_id)
            intent.putExtra("INTENT_NAME",studentName)
            intent.putExtra("ID_RECORD",view.tvRecordID.text.toString().toInt())
            view.context.startActivity(intent)
        }

        fun render(record: Record,position: Int, studentName:String){
            view.tvPosition.text = position.toString()
            view.tvRecordID.text = record.id.toString()
            view.tvRecordYear.text = record.school_year.toString()
            view.tvRecordSection.text = record.section



            view.btnRecordRead.setOnClickListener{
                //showMessage(view)
                checkValue(record.student_id,studentName)
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RecordHolder(layoutInflater.inflate(R.layout.item_record, parent, false))
    }

    override fun onBindViewHolder(holder: RecordHolder, position: Int) {
        holder.render(records[position],position,studentName)
    }

    override fun getItemCount(): Int = records.size



}
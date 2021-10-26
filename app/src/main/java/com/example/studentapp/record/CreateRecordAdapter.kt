package com.example.studentapp.record

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.studentapp.R
import kotlinx.android.synthetic.main.edit_item_subject.view.*


class CreateRecordAdapter(val year:Int, val idStudent:Int, private val record: RecordCreate):
    RecyclerView.Adapter<CreateRecordAdapter.CreateRecordHolder>(){

        var getDataAll:MutableList<Subject> = record.Subjects

        //private var subjects:MutableList<Subject> = mutableListOf<Subject>()
        //private var newRecord: Record = Record(idStudent,"A",year,subjects)

        inner class CreateRecordHolder(private val view: View):RecyclerView.ViewHolder(view){

            fun render(subject: Subject, position: Int){
                view.tvSubjectTitle.text = subject.Title


                /*
                view.setOnClickListener{
                    var d = view.tvSubjectNote.text
                    Toast.makeText(view.context, d, Toast.LENGTH_SHORT).show()

                }
                 */

                //val y = x?.toDouble() ?: 0.0

                view.tvSubjectNote.doAfterTextChanged {
                    try {
                        subject.Notes[0] = view.tvSubjectNote.text.toString().toInt()
                        getDataAll[position] = subject

                    }
                    catch (e: NumberFormatException){

                    }

                }


                view.tvSubjectNote2.doAfterTextChanged {
                    try{
                        subject.Notes[1] = view.tvSubjectNote2.text.toString().toInt()
                        getDataAll[position] = subject
                    }
                    catch(e: NumberFormatException){

                    }

                }


                view.tvSubjectNote3.doAfterTextChanged {
                    try {
                        subject.Notes[2] = view.tvSubjectNote3.text.toString().toInt()
                    getDataAll[position] = subject //subject.Note = mutableListOf(note1,note2,note3)
                    }

                    catch(e: NumberFormatException){
                        //value == ""

                    }
                }


            }
        }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreateRecordHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CreateRecordHolder(layoutInflater.inflate(R.layout.edit_item_subject, parent,false))
    }

    override fun onBindViewHolder(holder: CreateRecordHolder, position: Int) {
        holder.render(record.Subjects[position],position)
    }

    //fun getData(): MutableList<Subject> = getDataAll

    override fun getItemCount(): Int = record.Subjects.size

}

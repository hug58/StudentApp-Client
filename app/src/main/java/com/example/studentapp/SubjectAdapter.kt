package com.example.studentapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_subject.view.*

class SubjectAdapter(private val subjects: MutableList<SubjectRecord>): RecyclerView.Adapter<SubjectAdapter.SubjectHolder>() {
    class SubjectHolder(private val view: View): RecyclerView.ViewHolder(view) {
        fun render(subject: SubjectRecord) {
            view.tvSubjectTitle.text = subject.Title

            view.tvSubjectNote.text = subject.Notes[0].toString()
            view.tvSubjectNote2.text = subject.Notes[1].toString()
            view.tvSubjectNote3.text = subject.Notes[2].toString()

            var noteFinal:Int = 0
            for (note  in subject.Notes){ noteFinal += note}

            noteFinal /= 3

            view.tvSubjectNoteFinal.text = noteFinal.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SubjectHolder(layoutInflater.inflate(R.layout.item_subject,parent,false))
    }

    override fun onBindViewHolder(holder: SubjectHolder, position: Int) {

        holder.render(subjects[position])
    }

    override fun getItemCount(): Int = subjects.size
}


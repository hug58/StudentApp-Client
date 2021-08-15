package com.example.studentapp

import com.google.gson.annotations.SerializedName

data class StudentDetail(
    @SerializedName("ci") var CI:String,
    @SerializedName("first_name") var FirstName:String,
    @SerializedName("email") val Email:String,
    @SerializedName("is_active") var IsActive:Boolean,
    @SerializedName("last_name") var LastName:String,
    @SerializedName("id") val ID:Int,

)

data class Student(
    @SerializedName("ci") var CI:String,
    @SerializedName("first_name") var FirstName:String,
    @SerializedName("email") val Email:String,
    @SerializedName("is_active") var IsActive:Boolean,
    @SerializedName("last_name") var LastName:String,
    @SerializedName("id") val ID:Int,
    @SerializedName("hashed_password") var Password:String

)

data class Subject(
    @SerializedName ("title") val Title:String,
    @SerializedName ("notes") var Notes:MutableList<Int>,
)



data class Record(
    @SerializedName("id") var id:Int,
    @SerializedName("school_year") var school_year:Int,
    @SerializedName("student_id") var student_id:Int,
    @SerializedName("section") var section:String,
    @SerializedName("subjects") var Subjects:MutableList<Subject>
)

data class RecordCreate(
    @SerializedName("school_year") var school_year:Int=0,
    @SerializedName("section") var section:String,
    @SerializedName("student_id") var student_id: Int? =null,
    @SerializedName("subjects") var Subjects:MutableList<Subject>


)

data class RecordMeta(
    @SerializedName("id") var id:Int,
    @SerializedName("school_year") var school_year:Int,
    @SerializedName("student_id") var student_id:Int,
    @SerializedName("section") var section:String,
)

data class SubjectRecord(
    @SerializedName ("id") val id:Int,
    @SerializedName ("title") val Title:String,
    @SerializedName ("record_id") val record_id:Int,
    @SerializedName ("notes") var Notes:MutableList<Int>,
)

data class RecordDetail(
    @SerializedName("record") var record:RecordMeta,
    @SerializedName("subjects") var subjects: MutableList<SubjectRecord>
)

data class RecordResponseDetail(
    @SerializedName("data") var data: RecordDetail
)

data class RecordResponse(
    @SerializedName("data") var data: List<Record>
    )

data class RecordTable(
    @SerializedName("school_year") var school_year:Int,
    @SerializedName("id") var id: Int,
    @SerializedName("titles") var titles:MutableList<String>


)

data class RecordTableResponse(
    @SerializedName("data") var data: RecordTable
)


data class StudentCreate(
    @SerializedName("ci") var CI:String="",
    @SerializedName("first_name") var FirstName:String="",
    @SerializedName("email") var Email:String="",
    @SerializedName("is_active") var IsActive:Boolean=false,
    @SerializedName("last_name") var LastName:String="",
    @SerializedName("hashed_password") var Password:String=""
)

data class messageRespond(
    @SerializedName("success") var message:Boolean
)

data class StudentResponse (
    @SerializedName("data") var data:List<Student>
)

data class StudentsName(@SerializedName("data") var data: List<String>)







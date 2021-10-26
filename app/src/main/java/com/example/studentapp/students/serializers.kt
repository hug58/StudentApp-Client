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







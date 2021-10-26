package com.example.studentapp

import com.example.studentapp.record.RecordCreate
import com.example.studentapp.record.RecordResponse
import com.example.studentapp.record.RecordResponseDetail
import com.example.studentapp.record.RecordTableResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface APIservice {

    @GET
    suspend fun getStudentAll(@Url url:String): Response<StudentResponse>

    @GET
    suspend fun getStudentByCI(@Url url:String): Response<StudentDetail>

    @GET
    suspend fun getRecordsByYears(@Url url: String): Response<RecordTableResponse>

    @GET
    suspend fun getRecordsByStudent(@Url url: String): Response<RecordResponse>

    @GET
    suspend fun recordByIDResponse(@Url url: String): Response<RecordResponseDetail>

    @GET
    suspend fun getStudentName(@Url url:String): Response<StudentsName>

    @Headers("Content-Type: application/json")
    @POST("students/records/create")
    suspend fun postAddRecord(@Body requestBody: RecordCreate): Response<messageRespond>

    @Headers("Content-Type: application/json")
    @POST("students/create")
    suspend fun postAddStudent(@Body requestBody: StudentCreate): Response<messageRespond>




}
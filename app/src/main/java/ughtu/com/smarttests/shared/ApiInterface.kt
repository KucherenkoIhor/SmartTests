package ughtu.com.smarttests.shared

import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable
import ughtu.com.smarttests.model.*

/**
 * Created by igor on 01.12.16.
 */
interface ApiInterface {

    @GET("/api/groups")
    fun getGroups() : Observable<List<Group>>

    @GET("/api/subjects")
    fun getSubjects() : Observable<List<Subject>>

    @GET("/api/lectures/{subjectId}")
    fun getLectures(@Path("subjectId") subjectId: Long) : Observable<List<Lecture>>

    @GET("/api/tests/{lectureId}")
    fun getQuestions(@Path("lectureId") lectureId: Long) : Observable<List<Question>>

    @GET("/api/answers/{questionId}")
    fun getAnswers(@Path("questionId") questionId: Long) : Observable<List<Answer>>

}
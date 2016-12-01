package ughtu.com.smarttests.shared

import retrofit2.http.GET
import rx.Observable
import ughtu.com.smarttests.model.Group
import ughtu.com.smarttests.model.Subject

/**
 * Created by igor on 01.12.16.
 */
interface ApiInterface {

    @GET("/api/groups")
    fun getGroups() : Observable<List<Group>>

    @GET("/api/subjects")
    fun getSubjects() : Observable<List<Subject>>
}
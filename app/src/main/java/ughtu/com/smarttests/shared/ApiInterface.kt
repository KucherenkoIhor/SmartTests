package ughtu.com.smarttests.shared

import retrofit2.http.GET
import rx.Observable
import ughtu.com.smarttests.model.Group

/**
 * Created by igor on 01.12.16.
 */
interface ApiInterface {

    @GET("/api/groups")
    fun getGroups() : Observable<List<Group>>
}
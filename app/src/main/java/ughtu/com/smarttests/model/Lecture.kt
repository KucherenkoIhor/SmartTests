package ughtu.com.smarttests.model

import com.google.gson.annotations.SerializedName

/**
 * Created by igor on 02.12.16.
 */
class Lecture {

    @SerializedName("subjectId")
    var subjectId: Long? = null

    @SerializedName("id")
    var id: Long? = null

    @SerializedName("name")
    var name: String? = null

}
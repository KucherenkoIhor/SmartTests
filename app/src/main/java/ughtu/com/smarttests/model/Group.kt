package ughtu.com.smarttests.model

import com.google.gson.annotations.SerializedName

/**
 * Created by igor on 01.12.16.
 */
class Group {

    @SerializedName("id")
    var id: Long? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("pass")
    var pass: String? = null

}
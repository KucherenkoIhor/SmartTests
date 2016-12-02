package ughtu.com.smarttests.model

import com.google.gson.annotations.SerializedName

/**
 * Created by igor on 02.12.16.
 */
class Question {


    @SerializedName("lectureId")
    var lectureId: Long? = null

    @SerializedName("id")
    var id: Long? = null

    @SerializedName("isHidden")
    var isHidden: Boolean? = null

    @SerializedName("text")
    var text: String? = null

    var answers: List<Answer>? = null

}
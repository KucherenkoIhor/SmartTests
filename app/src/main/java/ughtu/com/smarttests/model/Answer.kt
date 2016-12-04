package ughtu.com.smarttests.model

import com.google.gson.annotations.SerializedName

/**
 * Created by igor on 02.12.16.
 */
class Answer {

    @SerializedName("questionId")
    var questionId: Long? = null

    @SerializedName("id")
    var id: Long? = null

    @SerializedName("isCorrect")
    var isCorrect: Boolean? = null

    @SerializedName("text")
    var text: String? = null

    var isChecked = false

}
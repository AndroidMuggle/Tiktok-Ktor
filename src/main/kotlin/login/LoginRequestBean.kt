package login

import com.google.gson.annotations.SerializedName

data class LoginRequestBean(
    @SerializedName("avatar")
    val avatar: String?,
    @SerializedName("captchaCode")
    val captchaCode: String?,
    @SerializedName("password")
    val password: String?,
    @SerializedName("phoneNumber")
    val phoneNumber: String,
    @SerializedName("username")
    val username: String?
)
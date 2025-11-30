package login

import com.google.gson.annotations.SerializedName

data class LoginResponseBean(
    @SerializedName("avatar")
    val avatar: String?,
    @SerializedName("password")
    val password: String?,
    @SerializedName("phoneNumber")
    val phoneNumber: String?,
    @SerializedName("tiktokId")
    val tiktokId: String?,
    @SerializedName("userId")
    val userId: String?,
    @SerializedName("username")
    val username: String?
)
package login

import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id

data class UserBean(
    @BsonId
    val id: Id<UserBean>,
    val userId: String,
    val username: String,
    val password: String,
    val phoneNumber: String,
    val avatar: String,
    val tiktokId: String,
) {

    fun convert2LoginResponseBean(): LoginResponseBean {
        return LoginResponseBean(
            userId = userId,
            avatar = avatar,
            password = password,
            phoneNumber = phoneNumber,
            tiktokId = tiktokId,
            username = username,
        )
    }
}
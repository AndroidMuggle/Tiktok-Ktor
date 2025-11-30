package com.muggle.login

import com.muggle.common.TiktokLogger
import common.CommonResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import login.LoginRequestBean
import login.UserBean
import org.bson.types.ObjectId
import org.litote.kmongo.id.toId
import java.util.*

fun Application.loginRouter() {

    val loginService = LoginService()
    routing {
        /**
         * 密码登录
         */
        get("/login/loginByPassword") {
            runCatching {
                val loginParams = call.receive<LoginRequestBean>()
                val userBean = loginService.findByPhone(loginParams.phoneNumber)
                if (userBean != null && userBean.password == loginParams.password) {
                    call.respond(
                        CommonResponse.Success(
                            msg = HttpStatusCode.OK.description,
                            code = HttpStatusCode.OK.value,
                            data = userBean.convert2LoginResponseBean()
                        )
                    )
                } else {
                    // todo tiktokid 生成,强行加一条数据
                    UserBean(
                        id = ObjectId.get().toId(),
                        userId = UUID.randomUUID().toString(),
                        username = loginParams.username ?: "",
                        password = loginParams.password ?: "",
                        phoneNumber = loginParams.phoneNumber,
                        avatar = loginParams.avatar ?: "",
                        tiktokId = "",
                    ).also {
                        loginService.insertUser(
                            it
                        )
                        call.respond(
                            CommonResponse.Success(
                                msg = HttpStatusCode.OK.description,
                                code = HttpStatusCode.OK.value,
                                data = it.convert2LoginResponseBean()
                            )
                        )
                    }
                }
            }.onFailure {
                call.respondRedirect("/")
                TiktokLogger.error("/login/loginByPassword error", it)
            }
        }

        /**
         * 验证码登录
         */
        get("/login/loginByCaptchaCode") {

        }

        /**
         * 获取登录验证码
         */
        get("/login/applyCaptchaCode") {
            runCatching {
                val loginParams = call.receive<LoginRequestBean>()
                loginService.applyCaptchaCode(loginParams.phoneNumber)
            }.onFailure {
                call.respondRedirect("/")
                TiktokLogger.error("/login/applyCaptchaCode error", it)
            }
        }

        /**
         * 更新密码
         */
        post("/login/updatePassword") {
            runCatching {
                val loginParams = call.receive<LoginRequestBean>()
                val userBean = loginService.findByPhone(loginParams.phoneNumber)
                if (userBean != null) {
                    loginService.updatePassword(userBean.copy(password = loginParams.password ?: ""))
                    call.respond(
                        CommonResponse.Success(
                            msg = HttpStatusCode.OK.description, code = HttpStatusCode.OK.value, data = null
                        )
                    )
                } else {
                    call.respond(
                        CommonResponse.Success(
                            msg = HttpStatusCode.Forbidden.description,
                            code = HttpStatusCode.Forbidden.value,
                            data = null
                        )
                    )
                    TiktokLogger.info("user not found,can't update")
                }
            }.onFailure {
                call.respondRedirect("/")
                TiktokLogger.error("/login/updatePassword error", it)
            }
        }
    }
}
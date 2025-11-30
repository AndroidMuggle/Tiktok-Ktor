package com.muggle.login

import com.aliyun.dypnsapi20170525.Client
import com.aliyun.dypnsapi20170525.models.SendSmsVerifyCodeRequest
import com.aliyun.teaopenapi.models.Config
import com.muggle.common.TiktokLogger
import login.UserBean
import org.bson.types.ObjectId
import org.ehcache.config.builders.CacheConfigurationBuilder
import org.ehcache.config.builders.CacheManagerBuilder
import org.ehcache.config.builders.ExpiryPolicyBuilder
import org.ehcache.config.builders.ResourcePoolsBuilder
import org.ehcache.config.units.MemoryUnit
import org.litote.kmongo.KMongo
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.getCollection
import org.litote.kmongo.id.toId
import java.time.Duration
import java.util.*

/**
 * 登录service
 */
class LoginService(connectString: String? = "") {

    private val cacheManager by lazy {
        CacheManagerBuilder
            .newCacheManagerBuilder()
            .withCache(
                "captchaCode",
                CacheConfigurationBuilder.newCacheConfigurationBuilder<String, UserBean>(
                    String::class.java,
                    UserBean::class.java,
                    ResourcePoolsBuilder.newResourcePoolsBuilder()
                        .disk(500, MemoryUnit.MB, false)
                        .heap(1000, MemoryUnit.MB)
                        .offheap(100, MemoryUnit.MB)
                )
                    .withExpiry(
                        ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(60))
                    )

            )
            .build()
    }

    private val mongoClient by lazy {
        if (connectString.isNullOrEmpty()) {
            KMongo.createClient()
        } else {
            KMongo.createClient(connectString)
        }
    }

    private val smsClient by lazy {
        Client(Config().apply {

        })
    }

    private val userCollection by lazy {
        mongoClient.getDatabase("users").getCollection<UserBean>()
    }

    fun findByPhone(phoneNumber: String): UserBean? {
        return userCollection.findOne {
            UserBean::phoneNumber eq phoneNumber
        }
    }

    fun insertUser(user: UserBean) {
        userCollection.insertOne(user)
    }

    fun loginByCaptchaCode(captchaCode: String) {

    }

    fun applyCaptchaCode(phoneNumber: String) {
        val response = smsClient.sendSmsVerifyCode(SendSmsVerifyCodeRequest().apply {
            codeLength = 4
            this.phoneNumber = phoneNumber
            signName = "云渚科技验证平台"
            templateCode = "SMS_327698178"
            templateParam = "{\"code\":\"##code##\",\"min\":\"1\"}"
        })

        TiktokLogger.info("SmsCode response: $response")

        cacheManager.getCache("captchaCode", String::class.java, UserBean::class.java)?.run {
            putIfAbsent(
                arrayOf(response.body.code, phoneNumber).joinToString("_"),
                findByPhone(phoneNumber) ?: UserBean(
                    id = ObjectId.get().toId(),
                    userId = UUID.randomUUID().toString(),
                    username = "",
                    password = "",
                    phoneNumber = phoneNumber,
                    avatar = "",
                    tiktokId = ""
                )
            )
        }
    }

    fun updatePassword(user: UserBean) {
        userCollection.replaceOne(UserBean::phoneNumber eq user.phoneNumber, user)
    }

    companion object {

    }

}
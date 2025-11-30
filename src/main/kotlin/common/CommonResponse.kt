package common

/**
 * 通用的数据返回
 */
sealed interface CommonResponse {
    /**
     * 成功的数据
     */
    data class Success<T>(val msg: String?, val code: Int, val data: T?) : CommonResponse

    /**
     * 失败数据返回
     */
    data class Error(val code: Int, val message: String) : CommonResponse
}
package com.muggle.common

import org.slf4j.LoggerFactory

object TiktokLogger {

    private val logger = LoggerFactory.getLogger(TiktokLogger::class.java)

    fun debug(msg: String) {
        logger.debug(msg)
    }

    fun info(msg: String) {
        logger.info(msg)
    }

    fun warn(msg: String) {
        logger.warn(msg)
    }

    fun error(msg: String) {
        logger.error(msg)
    }

    fun trace(msg: String) {
        logger.trace(msg)
    }

    fun trace(msg: String, vararg args: Any) {
        logger.trace(msg, args)
    }

    fun debug(msg: String, vararg args: Any) {
        logger.debug(msg, args)
    }

    fun info(msg: String, vararg args: Any) {
        logger.info(msg, args)
    }

    fun warn(msg: String, vararg args: Any) {
        logger.warn(msg, args)
    }

    fun error(msg: String, throwable: Throwable) {
        logger.error(msg, throwable)
    }
}
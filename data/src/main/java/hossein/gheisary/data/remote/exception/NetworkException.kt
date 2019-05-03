package com.global.data.network.exception

class NetworkException (override var message: String, var code: Int = 404) : Exception()
package com.vmadalin.core.network

data class NetworkState(
    val status: Status,
    val message: String? = null
) {
    enum class Status {
        RUNNING,
        SUCCESS,
        FAILED
    }
}

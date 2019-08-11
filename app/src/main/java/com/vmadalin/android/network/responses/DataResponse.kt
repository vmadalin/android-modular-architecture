package com.vmadalin.android.network.responses

data class DataResponse<T>(
    var offset: Int,
    var limit: Int,
    var total: Int,
    var count: Int,
    var results: List<T>
)

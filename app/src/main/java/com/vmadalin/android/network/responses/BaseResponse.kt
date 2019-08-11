package com.vmadalin.android.network.responses

data class BaseResponse<T>(
    var code: Int,
    var status: String,
    var data: DataResponse<T>
)

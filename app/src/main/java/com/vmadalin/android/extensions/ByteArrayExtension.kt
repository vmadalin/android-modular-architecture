package com.vmadalin.android.extensions

fun ByteArray.toHex(): String {
    return joinToString("") { "%02x".format(it) }
}

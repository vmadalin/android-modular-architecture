package com.vmadalin.android.extensions

import java.security.MessageDigest

fun String.toMD5(): String {
    return MessageDigest
        .getInstance("MD5")
        .digest(this.toByteArray())
        .toHex()
}

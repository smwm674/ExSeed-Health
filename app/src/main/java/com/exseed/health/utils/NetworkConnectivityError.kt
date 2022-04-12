package com.exseed.health.utils

import java.io.IOException

class NetworkConnectivityError : IOException() {
     override val message: String
        get() = "No Internet Connection"
}
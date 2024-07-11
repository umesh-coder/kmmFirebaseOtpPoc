package com.umeshdev.kmmfirebaseotppoc

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
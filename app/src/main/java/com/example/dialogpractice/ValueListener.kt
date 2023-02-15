package com.example.dialogpractice

interface ValueListener {
    fun sendValueToParent(value: Int)
    fun sendValueToParentDelayed(value: Int, delay: Long)
}
package com.example.kiet_kotlin
import kotlinx.coroutines.*
import java.time.Duration

class Dice(  rollingDuration: Long){
    private var mRollingDuration:Long = 0;
    private val diceSides=6;
    init {
        mRollingDuration=rollingDuration;
    }

    suspend fun roll():Int{
        println("Duration is $mRollingDuration");
        delay(mRollingDuration);
        return (1..diceSides).random();
    }
}
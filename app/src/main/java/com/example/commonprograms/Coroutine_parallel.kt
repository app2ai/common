package com.example.commonprograms

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun main() {

    GlobalScope.launch {

        val deferredOne = async {
            doLongRunningTaskOne()
        }

        val deferredTwo = async {
            doLongRunningTaskTwo()
        }

        val result = deferredOne.await() + deferredTwo.await()

        print(result) // back on UI thread

    }
}

private suspend fun doLongRunningTaskTwo(): Int {
    return withContext(Dispatchers.Default) {
        // your code for doing a long running task
        // Added delay to simulate
        delay(2000)
        return@withContext 10
    }
}

private suspend fun doLongRunningTaskOne(): Int {
    return withContext(Dispatchers.Default) {
        // your code for doing a long running task
        // Added delay to simulate
        delay(2000)
        return@withContext 10
    }
}
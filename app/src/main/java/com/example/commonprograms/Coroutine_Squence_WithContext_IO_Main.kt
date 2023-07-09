package com.example.commonprograms

import kotlinx.coroutines.delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun main() {
    GlobalScope.launch(Dispatchers.Main) {
        doSomething() // non-suspend, UI thread
        doLongRunningTask() // suspend, Default background thread
        doSomethingElse() // non-suspend, UI thread
    }

    // Sequential execute
    GlobalScope.launch(Dispatchers.Main) {
        val resultOne = doLongRunningTaskOne()
        val resultTwo = doLongRunningTaskTwo()
        print("Sum : ${resultOne + resultTwo}") // back on UI thread
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

fun doSomething() {
    print("1. doSomething")
}

fun doSomethingElse() {
    print("3. doSomethingElse")
}

suspend fun doLongRunningTask() {
    withContext(Dispatchers.Default) {
        // code for doing a long running task
        // Added delay to simulate
        delay(2000)
        print("2. doLongRunningTask")
    }
}

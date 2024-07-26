package com.example.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class AppWorker4(context: Context, workerParameters: WorkerParameters):
    Worker(context,workerParameters) {
    override fun doWork(): Result {
        startWorkInBg()
        val input = inputData.getStringArray("work")
        println("all workers data : ${input.toString()}")
        return Result.success()
    }
    private fun startWorkInBg() {
        for(i in 1..5){
            if(isStopped){
                break
            }
            Thread.sleep(1000)
            println("Random Number From Worker 4: ${(Math.random()*100).toInt()} Thread Name: ${Thread.currentThread().name}")
        }
    }

    override fun onStopped() {
        super.onStopped()
        println("Worker 4 On Stop")
    }
}
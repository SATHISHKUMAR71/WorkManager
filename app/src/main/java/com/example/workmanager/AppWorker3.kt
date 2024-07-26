package com.example.workmanager

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters


class AppWorker3(context: Context, workerParameters: WorkerParameters):
    Worker(context,workerParameters) {
    override fun doWork(): Result {
        startWorkInBg()
        val outputWork3 = Data.Builder()
            .putString("work","Work is Finished from work 3")
            .build()
        return Result.success(outputWork3)
    }
    private fun startWorkInBg() {
        for(i in 1..5){
            if(isStopped){
                break
            }
            Thread.sleep(1000)
            println("Random Number From Worker 3: ${(Math.random()*100).toInt()} Thread Name: ${Thread.currentThread().name}")
        }
    }

    override fun onStopped() {
        super.onStopped()
        println("Worker 3 On Stop")
    }
}
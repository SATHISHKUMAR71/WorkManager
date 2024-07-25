package com.example.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class PeriodicWorker(context: Context, workerParameters: WorkerParameters):
    Worker(context,workerParameters) {
    override fun doWork(): Result {
        startWorkInBg()
        return Result.success()
    }
    private fun startWorkInBg() {
        for(i in 1..5){
            if(isStopped){
                break
            }
            Thread.sleep(1000)
            println("Random Number From Periodic Worker: ${(Math.random()*100).toInt()} Thread Name: ${Thread.currentThread().name}")
        }
    }
    override fun onStopped() {
        super.onStopped()
        println("Periodic Worker On Stop")
    }
}
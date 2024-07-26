package com.example.workmanager

import android.content.Context
import android.widget.TimePicker
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.sql.Time
import java.util.concurrent.TimeUnit

class AppWorker(context:Context,workerParameters: WorkerParameters):Worker(context,workerParameters) {
    override fun doWork(): Result {
        startWorkInBg()
        val workManager = WorkManager.getInstance(applicationContext)
        val workRequest = OneTimeWorkRequest.Builder(AppWorker::class.java)
            .setInitialDelay(1,TimeUnit.MINUTES)
            .build()
        workManager.enqueue(workRequest)
        return Result.success()
    }

    private fun startWorkInBg() {
        for(i in 1..5){
            if(!isStopped){
                Thread.sleep(1000)
                println("Random Number From Worker 1: ${(Math.random()*100).toInt()} Thread Name: ${Thread.currentThread().name}")
            }
            else{
                break
            }
        }
    }

    override fun onStopped() {
        super.onStopped()
        println("Worker 1 On Stop")
    }
}
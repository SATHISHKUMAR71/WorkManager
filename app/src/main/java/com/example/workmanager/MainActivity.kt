package com.example.workmanager

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import java.util.Arrays
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val workRequest1 = OneTimeWorkRequest.Builder(AppWorker::class.java)
            .build()
        val workRequest2 = OneTimeWorkRequest.Builder(AppWorker2::class.java)
            .build()
        val workRequest3 = OneTimeWorkRequest.Builder(AppWorker3::class.java)
            .build()
        val workRequest4 = OneTimeWorkRequest.Builder(AppWorker4::class.java)
            .build()
        val workRequest5 = OneTimeWorkRequest.Builder(AppWorker5::class.java)
            .build()
        val periodicWorker = PeriodicWorkRequest.Builder(PeriodicWorker::class.java,15,TimeUnit.MINUTES).build()

        val workManager = WorkManager.getInstance(applicationContext)
        findViewById<Button>(R.id.buttonStart).setOnClickListener {
            workManager.beginWith(listOf(workRequest1,workRequest2,workRequest3)).then(workRequest4).then(workRequest5).enqueue()
        }
        findViewById<Button>(R.id.buttonStop).setOnClickListener{
            workManager.cancelWorkById(workRequest1.id)
        }
        findViewById<Button>(R.id.buttonPeriodicStart).setOnClickListener{
            workManager.enqueue(periodicWorker)
        }
        findViewById<Button>(R.id.buttonPeriodicStop).setOnClickListener{
            workManager.cancelWorkById(periodicWorker.id)
        }
    }
}
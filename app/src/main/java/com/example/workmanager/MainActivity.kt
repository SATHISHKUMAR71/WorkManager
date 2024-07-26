package com.example.workmanager

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.work.ArrayCreatingInputMerger
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import java.util.Arrays
import java.util.Calendar
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),101
        )
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .build()
        val workRequest1 = OneTimeWorkRequest.Builder(AppWorker2::class.java)
            .setConstraints(constraints)
            .setInitialDelay(10000,TimeUnit.MILLISECONDS)
            .build()
        val periodicWorker = PeriodicWorkRequest.Builder(AppWorker2::class.java,15,TimeUnit.MINUTES)
            .build()
        val workManager = WorkManager.getInstance(applicationContext)

        findViewById<Button>(R.id.buttonStop).setOnClickListener{
            workManager.cancelAllWork()
        }
        workManager.enqueueUniquePeriodicWork("My periodic Work",
            ExistingPeriodicWorkPolicy.UPDATE,periodicWorker)
        findViewById<Button>(R.id.buttonPeriodicStop).setOnClickListener{
            workManager.cancelWorkById(periodicWorker.id)
        }
    }
}
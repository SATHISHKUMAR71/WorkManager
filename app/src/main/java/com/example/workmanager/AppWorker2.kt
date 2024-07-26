package com.example.workmanager

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class AppWorker2(context: Context,workerParameters: WorkerParameters):Worker(context,workerParameters) {
    override fun doWork(): Result {
        createNotificationChannel()
        startWorkInBg()
        return Result.success()

    }
    private fun startWorkInBg() {
        val intent = Intent(applicationContext,MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(applicationContext,0,intent, PendingIntent.FLAG_MUTABLE)
            Thread.sleep(1000)
            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
                val notificationManager1 = NotificationManagerCompat.from(applicationContext)
                val notification = NotificationCompat.Builder(applicationContext,"DemoChannelID")
                    .setContentText("Demo Content ")
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentIntent(pendingIntent)
                    .setGroup("NotificationGroup")
                    .setAutoCancel(true)
                    .build()

                if (ActivityCompat.checkSelfPermission(
                        applicationContext,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    notificationManager1.notify(1,notification)
                }
            }
            println("Notification Pushed 1")


//
//            if(isStopped){
//                break
//            }
//            Thread.sleep(1000)
//            println("Random Number From Worker 2: ${(Math.random()*100).toInt()} Thread Name: ${Thread.currentThread().name}")
//        }
    }
    override fun onStopped() {
        super.onStopped()
        println("Worker 2 On Stop")
    }
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "DemoChannelID"
            val channelName = "DemoChannelName"
            val channelDescription = "Demo Channel Description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = channelDescription
            }
            val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

}
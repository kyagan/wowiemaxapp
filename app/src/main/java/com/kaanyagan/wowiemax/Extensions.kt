package com.kaanyagan.wowiemax

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar


fun Context.showToast(message:String){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }

    fun Context.showAlert(title:String, message:String){
        AlertDialog.Builder(this).setTitle(title).setMessage(message).create().show()
    }
    fun Context.showSnackBar(view: View, message:String){
        Snackbar.make(view,message,Snackbar.LENGTH_LONG).show()
    }

    fun Context.convertMinutesToTime(minutes: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, minutes / 60)
        calendar.set(Calendar.MINUTE, minutes % 60)

        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        return "$hour:$minute"
    }

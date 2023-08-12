package com.kaanyagan.wowiemax

import android.annotation.SuppressLint
import android.app.ProgressDialog.show
import android.content.Context
import android.graphics.Color
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar


fun Context.showToast(message:String){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }

    fun Context.showAlert(title:String, message:String, icon:Int){
        val customTitle = SpannableString(title)
        val customMessage = SpannableString(message)
        customTitle.setSpan(ForegroundColorSpan(ContextCompat.getColor(applicationContext,R.color.white)),0, title.length, 0)
        customMessage.setSpan(ForegroundColorSpan(ContextCompat.getColor(applicationContext,R.color.white)),0, message.length, 0)

        val dialog = AlertDialog.Builder(this)
            .setTitle(customTitle)
            .setMessage(customMessage)
            .setIcon(icon)
            .setPositiveButton("Anladım",null)
            .create()
            dialog.window?.setBackgroundDrawableResource(R.color.background)
            dialog.show()
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

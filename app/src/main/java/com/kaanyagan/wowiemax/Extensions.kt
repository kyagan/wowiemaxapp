package com.kaanyagan.wowiemax

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog.show
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
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

    @SuppressLint("SuspiciousIndentation")
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

    fun Activity.vibratePhone(context:Context) {
        val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(200)
        }
    }



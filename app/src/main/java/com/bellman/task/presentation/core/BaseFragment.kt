package com.bellman.task.presentation.core

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.bellman.task.R
import io.reactivex.disposables.CompositeDisposable

import android.location.Geocoder
import android.annotation.SuppressLint

import android.util.Log
import java.util.*


open class BaseFragment : Fragment() {
    private lateinit var dialog: AlertDialog



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makeDialog()
    }

    private fun makeDialog() {
        val mBuilder = AlertDialog.Builder(activity)
        val mView = layoutInflater.inflate(R.layout.loading_ui, null)
        mBuilder.setView(mView)
        dialog = mBuilder.create()
        dialog.setCanceledOnTouchOutside(false)
        dialog.window!!.setDimAmount(0.0f)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    protected fun showProgressDialog() {
        if (dialog != null && !dialog.isShowing) {
            dialog.show()
            dialog.window!!.setLayout(200, 200)
        }
    }

    protected fun dismissProgressDialg() {
        dialog.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    @SuppressLint("LongLogTag")
    fun getCountryName(latitude: Double, longitude: Double): String {
        val geocoder = Geocoder(activity)
        var strAdd = ""
        try {
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            if (addresses != null) {
                val returnedAddress = addresses[0]
                val strReturnedAddress = StringBuilder("")

                for (i in 0..returnedAddress.maxAddressLineIndex) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append(",")
                }
                strAdd = strReturnedAddress.toString()
                Log.w("My Current loction address", strReturnedAddress.toString())
            } else {
                Log.w("My Current loction address", "No Address returned!")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.w("My Current loction address", "Canont get Address!")
        }

        return strAdd

    }
}

package com.bellman.task.presentation.core

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.util.*
import java.util.concurrent.TimeoutException
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.bellman.task.R
import com.bellman.task.domain.core.*


fun View.toggleVisibility(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

infix fun View.onClick(action: (() -> Unit)) {
    this.setOnClickListener { action.invoke() }
}

fun View.showSnackbar(message: String) {
    if (!TextUtils.isEmpty(message)) {
        val snackBar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
        snackBar.setActionTextColor(Color.WHITE)
        val sbView = snackBar.view
        sbView.setBackgroundColor(ContextCompat.getColor(this.context, R.color.colorAccent))
        snackBar.show()
    }
}

fun EditText.searchObservable(): Observable<String> {
    val subject = PublishSubject.create<String>()
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(text: CharSequence, start: Int, before: Int, count: Int) {
            subject.onNext(text.toString())
        }

        override fun afterTextChanged(s: Editable) {
//            subject.onComplete()
        }
    })

    return subject
}


fun Fragment.showMessage(error: Throwable) {
    activity?.run {
        showMessage(error)
    }
}

fun Context.showShortToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Activity.showMessage(error: Throwable) {
    when (error) {
        is ServerErrorException -> showShortToast(getString(R.string.internal))
        is TimeoutException -> showShortToast(getString(R.string.time))
        is ServerUnreachableException -> showShortToast(getString(R.string.unreach))
        is BadRequest -> showShortToast(getString(R.string.bad))
        is NotFound -> showShortToast(getString(R.string.notFound))
        is ManyRequest -> showShortToast(getString(R.string.manyRequest))
        is UnAuthorizedException -> showShortToast(getString(R.string.unauthorize))
        else -> showShortToast(error.message.toString())

    }
}


fun Activity.setSpinnerCustomAdabter(
    spinner: Spinner,
    list: ArrayList<String>,
    layId: Int
) {
    val adapter = ArrayAdapter(
        this.applicationContext, layId, list
    )
    adapter.setDropDownViewResource(layId)
    spinner.adapter = adapter
}

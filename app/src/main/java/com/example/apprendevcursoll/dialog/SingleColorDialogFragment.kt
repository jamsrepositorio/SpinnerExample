package com.example.apprendevcursoll.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.lang.IllegalStateException

class SingleColorDialogFragment:DialogFragment()
{
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val  selectedColors = mutableListOf<Int>()
            val builder = AlertDialog.Builder(it)
        builder.create()
        }?:throw IllegalStateException("Actividad no vacía")
    }
}
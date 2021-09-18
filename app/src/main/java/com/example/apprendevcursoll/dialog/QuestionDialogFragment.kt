package com.example.apprendevcursoll.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.lang.IllegalStateException

class QuestionDialogFragment : DialogFragment()
{
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog
    {
        return activity?.let {
       val builder = AlertDialog.Builder(it)
        builder.setMessage("Te gustaría programar en Android?")
            .setPositiveButton("Sí")
            {dialog, id ->

            }
            .setNegativeButton("No")
            {dialog, id ->

            }
            builder.create()

        }?:throw IllegalStateException("La actividad no puede ser nula")


    }
}
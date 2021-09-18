package com.example.apprendevcursoll.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import com.example.apprendevcursoll.R
import java.lang.IllegalStateException

class ColorDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Colores")
                .setItems(
                    R.array.colors
                ) { dialog, colorId ->
                Log.d(ColorDialogFragment::class.java.simpleName,"colorId = $colorId")

                }
            builder.create()

        } ?: throw IllegalStateException("Esta actividad no está vacía")
    }
}






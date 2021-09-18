package com.example.apprendevcursoll.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import com.example.apprendevcursoll.R
import java.lang.IllegalStateException

class MulticolorsDialogFragment:DialogFragment()
{
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val selectedColors = mutableListOf<Int>()
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Seleccion de colores multiples")
                .setMultiChoiceItems(
                    R.array.colors,
                null
                ) {
                    dialog,colorId, isChecked ->
                    if(isChecked)
                    {
                        selectedColors.add(colorId)
                    }else if(selectedColors.contains(colorId))
                    {
                        selectedColors.remove(colorId)
                    }
                    Log.d(MulticolorsDialogFragment::class.java.simpleName,
                        "selectedColors size = ${selectedColors.size}")
                }
                .setPositiveButton("Acepto", DialogInterface.OnClickListener{_,_  ->})
                .setNegativeButton("Cancelar", DialogInterface.OnClickListener{_,_ ->})
                builder.create()
        }?:throw IllegalStateException("La actividad no debe ser nula")
    }
}
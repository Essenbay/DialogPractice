package com.example.dialogpractice.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ListAdapter
import com.example.dialogpractice.R
import com.example.dialogpractice.ValuesViewModel

//Changes through view model
class ListDialog : DialogFragment() {
    private val parentViewModel: ValuesViewModel by activityViewModels()
    private val offers: Array<CharSequence> = arrayOf("1", "2", "3")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var selectedValue: Int = 1
        val builder = AlertDialog.Builder(requireContext())
            .setTitle("Set value by offers")
            .setSingleChoiceItems(offers, 0,
                DialogInterface.OnClickListener { dialog, which ->
                    selectedValue = which
                })
            .setItems(offers) { _, which ->
                selectedValue = which
            }
            .setPositiveButton("Ok") { _, _ ->
                parentViewModel.setValue(selectedValue)
            }
            .setNegativeButton("Cancel") { _, _ ->

            }
        return builder.create()
    }
}
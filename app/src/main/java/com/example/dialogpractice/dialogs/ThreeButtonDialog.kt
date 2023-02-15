package com.example.dialogpractice.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.dialogpractice.ValueListener


class ThreeButtonDialog : DialogFragment() {
    private val value = 3
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val parentActivity = activity
        if (parentActivity is ValueListener) {
            val builder = AlertDialog.Builder(requireContext())
                .setTitle("Are you sure you want to set value to 3?")
                .setPositiveButton("Yes") { _, _ ->
                    parentActivity.sendValueToParent(value)
                }
                .setNegativeButton("No") { _, _ ->

                }
                .setNeutralButton("After 5 seconds") { _, _ ->
                    parentActivity.sendValueToParentDelayed(value, 5000)
                }
            return builder.create()
        } else {
            throw IllegalStateException("Parent Activity is not instance of ValueListener interface")
        }
    }
}
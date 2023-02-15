package com.example.dialogpractice.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment

class TwoButtonDialog : DialogFragment() {
    private val value = 2
    private val secondValue = 1

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Are you sure you want to set value to 2")
            .setPositiveButton("Yes") { _, _ ->
                requireActivity().supportFragmentManager.setFragmentResult(
                    REQUEST_KEY_DIALOG,
                    bundleOf(BUNDLE_KEY_DIALOG to value)
                )
            }
            .setNegativeButton("No") { _, _ ->
            }
        return builder.create()
    }

    companion object {
        const val REQUEST_KEY_DIALOG = "REQUEST_KEY_GET_2"
        const val BUNDLE_KEY_DIALOG = "BUNDLE_KEY_GET_2"
    }
}
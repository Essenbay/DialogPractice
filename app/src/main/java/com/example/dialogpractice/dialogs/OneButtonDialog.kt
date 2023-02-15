package com.example.dialogpractice.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment


class OneButtonDialog : DialogFragment() {
    private val positiveResult = 1
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
            .setTitle("The value was set to 1")
            .setPositiveButton("Ok") { _, _ ->
                requireActivity().supportFragmentManager.setFragmentResult(
                    REQUEST_KEY_DIALOG,
                    bundleOf(
                        BUNDLE_KEY_DIALOG to positiveResult
                    )
                )
            }
        return builder.create()
    }

    companion object {
        const val REQUEST_KEY_DIALOG = "REQUEST_KEY_GET_1"
        const val BUNDLE_KEY_DIALOG = "BUNDLE_KEY_GET_1"
    }
}
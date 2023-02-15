package com.example.dialogpractice.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.example.dialogpractice.R

class TextInputDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = layoutInflater.inflate(R.layout.fragment_input_dialog, null)
        val builder = AlertDialog.Builder(requireContext())
            .setView(view)
            .setPositiveButton("Ok") { _, _ ->
                val value = Integer.parseInt(
                    view.findViewById<EditText>(R.id.user_value).text.toString()
                )
                requireActivity().supportFragmentManager.setFragmentResult(
                    REQUEST_KEY_DIALOG, bundleOf(
                        BUNDLE_KEY_DIALOG to value
                    )
                )
            }
            .setNegativeButton("Cancel") { _, _ ->

            }
        return builder.create()
    }

    companion object {
        const val REQUEST_KEY_DIALOG = "REQUEST_KEY_GET_INPUT"
        const val BUNDLE_KEY_DIALOG = "BUNDLE_KEY_GET_INPUT"
    }
}
package com.example.dialogpractice


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.dialogpractice.databinding.ActivityMainBinding
import com.example.dialogpractice.dialogs.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), ValueListener {
    private lateinit var binding: ActivityMainBinding
    private val valuesViewModel: ValuesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //One Button Dialog
        binding.oneButtonAlert.setOnClickListener {
            val fm = supportFragmentManager
            OneButtonDialog().show(fm, "One Button Dialog")
        }
        supportFragmentManager.setFragmentResultListener(
            OneButtonDialog.REQUEST_KEY_DIALOG,
            this
        ) { _, bundle ->
            val result = bundle.getInt(OneButtonDialog.BUNDLE_KEY_DIALOG)
            binding.outputText.text = result.toString()
            valuesViewModel.setValue(result)
        }

        //Two Button Dialog
        binding.twoButtonAlert.setOnClickListener {
            TwoButtonDialog().show(supportFragmentManager, "Set 2 value")
        }
        supportFragmentManager.setFragmentResultListener(
            TwoButtonDialog.REQUEST_KEY_DIALOG, this
        ) { _, bundle ->
            val result = bundle.getInt(TwoButtonDialog.BUNDLE_KEY_DIALOG)
            binding.outputText.text = result.toString()
            valuesViewModel.setValue(result)
        }

        //Three Button Dialog
        binding.threeButtonAlert.setOnClickListener {
            ThreeButtonDialog().show(supportFragmentManager, null)
        }

        //Text Input Dialog
        binding.textInputDialog.setOnClickListener {
            TextInputDialog().show(supportFragmentManager, null)
        }
        supportFragmentManager.setFragmentResultListener(
            TextInputDialog.REQUEST_KEY_DIALOG, this
        ) { _, bundle ->
            val result = bundle.getInt(TextInputDialog.BUNDLE_KEY_DIALOG)
            binding.outputText.text = result.toString()
            valuesViewModel.setValue(result)
        }

        //List Dialog
        binding.listDialog.setOnClickListener {
            ListDialog().show(supportFragmentManager, null)
        }

        //Full Screen Dialog

        //Date and Time Dialog

        //Update UI
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                valuesViewModel.listValue.collect {
                    binding.outputText.text = if (it.isEmpty()) "No value"
                    else it.last().toString()
                }
            }
        }
    }

    override fun sendValueToParent(value: Int) {
        valuesViewModel.setValue(value)
        binding.outputText.text = valuesViewModel.getLastValue()?.toString() ?: "No value"
    }

    override fun sendValueToParentDelayed(value: Int, delay: Long) {
        lifecycleScope.launch {
            delay(delay)
            valuesViewModel.setValue(value)
            binding.outputText.text = valuesViewModel.getLastValue()?.toString() ?: "No value"
        }
    }
}

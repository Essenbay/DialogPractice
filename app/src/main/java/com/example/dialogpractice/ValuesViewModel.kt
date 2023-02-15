package com.example.dialogpractice

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class ValuesViewModel() : ViewModel() {
    private var _mutableListValue: MutableStateFlow<MutableList<Int>> = MutableStateFlow(mutableListOf())
    val listValue: StateFlow<List<Int>> = _mutableListValue.asStateFlow()

    fun getValueAt(index: Int): Int? = _mutableListValue.value.getOrNull(index)

    fun getLastValue(): Int? {
        return _mutableListValue.run {
            if (this.value.isEmpty()) null
            else _mutableListValue.value.last()
        }
    }

    fun setValue(number: Int) {
        Log.d("ViewModel", "The value was set: $number")
        _mutableListValue.value.add(number)
    }
}
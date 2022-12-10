package com.example.fragments

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData

class SharedViewModel : ViewModel() {

    private var _numOne = MutableLiveData<String>()
    val numOne: LiveData<String> = _numOne
    fun saveNumOne(newNum: String) {
        _numOne.value = newNum
    }
    fun getOne(): Float {
        return _numOne.value.toString().toFloat()
    }

    private var _numTwo = MutableLiveData<String>()
    val numTwo: LiveData<String> = _numTwo
    fun saveNumTwo(newNum: String) {
        _numTwo.value = newNum
    }
    fun getTwo(): Float {
        return _numTwo.value.toString().toFloat()
    }

    private var _oper = MutableLiveData<String>()
    val oper: LiveData<String> = _oper
    fun saveOper(newNum: String) {
        _oper.value = newNum
    }
    fun getOper(): String {
        return _oper.value.toString()
    }

    private var _frag = MutableLiveData<Int>()
    val frag: LiveData<Int> = _frag
    fun saveFrag(newFrag: Int) {
        _frag.value = newFrag
    }
    fun getFrag(): Int? {
        return _frag.value
    }

    private var _stack = mutableListOf<Int>()
    fun pushFragmentToStack() {
        if (getFrag() == 1) {
            _stack?.add(1)
        }
        if (getFrag() == 2) {
            _stack?.add(2)
        }

        if (getFrag() == 3) {
            _stack?.add(3)
        }

        if (getFrag() == 4) {
            _stack?.add(4)
        }
    }

    fun popFromStack(): Int? {
        val popped = _stack?.last()
        _stack?.removeAt(_stack.lastIndex)
        return popped
    }

    fun getStack(): MutableList<Int> {
        return _stack
    }
}
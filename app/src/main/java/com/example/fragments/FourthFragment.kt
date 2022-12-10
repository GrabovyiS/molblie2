package com.example.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation


class FourthFragment : Fragment() {

    private val sharedViewModel : SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fourth, container, false)

        var output = view.findViewById<TextView>(R.id.text_fourth)

        var result: Float
        result = 0F

        Log.d("hehe",sharedViewModel.getOper())
        if (sharedViewModel.getOper() == "+") {
            result = sharedViewModel.getOne() + sharedViewModel.getTwo()
        }

        if (sharedViewModel.getOper() == "-") {
            result = sharedViewModel.getOne() - sharedViewModel.getTwo()
        }

        if (sharedViewModel.getOper() == "*") {
            result = sharedViewModel.getOne() * sharedViewModel.getTwo()
        }

        if ((sharedViewModel.getOper() == "/") or (sharedViewModel.getOper() == ":")) {
            result = sharedViewModel.getOne() / sharedViewModel.getTwo()
        }

        output.text = result.toString()

        val backBtn = view.findViewById<Button>(R.id.back)
        backBtn.setOnClickListener {
            sharedViewModel.pushFragmentToStack()
            sharedViewModel.saveFrag(3)
            sharedViewModel.getFrag()?.let { it1 -> (activity as MainActivity?)!!.buttons(it1) }
            Navigation.findNavController(view).navigate(R.id.action_fourthFragment_to_thirdFragment)
        }

        return view
    }


}
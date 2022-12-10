package com.example.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation

class ThirdFragment : Fragment() {
    private val sharedViewModel : SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_third, container, false)

        view.findViewById<TextView>(R.id.text_third)

        val nextBtn = view.findViewById<Button>(R.id.next)
        val input = view.findViewById<EditText>(R.id.editText)
        val backBtn = view.findViewById<Button>(R.id.back)

        sharedViewModel.oper.observe(viewLifecycleOwner) { oper ->
            input.setText(oper)
        }

        nextBtn.setOnClickListener {
            sharedViewModel.pushFragmentToStack()
            sharedViewModel.saveFrag(4)
            sharedViewModel.getFrag()?.let { it1 -> (activity as MainActivity?)!!.buttons(it1) }
            sharedViewModel.saveOper(input.text.toString())
            Navigation.findNavController(view).navigate(R.id.action_thirdFragment_to_fourthFragment)
        }

        backBtn.setOnClickListener {
            sharedViewModel.pushFragmentToStack()
            sharedViewModel.saveFrag(2)
            sharedViewModel.getFrag()?.let { it1 -> (activity as MainActivity?)!!.buttons(it1) }
            Navigation.findNavController(view).navigate(R.id.action_thirdFragment_to_secondFragment)
        }

        // Inflate the layout for this fragment
        return view
    }
}
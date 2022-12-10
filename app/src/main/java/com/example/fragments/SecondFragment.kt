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
import androidx.navigation.fragment.findNavController
import com.example.fragments.databinding.FragmentFirstBinding

class SecondFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding

    private val sharedViewModel : SharedViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        val nextBtn = view.findViewById<Button>(R.id.next)
        val input = view.findViewById<EditText>(R.id.editText)
        val backBtn = view.findViewById<Button>(R.id.back)

        view.findViewById<TextView>(R.id.text_secondnumber)

        sharedViewModel.numTwo.observe(viewLifecycleOwner) { numTwo ->
            input.setText(numTwo)
        }

        nextBtn.setOnClickListener {
            sharedViewModel.pushFragmentToStack()
            sharedViewModel.saveFrag(3)
            sharedViewModel.getFrag()?.let { it1 -> (activity as MainActivity?)!!.buttons(it1) }
            sharedViewModel.saveNumTwo(input.text.toString())
            Navigation.findNavController(view).navigate(R.id.action_secondFragment_to_thirdFragment)
        }

        backBtn.setOnClickListener {
            sharedViewModel.pushFragmentToStack()
            sharedViewModel.saveFrag(1)
            sharedViewModel.getFrag()?.let { it1 -> (activity as MainActivity?)!!.buttons(it1) }
            Navigation.findNavController(view).navigate(R.id.action_secondFragment_to_firstFragment)
        }

        return view
    }
}
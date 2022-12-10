package com.example.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.fragments.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel : SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_first, container, false)
        val nextBtn = view.findViewById<Button>(R.id.next)
        val input = view.findViewById<EditText>(R.id.editText)

        sharedViewModel.numOne.observe(viewLifecycleOwner) { numOne ->
            input.setText(numOne)
        }

        nextBtn.setOnClickListener {
            sharedViewModel.pushFragmentToStack()
            sharedViewModel.saveFrag(2)
            sharedViewModel.getFrag()?.let { it1 -> (activity as MainActivity?)!!.buttons(it1) }
            sharedViewModel.saveNumOne(input.text.toString())
            Navigation.findNavController(view).navigate(R.id.action_firstFragment_to_secondFragment)
        }

        return view
    }

}
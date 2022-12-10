package com.example.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {

    private val sharedModel: SharedViewModel by viewModels()

    var btnFragment1: Button? = null
    var btnFragment2: Button? = null
    var btnFragment3: Button? = null
    var btnFragment4: Button? = null

    fun buttons (fragment: Int) {

        if (fragment == 1) {
            btnFragment2?.visibility = View.GONE
            btnFragment3?.visibility = View.GONE
            btnFragment4?.visibility = View.GONE
        }

        if (fragment == 2) {
            btnFragment2?.visibility = View.VISIBLE
            btnFragment3?.visibility = View.GONE
            btnFragment4?.visibility = View.GONE
        }

        if (fragment == 3) {
            btnFragment2?.visibility = View.VISIBLE
            btnFragment3?.visibility = View.VISIBLE
            btnFragment4?.visibility = View.GONE
        }

        if (fragment == 4) {
            btnFragment2?.visibility = View.VISIBLE
            btnFragment3?.visibility = View.VISIBLE
            btnFragment4?.visibility = View.VISIBLE
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        btnFragment1 = findViewById(R.id.btnFragment1)
        btnFragment2 = findViewById(R.id.btnFragment2)
        btnFragment3 = findViewById(R.id.btnFragment3)
        btnFragment4 = findViewById(R.id.btnFragment4)

        sharedModel.saveFrag(1)
        sharedModel.getFrag()?.let { buttons(it) }



        fun toFirst () {
            if (sharedModel.getFrag() == 1) return

            if (sharedModel.getFrag() == 2) {
                navController.navigate(R.id.action_secondFragment_to_firstFragment)
            }

            if (sharedModel.getFrag() == 3) {
                navController.navigate(R.id.action_thirdFragment_to_firstFragment)
            }

            if (sharedModel.getFrag() == 4) {
                navController.navigate(R.id.action_fourthFragment_to_firstFragment)
            }

            sharedModel.saveFrag(1)
        }

        fun toSecond () {
            if (sharedModel.getFrag() == 1) {
                navController.navigate(R.id.action_firstFragment_to_secondFragment)
            }

            if (sharedModel.getFrag() == 2) return

            if (sharedModel.getFrag() == 3) {
                navController.navigate(R.id.action_thirdFragment_to_secondFragment)
            }

            if (sharedModel.getFrag() == 4) {
                navController.navigate(R.id.action_fourthFragment_to_secondFragment)
            }

            sharedModel.saveFrag(2)
        }

        fun toThird () {
            if (sharedModel.getFrag() == 2) {
                navController.navigate(R.id.action_secondFragment_to_thirdFragment)
            }

            if (sharedModel.getFrag() == 3) return

            if (sharedModel.getFrag() == 4) {
                navController.navigate(R.id.action_fourthFragment_to_thirdFragment)
            }

            sharedModel.saveFrag(3)
        }

        fun toFourth () {
            if (sharedModel.getFrag() == 3) {
                navController.navigate(R.id.action_thirdFragment_to_fourthFragment)
            }

            if (sharedModel.getFrag() == 4) return

            sharedModel.saveFrag(4)
        }

        btnFragment1?.setOnClickListener {
            sharedModel.pushFragmentToStack()
            toFirst()
            sharedModel.getFrag()?.let { it1 -> buttons(it1) } //buttons(getFrag)
        }

        btnFragment2?.setOnClickListener {
            sharedModel.pushFragmentToStack()
            toSecond()
            sharedModel.getFrag()?.let { it1 -> buttons(it1) }
        }

        btnFragment3?.setOnClickListener {
            sharedModel.pushFragmentToStack()
            toThird()
            sharedModel.getFrag()?.let { it1 -> buttons(it1) }
        }

        btnFragment4?.setOnClickListener {
            sharedModel.pushFragmentToStack()
            toFourth()
            sharedModel.getFrag()?.let { it1 -> buttons(it1) }
        }
    }

    override fun onBackPressed() {
        Log.d("backPressed", sharedModel.getStack().toString())
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        fun toFirst () {
            if (sharedModel.getFrag() == 1) return

            if (sharedModel.getFrag() == 2) {
                navController.navigate(R.id.action_secondFragment_to_firstFragment)
            }

            if (sharedModel.getFrag() == 3) {
                navController.navigate(R.id.action_thirdFragment_to_firstFragment)
            }

            if (sharedModel.getFrag() == 4) {
                navController.navigate(R.id.action_fourthFragment_to_firstFragment)
            }

            sharedModel.saveFrag(1)
        }

        fun toSecond () {
            if (sharedModel.getFrag() == 1) {
                navController.navigate(R.id.action_firstFragment_to_secondFragment)
            }

            if (sharedModel.getFrag() == 2) return

            if (sharedModel.getFrag() == 3) {
                navController.navigate(R.id.action_thirdFragment_to_secondFragment)
            }

            if (sharedModel.getFrag() == 4) {
                navController.navigate(R.id.action_fourthFragment_to_secondFragment)
            }

            sharedModel.saveFrag(2)
        }

        fun toThird () {
            if (sharedModel.getFrag() == 2) {
                navController.navigate(R.id.action_secondFragment_to_thirdFragment)
            }

            if (sharedModel.getFrag() == 3) return

            if (sharedModel.getFrag() == 4) {
                navController.navigate(R.id.action_fourthFragment_to_thirdFragment)
            }

            sharedModel.saveFrag(3)
        }

        fun toFourth () {
            if (sharedModel.getFrag() == 3) {
                navController.navigate(R.id.action_thirdFragment_to_fourthFragment)
            }

            if (sharedModel.getFrag() == 4) return

            sharedModel.saveFrag(4)
        }

        if (sharedModel.getStack()?.size == 0 || sharedModel.getStack() == null) {
            Log.d("backPressed", "FINISH")
            finish()
        }

        val last = sharedModel.popFromStack() //we popped, we saved this last used element, now we navigate to the previous fragment

        if (last == 1) {
            toFirst()
            sharedModel.getFrag()?.let { it1 -> buttons(it1) }
        }

        if (last == 2) {
            toSecond()
            sharedModel.getFrag()?.let { it1 -> buttons(it1) }
        }

        if (last == 3) {
            toThird()
            sharedModel.getFrag()?.let { it1 -> buttons(it1) }
        }

        if (last == 4) {
            toFourth()
            sharedModel.getFrag()?.let { it1 -> buttons(it1) }
        }
    }

}
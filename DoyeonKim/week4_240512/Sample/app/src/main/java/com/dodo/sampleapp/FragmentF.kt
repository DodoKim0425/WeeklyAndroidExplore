package com.dodo.sampleapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.Navigation
import com.dodo.sampleapp.databinding.FragmentCBinding
import com.dodo.sampleapp.databinding.FragmentFBinding
import androidx.navigation.fragment.findNavController


class FragmentF : Fragment() {

    private lateinit var binding: FragmentFBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setResult()
        binding = FragmentFBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setResult() {
        //findNavController().previousBackStackEntry?.savedStateHandle?.set(key, result) 로 해도 된다
        findNavController().getBackStackEntry(R.id.fragmentA).savedStateHandle.set(
            FragmentA.KEY_RESULT_BACK_STACK, "F결과값"
        )
    }
}
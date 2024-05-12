package com.dodo.sampleapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.dodo.sampleapp.databinding.FragmentBBinding

class FragmentB : Fragment() {

    private lateinit var binding: FragmentBBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setResult()
        binding = FragmentBBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setResult() {
        setFragmentResult(
            FragmentA.KEY_FRAGMENT_RESULT,
            bundleOf(FragmentA.KEY_RESULT_TEXT to "B결과값")
        )
    }
}
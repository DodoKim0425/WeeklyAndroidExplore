package com.dodo.sampleapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.dodo.sampleapp.databinding.FragmentCBinding
import com.dodo.sampleapp.databinding.FragmentDBinding

class FragmentD : Fragment() {

    private lateinit var binding: FragmentDBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setResult()
        binding = FragmentDBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setResult() {
        setFragmentResult(
            FragmentA.KEY_FRAGMENT_RESULT_INT,
            bundleOf(FragmentA.KEY_RESULT_INT to 100)
        )
    }
}
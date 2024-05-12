package com.dodo.sampleapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.dodo.sampleapp.databinding.FragmentABinding

private const val TAG = "FragmentA"

class FragmentA : Fragment() {
    companion object {
        const val KEY_FRAGMENT_RESULT = "key_fragment_result"
        const val KEY_FRAGMENT_RESULT_INT = "key_fragment_result_int"
        const val KEY_RESULT_TEXT = "key_result_text"
        const val KEY_RESULT_INT = "key_result_int"
        const val KEY_RESULT_BACK_STACK = "key_result_back_stack"
    }

    private lateinit var binding: FragmentABinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentABinding.inflate(inflater, container, false)
        initView()
        initClickListener()
        initBackStackListener()
        return binding.root
    }

    private fun initView() {
        setFragmentResultListener(KEY_FRAGMENT_RESULT) { requestKey, bundle ->
            Log.d(TAG, "requestKey: ${requestKey}")
            binding.textViewA.text = bundle.getString(KEY_RESULT_TEXT)
        }
        setFragmentResultListener(KEY_FRAGMENT_RESULT_INT) { requestKey, bundle ->
            Log.d(TAG, "requestKey: ${requestKey}")
            binding.textViewIntResult.text = bundle.getInt(KEY_RESULT_INT).toString()
        }
    }

    private fun initClickListener(){
        binding.buttonGoB.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentA_to_fragmentB)
        }
        binding.buttonGoC.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentA_to_fragmentC)
        }
        binding.buttonGoD.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentA_to_fragmentD)
        }
        binding.buttonGoF.setOnClickListener{
            findNavController().navigate(R.id.action_fragmentA_to_fragmentF)
        }
    }

    private fun initBackStackListener() {
        findNavController().currentBackStackEntry?.savedStateHandle?.apply {
            this?.getLiveData<String>(KEY_RESULT_BACK_STACK)
                ?.observe(viewLifecycleOwner) {
                    Log.d(TAG, "initBackStackListener: observed")
                    binding.textViewBackStack.text = it
                }
            this?.remove<String>(KEY_RESULT_BACK_STACK)
        }

    }

}
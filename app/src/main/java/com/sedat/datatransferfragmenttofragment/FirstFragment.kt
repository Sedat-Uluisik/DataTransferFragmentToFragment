package com.sedat.datatransferfragmenttofragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sedat.datatransferfragmenttofragment.databinding.FragmentFirstBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding ?= null
    private val binding get() = _binding!!

    private lateinit var preferenceDataStoreHelper: PreferenceDataStoreHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferenceDataStoreHelper = PreferenceDataStoreHelper(requireContext())

        binding.btnPassData.setOnClickListener{

            val data = binding.edtData.text.toString()

            if(binding.radioBtnArguments.isChecked){
                val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(dataOfArguments = data)
                findNavController().navigate(action)
            }else if(binding.radioBtnSharedPref.isChecked){
                lifecycleScope.launch(Dispatchers.IO) {
                    preferenceDataStoreHelper.saveData(data)
                    withContext(Dispatchers.Main){
                        findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
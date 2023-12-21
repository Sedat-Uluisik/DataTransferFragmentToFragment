package com.sedat.datatransferfragmenttofragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.sedat.datatransferfragmenttofragment.databinding.FragmentSecondBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding ?= null
    private val binding get() = _binding!!

    private val args: SecondFragmentArgs by navArgs()
    private lateinit var preferenceDataStoreHelper: PreferenceDataStoreHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferenceDataStoreHelper = PreferenceDataStoreHelper(requireContext())

        //arguments
        //binding.txtData.text = args.dataOfArguments

        //preferences datastore
        /*lifecycleScope.launch(Dispatchers.IO) {
            preferenceDataStoreHelper.getData().collect{
                withContext(Dispatchers.Main){
                    binding.txtData.text = it
                }
            }
        }*/

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
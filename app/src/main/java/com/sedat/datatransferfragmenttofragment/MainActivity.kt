package com.sedat.datatransferfragmenttofragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.sedat.datatransferfragmenttofragment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Transfer {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
        arguments +
        preferenceDatastore +
        sharedViewModel +
        interface
        globalItem +
         */

    }

    override fun passData(data: String) {
        val bundle = Bundle()
        bundle.putString("txtData", data)

        val transaction = this.supportFragmentManager.beginTransaction()
        val frag2 = SecondFragment()
        frag2.arguments = bundle

        transaction.replace(R.id.nav_host_fragment, frag2)
        transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()
    }
}
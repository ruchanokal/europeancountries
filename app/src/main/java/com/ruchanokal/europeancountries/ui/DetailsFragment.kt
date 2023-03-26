package com.ruchanokal.europeancountries.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.ruchanokal.europeancountries.R
import com.ruchanokal.europeancountries.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var binding : FragmentDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_details,container,false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {

            val bundle = it.getBundle("mybundle")

            //data binding kütüphanesi sayesinde dataları xml ile bağlıyoruz ve sadece
            //country tag'ine diğer fragmenttan gelen datayı atıyoruz.
            if (bundle != null) {
                binding!!.country = bundle.getParcelable("country")
            }

        }


    }


}
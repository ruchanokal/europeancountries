package com.ruchanokal.europeancountries.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.ruchanokal.europeancountries.R
import com.ruchanokal.europeancountries.databinding.FragmentMainBinding
import com.ruchanokal.europeancountries.model.Country
import com.ruchanokal.europeancountries.model.CountryItem
import com.ruchanokal.europeancountries.util.Constants.Companion.allcountries
import com.ruchanokal.europeancountries.util.Constants.Companion.central
import com.ruchanokal.europeancountries.util.Constants.Companion.eastern
import com.ruchanokal.europeancountries.util.Constants.Companion.northern
import com.ruchanokal.europeancountries.util.Constants.Companion.southeast
import com.ruchanokal.europeancountries.util.Constants.Companion.southern
import com.ruchanokal.europeancountries.util.Constants.Companion.western
import com.ruchanokal.europeancountries.util.Singleton
import com.ruchanokal.europeancountries.viewmodel.CountryViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Single

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val TAG = "MainFragment"

    private var binding: FragmentMainBinding? = null
    private lateinit var viewModel : CountryViewModel
    private var countryAdapter  = CountryAdapter(arrayListOf())
    private var countryList = arrayListOf<CountryItem>()
    lateinit var bottomSheetDialog: BottomSheetDialog


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main,container,false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = androidx.lifecycle.ViewModelProvider(this).get(CountryViewModel::class.java)
        viewModel.getCountryListData()
        initializeRecyclerViews()
        observeLiveData()

        searching()

        sorting()

        filteringCountries()

        unfocusSearchBarEditText()


    }

    private fun unfocusSearchBarEditText() {

        binding!!.searchBarEditText.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
                val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding!!.searchBarEditText.windowToken, 0)
                binding!!.searchBarEditText.clearFocus()
                return@setOnKeyListener true
            }
            false
        }
    }


    private fun filteringCountries() {

        binding!!.filter.setOnClickListener {

            val selectedButtonId = Singleton.selectedFilterRadioButtonId

            val dialogView2 = layoutInflater.inflate(R.layout.layout_filter, null)
            bottomSheetDialog = BottomSheetDialog(requireContext())
            bottomSheetDialog.setContentView(dialogView2)
            bottomSheetDialog.show()

            val southeastEurope = dialogView2.findViewById<RadioButton>(R.id.southeastEurope)
            val southernEurope = dialogView2.findViewById<RadioButton>(R.id.southernEurope)
            val northernEurope = dialogView2.findViewById<RadioButton>(R.id.northernEurope)
            val easternEurope = dialogView2.findViewById<RadioButton>(R.id.easternEurope)
            val centralEurope = dialogView2.findViewById<RadioButton>(R.id.centralEurope)
            val westernEurope = dialogView2.findViewById<RadioButton>(R.id.westernEurope)
            val allCountries = dialogView2.findViewById<RadioButton>(R.id.allCountries)

            val radioGroup = dialogView2.findViewById<RadioGroup>(R.id.radioGroup2)

            if (selectedButtonId != -1) {
                val selectedButton = dialogView2.findViewById<RadioButton>(selectedButtonId)
                selectedButton?.isChecked = true
            }


            radioGroup.setOnCheckedChangeListener { radioGroup, i ->

                val selectedButtonId = radioGroup.checkedRadioButtonId
                Singleton.selectedFilterRadioButtonId = selectedButtonId

            }

            southeastEurope.setOnCheckedChangeListener { compoundButton, b ->
                if (b){
                    viewModel.filterCountries(southeast,countryList)
                }
            }

            southernEurope.setOnCheckedChangeListener { compoundButton, b ->
                if (b){
                    viewModel.filterCountries(southern,countryList)
                }
            }

            northernEurope.setOnCheckedChangeListener { compoundButton, b ->
                if (b){
                    viewModel.filterCountries(northern,countryList)
                }
            }

            easternEurope.setOnCheckedChangeListener { compoundButton, b ->
                if (b){
                    viewModel.filterCountries(eastern,countryList)
                }
            }

            centralEurope.setOnCheckedChangeListener { compoundButton, b ->
                if (b){
                    viewModel.filterCountries(central,countryList)
                }
            }

            westernEurope.setOnCheckedChangeListener { compoundButton, b ->
                if (b){
                    viewModel.filterCountries(western,countryList)
                }
            }

            allCountries.setOnCheckedChangeListener { compoundButton, b ->
                if (b){
                    viewModel.filterCountries(allcountries,countryList)
                }
            }


        }
    }

    private fun searching() {

        binding!!.searchBarEditText.addTextChangedListener {
            viewModel.searchCountries(it?.toString(),countryList)
        }

    }

    private fun sorting() {

        binding!!.sort.setOnClickListener {

            val selectedButtonId = Singleton.selectedSortRadioButtonId

            val dialogView = layoutInflater.inflate(R.layout.layout_sort, null)
            bottomSheetDialog = BottomSheetDialog(requireContext())
            bottomSheetDialog.setContentView(dialogView)
            bottomSheetDialog.show()

            val aToZSort = dialogView.findViewById<RadioButton>(R.id.aToZSort)
            val zToASort = dialogView.findViewById<RadioButton>(R.id.zToASort)
            val defaultSort = dialogView.findViewById<RadioButton>(R.id.defaultSort)
            val populationAscendingSort = dialogView.findViewById<RadioButton>(R.id.populationAscendingSort)
            val populationDescendingSort = dialogView.findViewById<RadioButton>(R.id.populationDescendingSort)
            val radioGroup = dialogView.findViewById<RadioGroup>(R.id.radioGroup)

            if (selectedButtonId != -1) {
                val selectedButton = dialogView.findViewById<RadioButton>(selectedButtonId)
                selectedButton?.isChecked = true
            }


            radioGroup.setOnCheckedChangeListener { radioGroup, i ->

                val selectedButtonId = radioGroup.checkedRadioButtonId
                //sharedPref.edit().putInt(selectedSortRadioButtonId,selectedButtonId).apply()
                Singleton.selectedSortRadioButtonId = selectedButtonId
            }

            aToZSort.setOnCheckedChangeListener { compoundButton, b ->
                if (b){
                    val sortedList = countryList.sortedBy { it.name.official }
                    countryAdapter.updateCountryList(sortedList)
                }
            }

            zToASort.setOnCheckedChangeListener { compoundButton, b ->
                if (b){
                    val sortedList = countryList.sortedByDescending { it.name.official }
                    countryAdapter.updateCountryList(sortedList)
                }
            }

            defaultSort.setOnCheckedChangeListener { compoundButton, b ->
                if (b){
                    countryAdapter.updateCountryList(countryList)
                }
            }

            populationAscendingSort.setOnCheckedChangeListener { compoundButton, b ->
                if (b){
                    val sortedList = countryList.sortedBy { it.population }
                    countryAdapter.updateCountryList(sortedList)
                }
            }

            populationDescendingSort.setOnCheckedChangeListener { compoundButton, b ->
                if (b){
                    val sortedList = countryList.sortedByDescending { it.population }
                    countryAdapter.updateCountryList(sortedList)
                }
            }


        }

    }

    private fun observeLiveData() {

        //CountryViewModelda yapılan değişiklikleri burada gözlemliyoruz
        viewModel.loadingLiveData.observe(viewLifecycleOwner, Observer { loadingData ->

            if (loadingData){

                binding!!.countryRecyclerView.visibility = View.GONE
                binding!!.errorTryAgainLayout.visibility = View.GONE
                binding!!.progressBar.visibility = View.VISIBLE

            } else {

                binding!!.progressBar.visibility = View.GONE
            }

        })

        viewModel.errorLiveData.observe(viewLifecycleOwner, Observer { errorData ->

            if (errorData){

                binding!!.countryRecyclerView.visibility = View.GONE
                binding!!.errorTryAgainLayout.visibility = View.VISIBLE
                binding!!.progressBar.visibility = View.GONE

            } else {

                binding!!.errorTryAgainLayout.visibility = View.GONE
            }

        })

        viewModel.countryData.observe(viewLifecycleOwner, Observer { countryData ->

            countryData?.let {
                binding!!.countryRecyclerView.visibility = View.VISIBLE
                countryAdapter.updateCountryList(it)
                countryList = it as ArrayList<CountryItem>
            }
        })

        viewModel.tempCountryData.observe(viewLifecycleOwner, Observer { tempCountryData ->

            tempCountryData?.let {
                countryAdapter.updateCountryList(it)
            }
        })

        viewModel.filteredCountryData.observe(viewLifecycleOwner, Observer { filteredCountryData ->

            filteredCountryData?.let {
                countryAdapter.updateCountryList(it)
            }
        })
    }


    private fun initializeRecyclerViews() {

        val llm  =  LinearLayoutManager(requireContext())
        binding!!.countryRecyclerView.layoutManager = llm

        //RecyclerViewlarla adapterları bağlıyoruz
        binding!!.countryRecyclerView.adapter = countryAdapter
    }


}
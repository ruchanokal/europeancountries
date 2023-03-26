package com.ruchanokal.europeancountries.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ruchanokal.europeancountries.R
import com.ruchanokal.europeancountries.databinding.CountryRowBinding
import com.ruchanokal.europeancountries.model.CountryItem
import com.ruchanokal.europeancountries.util.Singleton

class CountryAdapter(val list : ArrayList<CountryItem>) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    private val TAG = "CountryAdapter"

    class CountryViewHolder(val binding: CountryRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = DataBindingUtil.inflate<CountryRowBinding>(LayoutInflater.from(parent.context), R.layout.country_row,parent,false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.binding.country = list.get(position)

        holder.itemView.setOnClickListener {

            Log.e(TAG,"country: " + list.get(position))


            //diğer fragmenta göndermek için hazırlanmış country data paketi
            val bundle = Bundle()
            bundle.putParcelable("country",list.get(position))


            // filtreleme ve sıralama ayarlarını burda temizliyoruz
            // ux standartlarına daha uygun bir kullanım
            Singleton.selectedFilterRadioButtonId = -1
            Singleton.selectedSortRadioButtonId = -1


            //animasyon efekti
            val navOptions = NavOptions.Builder()
                .setEnterAnim(R.anim.slide)
                .build()

            val action = MainFragmentDirections.actionMainFragmentToDetailsFragment(bundle)
            Navigation.findNavController(it).navigate(action,navOptions)

        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    //adapterı güncellemek için yazılan fonksiyon
    fun updateCountryList(myNewList : List<CountryItem>){
        list.clear()
        list.addAll(myNewList)
        notifyDataSetChanged()
    }
}
package tam.pa.al_quransederhana.ui.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import tam.pa.al_quransederhana.R
import tam.pa.al_quransederhana.databinding.FragmentSearchBinding
import tam.pa.al_quransederhana.helper.Resource
import tam.pa.al_quransederhana.network.response.ListSurahRespone
import tam.pa.al_quransederhana.ui.detail.DetailActivity
import tam.pa.al_quransederhana.ui.home.HomeViewModel
import tam.pa.al_quransederhana.ui.search.adapter.SearchAdapter

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val homeViewModel by lazy { ViewModelProvider(requireActivity()).get(HomeViewModel::class.java) }
    private lateinit var searchAdapter: SearchAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        setupRecyclerview()
        setupListener()
    }

    private fun setupListener() {
        binding.etSearch.doAfterTextChanged {
            searchAdapter.filter.filter( it.toString() )
        }
        binding.refreshList.setOnRefreshListener {
            homeViewModel.fetchListSurah()
        }
    }

    private fun setupObserver() {
        homeViewModel.listSurahResponse.observe(viewLifecycleOwner, Observer {
            when( it ){
                is Resource.Loading -> Log.d("_result2", "Loading")
                is Resource.Success -> {
                    Log.d("_result2", it.data.toString() )
                    searchAdapter.setData( it.data!!.data )
                }
                is Resource.Error -> Log.d("_result2", it.message.toString() )
            }
        })
    }

    private fun setupRecyclerview() {
        searchAdapter = SearchAdapter( arrayListOf(), object : SearchAdapter.OnAdapterListener{
            override fun OnClick(result: ListSurahRespone.DetailSurah) {
                startActivity(Intent(requireContext(), DetailActivity::class.java)
                    .putExtra("numberSurah", result.number.toString() ))
            }
        })
        binding.rvListSearch.adapter = searchAdapter
    }

}
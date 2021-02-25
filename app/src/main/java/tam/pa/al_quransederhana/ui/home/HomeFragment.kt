package tam.pa.al_quransederhana.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import tam.pa.al_quransederhana.R
import tam.pa.al_quransederhana.databinding.FragmentHomeBinding
import tam.pa.al_quransederhana.helper.Resource
import tam.pa.al_quransederhana.network.response.ListSurahRespone
import tam.pa.al_quransederhana.ui.detail.DetailActivity
import tam.pa.al_quransederhana.ui.home.adapter.ListSurahAdapter

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel by lazy { ViewModelProvider(requireActivity()).get( HomeViewModel::class.java ) }
    private lateinit var surahAdapter: ListSurahAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupRecyclerView()
        setupListener()
        setupObserver()
    }

    private fun setupListener() {
        binding.etSearch.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }
        binding.refreshList.setOnRefreshListener {
            viewModel.fetchListSurah()
        }
    }

    private fun setupRecyclerView() {
        surahAdapter = ListSurahAdapter( arrayListOf(), object : ListSurahAdapter.OnAdapterListener{
            override fun OnClick(result: ListSurahRespone.DetailSurah) {
                requireActivity().startActivity(
                    Intent(requireContext(), DetailActivity::class.java)
                        .putExtra("numberSurah", result.number.toString() )
                )
            }
        })
        binding.rvListSurah.adapter = surahAdapter
    }

    private fun setupView() {

    }

    private fun setupObserver() {
        viewModel.listSurahResponse.observe(viewLifecycleOwner, Observer {
            when( it ){
                is Resource.Loading -> {
                    binding.refreshList.isRefreshing = true
                    binding.rolling.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.refreshList.isRefreshing = false
                    binding.rolling.visibility = View.GONE
                    Log.d("_result", it.data.toString() )
                    surahAdapter.setData( it.data!!.data )
                }
                is Resource.Error -> {
                    binding.rolling.visibility = View.GONE
                    binding.refreshList.isRefreshing = false
                }
            }
        })
    }
}
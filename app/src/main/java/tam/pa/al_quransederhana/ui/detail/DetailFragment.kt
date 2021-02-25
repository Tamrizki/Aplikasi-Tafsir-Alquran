package tam.pa.al_quransederhana.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import tam.pa.al_quransederhana.R
import tam.pa.al_quransederhana.databinding.FragmentDetailBinding
import tam.pa.al_quransederhana.helper.Resource
import tam.pa.al_quransederhana.network.response.DetailSurahResponse
import tam.pa.al_quransederhana.ui.detail.adapter.AyatAdapter

class DetailFragment : Fragment() {
    private val noSurah by lazy { requireActivity().intent.getStringExtra("numberSurah") }
    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(DetailViewModel::class.java) }
    private lateinit var binding: FragmentDetailBinding
    private lateinit var ayatAdapter: AyatAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchDetailSurah( noSurah.toString() )
        setupObserver()
        setupListsner()
    }

    private fun setupListsner() {
    }

    private fun setupObserver() {
        viewModel.detailResponse.observe(viewLifecycleOwner, Observer {
            when( it ){
                is Resource.Loading ->{
                    binding.rolling.visibility = View.VISIBLE
                }
                is Resource.Success ->{
                    binding.rolling.visibility = View.GONE
                    setupView(it.data)
                    setupRecyclerView( it.data!!.data.verses )
                }
                is Resource.Error ->{
                    binding.rolling.visibility = View.GONE
                }
            }
        })
    }

    private fun setupRecyclerView(verses: List<DetailSurahResponse.DataSurah.VersesSurah>) {
        ayatAdapter = AyatAdapter( verses, object : AyatAdapter.OnAdapterListener{
            override fun OnCick(result: DetailSurahResponse.DataSurah.VersesSurah) {
                viewModel.fetchTafsir(
                    noSurah.toString(),
                    result.number.inSurah.toString())
                findNavController().navigate(R.id.action_detailFragment_to_tafsirFragment)
            }
        })
        binding.rvListAyat.adapter = ayatAdapter
    }

    private fun setupView(data: DetailSurahResponse?) {
        binding.tvNumberSurah.setText( data!!.data.number.toString() )
        binding.tvNameSurah.setText( data!!.data.name.long+" | "+ data!!.data.name.transliteration.id )
        binding.tvInfoSurah.setText( data!!.data.numberOfVerses.toString()+" | "+ data!!.data.revelation.id)
    }


}
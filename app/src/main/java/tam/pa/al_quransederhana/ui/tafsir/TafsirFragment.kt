package tam.pa.al_quransederhana.ui.tafsir

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import tam.pa.al_quransederhana.R
import tam.pa.al_quransederhana.databinding.FragmentTafsirBinding
import tam.pa.al_quransederhana.helper.Helper
import tam.pa.al_quransederhana.helper.Resource
import tam.pa.al_quransederhana.network.response.TafsirResponse
import tam.pa.al_quransederhana.ui.detail.DetailViewModel

class TafsirFragment : Fragment() {
    private lateinit var binding: FragmentTafsirBinding
    private val detailViewModel by lazy { ViewModelProvider(requireActivity()).get( DetailViewModel::class.java ) }
    private var isPlaying = false
    private val mediaHelper by lazy { Helper(requireContext()) }
    private var mediaUrl = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTafsirBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        setupListener()
        Log.d("checkMedia", mediaHelper.checkMedia().toString() )
    }

    private fun setupListener() {
        binding.btnAudio.setOnClickListener {
            if (!isPlaying){
                isPlaying = mediaHelper.playAudio( mediaUrl )
            }else{
                isPlaying = mediaHelper.stopAudio()
            }
            setupButtonAudio( isPlaying )
        }
    }

    private fun setupObserver() {
        detailViewModel.tafsirResponse.observe(viewLifecycleOwner, Observer {
            when( it ){
                is Resource.Loading ->{
                    binding.rolling.visibility = View.VISIBLE
                }
                is Resource.Success ->{
                    binding.rolling.visibility = View.GONE
                    stupView(it.data!!.data)
                    mediaUrl = it.data.data.audio.secondary[0].toString()
                }
                is Resource.Error ->{
                    binding.rolling.visibility = View.GONE
                }
            }
        })
    }

    private fun stupView(data: TafsirResponse.Data) {
        binding.tvArab.setText( data.text.arab )
        binding.tvTerjemah.setText( data.translation.id )
        binding.tvTafsirShort.setText( data.tafsir.id.short )
        binding.tvTafsirLong.setText( data.tafsir.id.long )
        binding.tvTitle.setText( data.surah.name.transliteration.id+" : "+data.number.inSurah.toString() )
    }

    fun setupButtonAudio(Playing: Boolean){
        if (Playing){
            binding.btnAudio.setBackgroundDrawable( requireContext().getDrawable(R.drawable.ic_stop_button))
        }else{
            binding.btnAudio.setBackgroundDrawable( requireContext().getDrawable(R.drawable.ic_play_button))
        }
    }
}
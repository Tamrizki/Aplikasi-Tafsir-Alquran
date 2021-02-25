package tam.pa.al_quransederhana.ui.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tam.pa.al_quransederhana.helper.Resource
import tam.pa.al_quransederhana.network.AlquranRepository
import tam.pa.al_quransederhana.network.response.DetailSurahResponse
import tam.pa.al_quransederhana.network.response.TafsirResponse
import java.lang.Exception

class DetailViewModel(
    private val repository: AlquranRepository
): ViewModel() {

    val detailResponse: MutableLiveData<Resource<DetailSurahResponse>> = MutableLiveData()
    val tafsirResponse: MutableLiveData<Resource<TafsirResponse>> = MutableLiveData()

    fun fetchDetailSurah(
        surah: String
    ) = viewModelScope.launch {
        detailResponse.value = Resource.Loading()
        Log.d("_result3", "Loading!!")
        try {
            val response = repository.fetchDetailSurah( surah )
            detailResponse.value = Resource.Success( response.body()!! )
            Log.d("_result3", response.body().toString() )

        }catch ( e: Exception){
            detailResponse.value = Resource.Error( e.message.toString() )
            Log.d("_result3", e.message.toString() )

        }
    }

    fun fetchTafsir(
        surah: String,
        ayat: String
    ) = viewModelScope.launch {
        tafsirResponse.value = Resource.Loading()
        try {
            val response = repository.fetchTafsir(surah, ayat)
            tafsirResponse.value = Resource.Success( response.body()!! )
        }catch ( e: Exception ){
            tafsirResponse.value = Resource.Error( e.message.toString() )
        }
    }

}
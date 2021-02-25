package tam.pa.al_quransederhana.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tam.pa.al_quransederhana.helper.Resource
import tam.pa.al_quransederhana.network.AlquranRepository
import tam.pa.al_quransederhana.network.response.ListSurahRespone

class HomeViewModel(
    private val repository: AlquranRepository
): ViewModel() {

    val listSurahResponse: MutableLiveData<Resource<ListSurahRespone>> = MutableLiveData()

    fun fetchListSurah() = viewModelScope.launch {
        listSurahResponse.value = Resource.Loading()
        try {
            val response = repository.fetchListSurah()
            listSurahResponse.value = Resource.Success( response.body()!! )
        }catch ( e: Exception ){
            listSurahResponse.value = Resource.Error( e.message.toString() )
        }
    }

}
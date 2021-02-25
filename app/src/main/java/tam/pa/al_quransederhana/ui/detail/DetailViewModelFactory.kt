package tam.pa.al_quransederhana.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tam.pa.al_quransederhana.network.AlquranRepository

class DetailViewModelFactory(
    private val repository: AlquranRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel( repository ) as T
    }
}
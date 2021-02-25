package tam.pa.al_quransederhana.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tam.pa.al_quransederhana.network.AlquranRepository

class HomeViewModelFactory(
    private val repository: AlquranRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel( repository ) as T
    }
}
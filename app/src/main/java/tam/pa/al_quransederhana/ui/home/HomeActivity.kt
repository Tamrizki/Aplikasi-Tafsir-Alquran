package tam.pa.al_quransederhana.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import tam.pa.al_quransederhana.R
import tam.pa.al_quransederhana.databinding.ActivityMainBinding
import tam.pa.al_quransederhana.helper.Resource
import tam.pa.al_quransederhana.ui.search.SearchFragment

class HomeActivity : AppCompatActivity(), KodeinAware {
    override val kodein by closestKodein()
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val homeFactory: HomeViewModelFactory by instance()
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViewModel()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, homeFactory ).get( HomeViewModel::class.java )
        viewModel.fetchListSurah()
    }
}
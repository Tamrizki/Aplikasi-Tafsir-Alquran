package tam.pa.al_quransederhana

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import tam.pa.al_quransederhana.network.AlquranRepository
import tam.pa.al_quransederhana.network.ApiService
import tam.pa.al_quransederhana.network.EndPoint
import tam.pa.al_quransederhana.ui.detail.DetailViewModelFactory
import tam.pa.al_quransederhana.ui.home.HomeViewModelFactory

class AlquranAplication: Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@AlquranAplication))
        bind<EndPoint>() with singleton { ApiService.getClient() }
        bind() from provider { AlquranRepository( instance() ) }
        bind() from provider { HomeViewModelFactory( instance() ) }
        bind() from provider { DetailViewModelFactory( instance() ) }
    }
}
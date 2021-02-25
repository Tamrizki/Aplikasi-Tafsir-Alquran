package tam.pa.al_quransederhana.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val baseurl = "https://api.quran.sutanlab.id"

object ApiService {

    fun getClient(): EndPoint{

        val logging = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor( logging )
            .build()

        val gson = GsonBuilder().serializeNulls().create()
        return Retrofit.Builder()
            .baseUrl( baseurl )
            .addConverterFactory( GsonConverterFactory.create( gson ))
            .client( client )
            .build()
            .create( EndPoint::class.java )


    }
}
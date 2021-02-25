package tam.pa.al_quransederhana.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import tam.pa.al_quransederhana.network.response.DetailSurahResponse
import tam.pa.al_quransederhana.network.response.ListSurahRespone
import tam.pa.al_quransederhana.network.response.TafsirResponse

interface EndPoint {

    @GET("surah")
    suspend fun listSurah(): Response<ListSurahRespone>

    @GET("surah/{surah}")
    suspend fun detailSurah(
        @Path("surah") surah: String ): Response<DetailSurahResponse>

    @GET("surah/{surah}/{ayah}")
    suspend fun tafsirSurah(
        @Path("surah") surah: String,
        @Path("ayah") ayah: String ): Response<TafsirResponse>

}
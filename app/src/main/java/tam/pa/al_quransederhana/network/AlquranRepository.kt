package tam.pa.al_quransederhana.network

class AlquranRepository(
    private val api: EndPoint
) {
    suspend fun fetchListSurah() = api.listSurah()

    suspend fun fetchDetailSurah(
        surah: String
    ) = api.detailSurah( surah )

    suspend fun fetchTafsir(
        surah: String,
        ayah: String
    ) = api.tafsirSurah( surah, ayah )
}
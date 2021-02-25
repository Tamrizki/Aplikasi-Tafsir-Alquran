package tam.pa.al_quransederhana.network.response

data class TafsirResponse(
    val code: Int,
    val status: String,
    val message: String,
    val data: Data
) {
    data class Data(
        val number: NumberSurah,
        val meta: Meta,
        val text: Text,
        val translation: Translation,
        val audio: Audio,
        val tafsir: Tafsir,
        val surah: Surah,
    ){
        data class NumberSurah(
            val inQuran: Int,
            val inSurah: Int
        )
        data class Meta(
            val juz: Int,
            val page: Int,
            val manzil: Int,
            val ruku: Int,
            val hizbQuarter: Int,
            val sajda: Sajda
        ){
            data class Sajda(
                val recommended: Boolean,
                val obligatory: Boolean
            )
        }
        data class Text(
            val arab: String,
            val transliteration: Transliteration
        ){
            data class Transliteration(
                val en: String
            )
        }
        data class Translation(
            val en: String,
            val id: String
        )
        data class Audio(
            val primary: String,
            val secondary: List<String>
        )
        data class Tafsir(
            val id: Id
        ){
            data class Id(
                val short: String,
                val long: String
            )
        }
        data class Surah(
            val number: Int,
            val sequence: Int,
            val numberOfVerses: Int,
            val name: NameSurah,
            val revelation: Revelation,
            val tafsir: SubTafsir,
        ){
            data class NameSurah(
                val short: String,
                val long: String,
                val transliteration: Transliteration,
                val translation: SubTranslation
            ){
                data class Transliteration(
                    val en: String,
                    val id: String
                )
                data class SubTranslation(
                    val en: String,
                    val id: String
                )
            }
            data class Revelation(
                val arab: String,
                val en: String,
                val id: String
            )
            data class SubTafsir(
                val id: String
            )
        }
    }
}
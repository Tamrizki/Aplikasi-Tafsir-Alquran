package tam.pa.al_quransederhana.network.response

data class DetailSurahResponse(
    val code: Int,
    val status: String,
    val message: String,
    val data: DataSurah
) {
    data class DataSurah(
        val number: Int,
        val sequence: Int,
        val numberOfVerses: Int,
        val name: NameSurah,
        val revelation: RevelationSurah,
        val tafsir: TafsirSurah,
        val preBismillah: PreBismillahSurah,
        val verses: List<VersesSurah>
    ){
        data class NameSurah(
            val short: String,
            val long: String,
            val transliteration: TransliterationSurah,
            val translation: TranslationSurah
        ){
            data class TransliterationSurah(
                val en: String,
                val id: String
            )
            data class TranslationSurah(
                val en: String,
                val id: String
            )
        }
        data class RevelationSurah(
            val arab: String,
            val en: String,
            val id: String
        )
        data class TafsirSurah(
            val id: String
        )
        data class PreBismillahSurah(
            val text: Text,
            val translation: Translation,
            val audio: Audio,
        ){
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
        }
        data class VersesSurah(
            val number: NumberSurah,
            val meta: Meta,
            val text: Text,
            val translation: Translation,
            val audio: Audio,
            val tafsir: Tafsir,
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
        }
    }
}
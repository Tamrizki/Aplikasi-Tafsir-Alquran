package tam.pa.al_quransederhana.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tam.pa.al_quransederhana.databinding.CustomListAyahBinding
import tam.pa.al_quransederhana.network.response.DetailSurahResponse

class AyatAdapter(val listAyat: List<DetailSurahResponse.DataSurah.VersesSurah>,
                  val listener: OnAdapterListener
               ): RecyclerView.Adapter<AyatAdapter.vHolder>() {
    class vHolder( val binding: CustomListAyahBinding): RecyclerView.ViewHolder( binding.root )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = vHolder(
        CustomListAyahBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: vHolder, position: Int) {
        val ayat = listAyat[ position ]
        holder.binding.tvArabic.setText( ayat.text.arab )
        holder.binding.tvLatin.setText( ayat.text.transliteration.en )
        holder.binding.tvTerjemah.setText( ayat.translation.id )
        holder.binding.container.setOnClickListener {
            listener.OnCick( ayat )
        }
    }

    override fun getItemCount() = listAyat.size

    interface OnAdapterListener{
        fun OnCick( result: DetailSurahResponse.DataSurah.VersesSurah )
    }
}
package tam.pa.al_quransederhana.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tam.pa.al_quransederhana.databinding.CustomListSuratBinding
import tam.pa.al_quransederhana.network.response.ListSurahRespone

class ListSurahAdapter(
        val listSurah: ArrayList<ListSurahRespone.DetailSurah>,
        val listener: OnAdapterListener): RecyclerView.Adapter<ListSurahAdapter.vHolder>() {

    class vHolder( val binding: CustomListSuratBinding ): RecyclerView.ViewHolder( binding.root )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = vHolder(
        CustomListSuratBinding.inflate( LayoutInflater.from(parent.context), parent, false )
    )

    override fun onBindViewHolder(holder: ListSurahAdapter.vHolder, position: Int) {
        val dataSurah = listSurah[ position ]
        holder.binding.tvAyah.setText( dataSurah.name.long )
        holder.binding.tvDetail.setText( dataSurah.revelation.id+" | "+dataSurah.numberOfVerses)
        holder.binding.tvNumberAyah.setText( dataSurah.number.toString() )
        holder.binding.container.setOnClickListener {
            listener.OnClick( dataSurah )
        }
    }

    override fun getItemCount() = listSurah.size

    interface OnAdapterListener{
        fun OnClick( result: ListSurahRespone.DetailSurah )
    }

    fun setData( data : List<ListSurahRespone.DetailSurah> ){
        listSurah.clear()
        listSurah.addAll( data )
        notifyDataSetChanged()
    }
}
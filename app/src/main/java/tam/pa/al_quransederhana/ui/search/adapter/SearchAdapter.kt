package tam.pa.al_quransederhana.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import tam.pa.al_quransederhana.databinding.CustomListSearchSurahBinding
import tam.pa.al_quransederhana.network.response.ListSurahRespone

class SearchAdapter(
        val listSurah: ArrayList<ListSurahRespone.DetailSurah>,
        val listener: OnAdapterListener): RecyclerView.Adapter<SearchAdapter.vHolder>(), Filterable {

    class vHolder( val binding: CustomListSearchSurahBinding): RecyclerView.ViewHolder( binding.root )

    private var searchResult = ArrayList< ListSurahRespone.DetailSurah >()

    init {
        searchResult = listSurah
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = vHolder(
            CustomListSearchSurahBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: vHolder, position: Int) {
        val dataSurah = searchResult[ position ]
        holder.binding.textIndo.setText( dataSurah.name.transliteration.id )
        holder.binding.container.setOnClickListener {
            listener.OnClick( dataSurah )
        }
    }

    override fun getItemCount() = searchResult.size

    interface OnAdapterListener{
        fun OnClick( result: ListSurahRespone.DetailSurah )
    }

    fun setData(data: List<ListSurahRespone.DetailSurah> ){
        listSurah.clear()
        listSurah.addAll( data )
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(dataSequence: CharSequence?): FilterResults {
                val charSequence = dataSequence.toString()

                if ( charSequence.isEmpty() ){
                    searchResult = listSurah
                } else {
                    val surahFiltered = ArrayList<ListSurahRespone.DetailSurah>()
                    for ( surah in listSurah ){
                        if (surah.name.transliteration.id.toLowerCase().contains( charSequence.toLowerCase())){
                            surahFiltered.add( surah )
                        }
                    }
                    searchResult = surahFiltered
                }
                val finalResult = FilterResults()
                finalResult.values = searchResult
                return finalResult
            }

            override fun publishResults(p0: CharSequence?, results: FilterResults?) {
                searchResult = results?.values as ArrayList<ListSurahRespone.DetailSurah>
                notifyDataSetChanged()
            }
        }
    }
}
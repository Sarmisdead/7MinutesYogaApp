package sarmisdead.a7minutesworkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import sarmisdead.a7minutesworkout.databinding.ItemsRowBinding

class HistoryAdapter(private val items : ArrayList<String>) :
      RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemsRowBinding) : RecyclerView.ViewHolder(binding.root){

        val llHistoryItemMain = binding.llHistoryItemMain
        val tvItem = binding.tvItem
        val tvPosition = binding.tvPosition

    } //End of ViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(ItemsRowBinding.inflate(LayoutInflater.from(parent.context),
        parent, false))

    } //End of onCreateViewHolder

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val date : String = items.get(position)
        holder.tvPosition.text = (position + 1).toString()
        holder.tvItem.text = date

        if(position % 2 == 0){

            holder.llHistoryItemMain.setBackgroundColor(
                ContextCompat.getColor(holder.itemView.context,
                R.color.lightYellow))

        } else {

            holder.llHistoryItemMain.setBackgroundColor(
                ContextCompat.getColor(holder.itemView.context,
                R.color.white))

        } //End of IF

    } //End of onBindViewHolder

    override fun getItemCount(): Int {

        return items.size

    } //End of getItemCount

} //End of HistoryAdapter
package sarmisdead.a7minutesworkout

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import sarmisdead.a7minutesworkout.databinding.ItemExerciseStatusBinding

class ExerciseStatusAdapter(val items: ArrayList<ExerciseModel>):
    RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() { //End of ExerciseStatusAdapter

    class ViewHolder(binding: ItemExerciseStatusBinding): RecyclerView.ViewHolder(binding.root){

        val tvItem =  binding.tvItem

    } //End of ViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(ItemExerciseStatusBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))

    } //End of onCreateViewHolder

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val model : ExerciseModel = items[position]
        holder.tvItem.text = model.getId().toString()

        when{

            model.getIsSelected() ->{
                holder.tvItem.background =
                    ContextCompat.getDrawable(holder.itemView.context,
                    R.drawable.item_circular_actual_position_color_background)
                holder.tvItem.setTextColor(Color.parseColor( "#2B316E"))

            } //End of model.getIsSelected
            model.getIsCompleted() ->{

                holder.tvItem.background =
                    ContextCompat.getDrawable(holder.itemView.context,
                        R.drawable.item_circular_completed_position_color_background)
                holder.tvItem.setTextColor(Color.parseColor( "#FBF4F2"))

            } else -> {

            holder.tvItem.background =
                ContextCompat.getDrawable(holder.itemView.context,
                    R.drawable.item_circular_color_background)
            holder.tvItem.setTextColor(Color.parseColor( "#FBF4F2"))

            } //End of ELSE

        } //End of WHEN

    } //End of onBindViewHolder

    override fun getItemCount(): Int {

        return items.size

    } //End of getItemCount

}
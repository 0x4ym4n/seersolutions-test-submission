package seersolutions.dashboard.ui.list

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import seersolutions.dashboard.R
import seersolutions.dashboard.databinding.StoryItemBinding


class StoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding: StoryItemBinding? = androidx.databinding.DataBindingUtil.bind(view)
    val name: TextView = view.findViewById(R.id.title)
    val by: TextView = view.findViewById(R.id.by)
    val image: ImageView = view.findViewById(R.id.image)

}
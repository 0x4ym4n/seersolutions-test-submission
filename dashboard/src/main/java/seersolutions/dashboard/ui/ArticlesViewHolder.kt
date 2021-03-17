package seersolutions.dashboard.ui.list

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import seersolutions.dashboard.R
import seersolutions.dashboard.databinding.ArticleItemBinding
import seersolutions.dashboard.databinding.StoryItemBinding


class ArticlesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding: ArticleItemBinding? = androidx.databinding.DataBindingUtil.bind(view)
    val name: TextView = view.findViewById(R.id.title)
    val source: TextView = view.findViewById(R.id.source)
    val image: ImageView = view.findViewById(R.id.image)

}
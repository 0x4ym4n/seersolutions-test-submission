package seersolutions.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import seersolutions.base.domain.ArticleDoa
import seersolutions.dashboard.ui.list.ArticlesViewHolder


class ArticlesAdapter(

    var articles: List<ArticleDoa>,
    var activityDashboard: ActivityDashboard
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ArticlesViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.article_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as ArticlesViewHolder).binding
        binding?.article = articles[position]
        binding?.executePendingBindings()
        holder.name.text = articles[position].title
        holder.source.text = articles[position].source

        Glide.with(activityDashboard).load(articles[position].thumbnail)
            .crossFade()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.image)

    }


    override fun getItemCount() = articles.size

}
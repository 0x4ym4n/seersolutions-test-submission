package seersolutions.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import seersolutions.base.domain.StoryDoa
import seersolutions.dashboard.ui.list.StoryViewHolder


class StoriesAdapter(

    var stories: List<StoryDoa>,
    var activityDashboard: ActivityDashboard
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return StoryViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.story_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as StoryViewHolder).binding
        binding?.story = stories[position]
        binding?.executePendingBindings()

        holder.name.text = stories[position].title
        holder.by.text = stories[position].by

        Glide.with(activityDashboard).load(stories[position].thumbnail)
            .crossFade()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.image)

    }


    override fun getItemCount() = stories.size

}
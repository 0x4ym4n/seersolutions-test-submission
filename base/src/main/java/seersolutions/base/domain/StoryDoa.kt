package seersolutions.base.domain

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "Stories", indices = [Index(value = ["id"], unique = true)])
data class StoryDoa(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    var title: String,
    var by: String,
    var url: String,
    var thumbnail: String
)



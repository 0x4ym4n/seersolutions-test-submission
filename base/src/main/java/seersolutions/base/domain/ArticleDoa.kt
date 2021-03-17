package seersolutions.base.domain

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "Articles", indices = [Index(value = ["id"], unique = true)])
data class ArticleDoa(
        @PrimaryKey(autoGenerate = true) var id: Int?,
        var title: String,
        var by: String,
        var url: String,
        var thumbnail: String,
        var source: String
)



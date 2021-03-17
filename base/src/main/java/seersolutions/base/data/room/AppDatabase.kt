package seersolutions.base.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import seersolutions.base.domain.ArticleDoa
import seersolutions.base.domain.LoginDoa
import seersolutions.base.domain.StoryDoa


@Database(
    entities = [ArticleDoa::class, StoryDoa::class, LoginDoa::class],
    version = AppDatabase.VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun roomDoa(): RoomDoa

    companion object {
        const val VERSION = 3
    }

}
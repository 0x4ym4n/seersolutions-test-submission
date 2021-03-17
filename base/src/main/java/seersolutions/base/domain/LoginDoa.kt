package seersolutions.base.domain

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "login", indices = [Index(value = ["username"], unique = true)])
data class LoginDoa(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    var username: String,
    var token: String
)
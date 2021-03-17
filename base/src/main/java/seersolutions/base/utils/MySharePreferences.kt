package seersolutions.base.utils

import android.content.SharedPreferences
import javax.inject.Inject

class MySharePreferences @Inject constructor(val mSharedPreferences: SharedPreferences) {
    fun putData(key: String, data: String) = mSharedPreferences.edit().putString(key, data).apply()
    fun getData(key: String): String = mSharedPreferences.getString(key, "")!!
}

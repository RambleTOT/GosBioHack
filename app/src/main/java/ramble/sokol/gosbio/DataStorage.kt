package ramble.sokol.gosbio

import android.content.Context
import android.content.SharedPreferences

class DataStorage(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun saveData(key: String, value: Int) {
        editor.putInt(key, value)
        editor.apply()
    }

    fun getData(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }
}
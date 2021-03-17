package seersolutions.base.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.content.ContextCompat
import seersolutions.base.R


object Utils {




    fun showAlert(context: Activity?, message: String) {
        val builder = AlertDialog.Builder(context)
        val view: View = context!!.layoutInflater.inflate(R.layout.row_error_alert, null)
        builder.setView(view)

        val title = view.findViewById(R.id.title) as TextView
        title.text = message
        builder.setPositiveButton(context.getString(android.R.string.ok)) { dialog, which ->
            dialog.dismiss()
        }
        val dialog: AlertDialog = builder.create()

        dialog.show()
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
            .setTextColor(ContextCompat.getColor(context, R.color.black_text_color))
        dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            .setTextColor(ContextCompat.getColor(context, R.color.black_text_color))
    }


    fun isNetConnected(mContext: Context): Boolean {
        val cm = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }



    fun hideSoftKeyboard(context: Activity) {
        if (context.currentFocus != null) {
            val inputMethodManager =
                context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(context.currentFocus!!.windowToken, 0)
        }
    }
    fun sendUnauthorized(context: Activity?) {
        try {
            val activity = context as Activity
            val intent = Intent(activity, Class.forName("seersolutions.login.LoginActivity"))
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("Unauthorized", "Unauthorized")
            activity.startActivity(intent)
            activity.finish()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }

    }




}
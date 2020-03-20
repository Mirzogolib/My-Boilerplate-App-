package com.example.myboilerplateapp.extensions

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.myboilerplateapp.R
import com.example.myboilerplateapp.model.system.ResourceManager
import retrofit2.HttpException
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.BackTo
import ru.terrakok.cicerone.commands.Replace
import java.io.IOException
import java.text.DecimalFormat


fun View.visible(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.GONE
}

fun View.showOrHide() {
    visibility = if (visibility == View.VISIBLE) View.GONE else View.VISIBLE
}

fun Fragment.toast(message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun Activity.toastActivity(message: String) {
    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun String.currency(): String = DecimalFormat("#,###").format(this.toDouble())
fun String.convertBack(): String = this.replace(",", " ")

fun EditText.showKeyboard() {
    requestFocus()
    post {
        val inputManager =
            this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        inputManager?.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }
}

fun EditText.hideKeyboard() {
    post {
        val inputManager =
            this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        inputManager?.hideSoftInputFromWindow(windowToken, InputMethodManager.SHOW_IMPLICIT)
    }
}

fun Context.color(colorRes: Int) = ContextCompat.getColor(this, colorRes)


fun Throwable.toUserMessage(resourceManager: ResourceManager) = when (this) {
    is HttpException -> when (this.code()) {
        304 -> resourceManager.getString(R.string.not_modified_error)
        400 -> resourceManager.getString(R.string.bad_request_error)
        401 -> resourceManager.getString(R.string.unauthorized_error)
        403 -> resourceManager.getString(R.string.forbidden_error)
        404 -> resourceManager.getString(R.string.not_found_error)
        405 -> resourceManager.getString(R.string.method_not_allowed_error)
        409 -> resourceManager.getString(R.string.conflict_error)
        422 -> resourceManager.getString(R.string.unprocessable_error)
        500 -> resourceManager.getString(R.string.server_error_error)
        else -> resourceManager.getString(R.string.unknown_error)
    }
    is IOException -> resourceManager.getString(R.string.network_error)
    else -> resourceManager.getString(R.string.unknown_error)


}

fun Navigator.setLaunchScreen(screen: SupportAppScreen) {
    applyCommands(
        arrayOf(
            BackTo(null),
            Replace(screen)
        )
    )
}


fun setImage(
    buttonId: Int,
    homeButton: AppCompatImageButton,
    favouriteButton: AppCompatImageButton,
    publishButton: AppCompatImageButton,
    accountButton: AppCompatImageButton
) {
    when (buttonId) {
        R.id.homeButton -> {
            homeButton.setImageResource(R.drawable.ic_home_green)
            favouriteButton.setImageResource(R.drawable.ic_heart_grey)
            publishButton.setImageResource(R.drawable.ic_publish_grey)
            accountButton.setImageResource(R.drawable.ic_account_grey)
        }
        R.id.favouriteButton -> {
            homeButton.setImageResource(R.drawable.ic_home_grey)
            favouriteButton.setImageResource(R.drawable.ic_heart_green)
            publishButton.setImageResource(R.drawable.ic_publish_grey)
            accountButton.setImageResource(R.drawable.ic_account_grey)
        }
        R.id.publishButton -> {
            homeButton.setImageResource(R.drawable.ic_home_grey)
            favouriteButton.setImageResource(R.drawable.ic_heart_grey)
            publishButton.setImageResource(R.drawable.ic_publish_green)
            accountButton.setImageResource(R.drawable.ic_account_grey)
        }
        R.id.accountButton -> {
            homeButton.setImageResource(R.drawable.ic_home_grey)
            favouriteButton.setImageResource(R.drawable.ic_heart_grey)
            publishButton.setImageResource(R.drawable.ic_publish_grey)
            accountButton.setImageResource(R.drawable.ic_account_green)
        }
    }

}

fun changeBackground(
    isFirst: Boolean,
    firstTextView: TextView,
    secondTextView: TextView,
    context: Context
) {
    Log.d("EXTENSION", "You are here and bool is $isFirst")
    if (isFirst) {
        firstTextView.setTextColor(ContextCompat.getColor(context, R.color.colorWhite))
        firstTextView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorDefaultGreen))
        secondTextView.setTextColor(ContextCompat.getColor(context, R.color.colorDefaultGreen))
        secondTextView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite))

    } else {
        firstTextView.setTextColor(ContextCompat.getColor(context, R.color.colorDefaultGreen))
        firstTextView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite))
        secondTextView.setTextColor(ContextCompat.getColor(context, R.color.colorWhite))
        secondTextView.setBackgroundColor(
            ContextCompat.getColor(
                context,
                R.color.colorDefaultGreen
            )
        )
    }

}


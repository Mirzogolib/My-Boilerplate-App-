package com.example.myboilerplateapp.model.system

import android.content.Context
import javax.inject.Inject

class AuthPrefs  @Inject constructor(
    private val context: Context
) : AuthHolder {

    companion object {
        private const val PREFS_NAME = "app_prefs"
        private const val TOKEN = "token"
        private const val ID ="id"
        private const val USER_PHONE_OR_EMAIL = "userPhoneOrEmail"
    }

    override var token: String?
        get() = getPrefs().getString(TOKEN, null)
        set(value) {
            getPrefs().edit().putString(TOKEN, value).apply()
        }

    override var id: Int?
        get() = getPrefs().getInt(ID, 0)
        set(value) {
            if (value != null) {
                getPrefs().edit().putInt(ID, value).apply()
            }
        }

    override var userPhoneOrEmail: String?
        get() = getPrefs().getString(USER_PHONE_OR_EMAIL, null)
        set(value) {
            getPrefs().edit().putString(USER_PHONE_OR_EMAIL, value).apply()
        }



    private fun getPrefs() = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    override fun clear() {
        getPrefs().edit().clear().apply()
    }
}
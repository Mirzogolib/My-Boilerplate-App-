package com.example.myboilerplateapp.model.system

interface AuthHolder {
    var token: String?
    var id: Int?
    var userPhoneOrEmail: String?
    fun clear()
}
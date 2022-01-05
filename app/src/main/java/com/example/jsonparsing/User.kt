package com.example.jsonparsing

data class UsersArray(
    val users: ArrayList<User>
)

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val gender: String,
    val weight: Double,
    val height: Int,
    val phone: Phone
    )

data class Phone(
    val mobile: String,
    val office: String,
    val phoneType: PhoneType
)

data class PhoneType (
    val brand: String,
    val model: String
)

package com.shubham.beyondroot

class Contacts {

    private var name: String? = null
    private var email: String? = null
    private var phoneNumber: String? = null
    private var gender: String? = null
    private var city: String? = null

    internal constructor(name: String?, eml: String?, phno: String?, gen: String?, ct: String?) {
        this.name = name
        email = eml
        phoneNumber = phno
        gender = gen
        city = ct
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String) {
        this.email = email
    }

    fun getPhno(): String? {
        return phoneNumber
    }

    fun setPhno(phno: String) {
        this.phoneNumber = phno
    }

    fun getGender(): String? {
        return gender
    }

    fun setGender(gender: String) {
        this.gender = gender
    }

    fun getCity(): String? {
        return city
    }

    fun setCity(city: String) {
        this.city = city
    }
}
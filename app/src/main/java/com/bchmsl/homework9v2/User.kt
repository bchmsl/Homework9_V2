package com.bchmsl.homework9v2

import android.os.Parcel
import android.os.Parcelable

data class User(
    var id: Int,
    var firstName: String,
    var lastName: String
) : Parcelable {
    var email: String = firstName.lowercase() + lastName.lowercase() + "@gmail.com"

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
        email = parcel.readString().toString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeString(email)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }

}

var usersList = mutableListOf(
    User(0, "Nika", "Tabatadze"),
    User(1, "Luka", "Parchukidze"),
    User(2, "Armen", "Garibyan"),
    User(3, "Bachana", "Mosulishvili"),
    User(4, "Lika", "Khokhiashvili"),
    User(5, "Luka", "Janjghava"),
    User(6, "Mariam", "Eristavi"),
    User(7, "Mariam", "Namgaladze")
)

var newUsersList = mutableListOf<User>()
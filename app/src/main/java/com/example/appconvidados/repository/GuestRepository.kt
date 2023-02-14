package com.example.appconvidados.repository

import android.content.Context
import com.example.appconvidados.model.GuestModel

class GuestRepository(context: Context) {

    private val guestDatabase = GuestDataBase.getDataBase(context).getDAO()


    fun insert(guest: GuestModel): Boolean {
       return guestDatabase.insert(guest) > 0
    }

    fun update(guest: GuestModel): Boolean {
       return guestDatabase.update(guest) > 0
    }

    fun delete(guest: GuestModel) {
        guestDatabase.delete(guest)
    }

    fun getGuests(): List<GuestModel> {
        return guestDatabase.getAll()
    }

    fun get(id:Int): GuestModel {
        return guestDatabase.get(id)
    }

    fun getPresence(): List<GuestModel> {
        return guestDatabase.getPresence()
    }

    fun getAbsent(): List<GuestModel> {
        return guestDatabase.getAbsent()
    }

}
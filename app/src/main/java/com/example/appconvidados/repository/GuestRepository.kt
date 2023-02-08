package com.example.appconvidados.repository

import android.content.ContentValues
import android.content.Context
import com.example.appconvidados.constants.DataBaseConstants
import com.example.appconvidados.model.GuestModel
import java.lang.Exception

class GuestRepository private constructor(context: Context) {

    private val guestDatabase = GuestDataBase(context)

    companion object {

        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository {
            if (!::repository.isInitialized) {
                repository = GuestRepository(context)
            }
            return repository
        }
    }

    fun insert(guest: GuestModel): Boolean {
        return try {
            val db = guestDatabase.writableDatabase

            val presence = if (guest.presence) 1 else 0

            val values = ContentValues()
            values.put("name", guest.name)
            values.put("presence", presence)

            db.insert("Guest", null, values)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun update(guest: GuestModel): Boolean {
        try {
            val db = guestDatabase.writableDatabase
            val presence = if (guest.presence) 1 else 0

            val values = ContentValues()
            values.put("name", guest.name)
            values.put("presence", presence)

            val selectection = "id = ?"
            val args = arrayOf(guest.id.toString())

            db.update(DataBaseConstants.GUEST.TABLE_NAME, values, "id = ?", args)
            return true
        } catch (e: Exception) {
            return false
        }
    }

    fun delete(guest: GuestModel): Boolean {
        return try {
            val db = guestDatabase.writableDatabase

            val args = arrayOf(guest.id.toString())

            db.delete(DataBaseConstants.GUEST.TABLE_NAME, "id = ?", arrayOf(guest.id.toString()))
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getGuests(): List<GuestModel> {

        val list = mutableListOf<GuestModel>()
        try{
            val db = guestDatabase.readableDatabase

            val args = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE,
            )

            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME, args,
                null,
                null,
                null,
                null,
                null
            )

            if(cursor != null && cursor.count > 0){
                while(cursor.moveToNext()){

                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))

                    val guest = GuestModel(id,name,presence == 1)
                    list.add(guest)
                }
            }
            cursor.close()
        }catch(e:Exception){
            return list
        }

        return list
    }

}
package com.example.appconvidados.view.listener

import com.example.appconvidados.model.GuestModel

interface OnGuestListener {
    fun onClick(guest:GuestModel)
    fun onDelete(guest:GuestModel)
}
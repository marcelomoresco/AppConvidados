package com.example.appconvidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appconvidados.model.GuestModel
import com.example.appconvidados.repository.GuestRepository

class GuestFormViewModel(application: Application) :AndroidViewModel(application) {

    private val repository = GuestRepository(application)

    private val guestModel = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = guestModel

    private val _saveGuest = MutableLiveData<String>()
    val saveGuest: LiveData<String> = _saveGuest


    fun save(guest:GuestModel){
        if(guest.id==0){
            if(repository.insert(guest)){
                _saveGuest.value = "Convidado criado com sucesso"
            }else{
                _saveGuest.value = "Falha ao criar"
            }

        }else{
            if(repository.update(guest)){
                _saveGuest.value = "Convidado atualizado com sucesso"
            }else{
                _saveGuest.value = "Falha ao atualizar"
            }

        }
    }


    fun get(id:Int){
        guestModel.value= repository.get(id)
    }



}
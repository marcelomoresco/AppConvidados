package com.example.appconvidados.view.viewholder

import android.content.DialogInterface
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.appconvidados.R
import com.example.appconvidados.databinding.RowGuestBinding
import com.example.appconvidados.model.GuestModel
import com.example.appconvidados.view.listener.OnGuestListener

class GuestViewHolder(private val bind: RowGuestBinding, private val listener: OnGuestListener) :
    RecyclerView.ViewHolder(bind.root) {

    fun bind(item: GuestModel) {
        bind.textNameGuest.text = item.name

        bind.textNameGuest.setOnClickListener {
            listener.onClick(item)
        }

        bind.textNameGuest.setOnLongClickListener {

            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de convidado")
                .setMessage("Tem certeza que deseja remover?")
                .setPositiveButton("Sim"
                ) { dialog, which ->
                    listener.onDelete(item)
                }
                .setNegativeButton("Não",null
                )

                .create()
                .show()
            true
        }

    }
}
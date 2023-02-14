package com.example.appconvidados.view.all_guests

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appconvidados.databinding.FragmentAllGuestsBinding
import com.example.appconvidados.model.GuestModel
import com.example.appconvidados.view.GuestFormActivity
import com.example.appconvidados.view.adapter.GuestsAdapter
import com.example.appconvidados.view.listener.OnGuestListener
import com.example.appconvidados.viewmodel.AllGuestsViewModel

class AllGuestsFragment : Fragment() {

    private var _binding: FragmentAllGuestsBinding? = null
    private val binding get() = _binding!!
    private val adapter = GuestsAdapter()
    private lateinit var viewModel: AllGuestsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        b: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this).get(AllGuestsViewModel::class.java)
        _binding = FragmentAllGuestsBinding.inflate(inflater, container, false)

        //Layout
        binding.recyclerAllGuests.layoutManager = LinearLayoutManager(context)

        //Adapter
        binding.recyclerAllGuests.adapter = adapter

        val listener = object : OnGuestListener{
            override fun onClick(guest: GuestModel) {
                val intent =Intent(context,GuestFormActivity::class.java)

                val bundle = Bundle()
                bundle.putInt("guestid",id)

                intent.putExtras(bundle)

                startActivity(intent)
            }

            override fun onDelete(guest:GuestModel) {
                viewModel.delete(guest)
                viewModel.getAll()
            }

        }

        adapter.attachListener(listener)



        observe()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAll()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe() {
        viewModel.guests.observe(viewLifecycleOwner) {
            adapter.updateGuests(it)
        }
    }
}
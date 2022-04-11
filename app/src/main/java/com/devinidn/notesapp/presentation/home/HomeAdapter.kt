package com.devinidn.notesapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.devinidn.notesapp.data.local.entity.Notes
import com.devinidn.notesapp.databinding.RowItemNotesBinding

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {

    var listNotes = ArrayList<Notes>()

    inner class MyViewHolder(val binding: RowItemNotesBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder (
        RowItemNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = listNotes.get(position)
        holder.binding.apply {
           mNotes = data
            executePendingBindings()

//            rowBackground.setOnClickListener {
//                HomeFragmentDirections.actionHomeFragmentToDetailFragment(data)
//            }
        }
    }

    override fun getItemCount(): Int = listNotes.size

    fun setData(notesData: List<Notes>?) {
        if (notesData == null) return
        val diffCallback = DiffCallback(listNotes, notesData)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        listNotes.clear()
        listNotes.addAll(notesData)
        diffResult.dispatchUpdatesTo(this)
    }
}
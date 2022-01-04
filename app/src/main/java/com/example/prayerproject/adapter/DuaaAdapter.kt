package com.example.prayerproject.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.prayerproject.databinding.DuaaItemLayoutBinding
import com.example.prayerproject.model.DuaaModel
import com.example.prayerproject.view.DuaaViewModel
import com.example.prayerproject.view.MyFavoriteDuaaViewModel

const val TAG = "DuaaAdapter"

class DuaaAdapter(
    val athkarViewModel: DuaaViewModel,
    val myFavoriteDuaaViewModel: MyFavoriteDuaaViewModel,
    val context: Context
) : RecyclerView.Adapter<DuaaAdapter.AthkarViewHolder>() {


    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DuaaModel>() {
        override fun areItemsTheSame(oldItem: DuaaModel, newItem: DuaaModel): Boolean {
            // we should use id but it could be any thing unique like username
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DuaaModel, newItem: DuaaModel): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    fun submitList(list: List<DuaaModel>) {
        differ.submitList(list)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AthkarViewHolder {

        val binding =
            DuaaItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AthkarViewHolder(binding, athkarViewModel)
    }


    override fun onBindViewHolder(holder: AthkarViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)
        holder.binding.addImagebutton.setOnClickListener {
            Toast.makeText(context, "Dua'a has been added to your list", Toast.LENGTH_SHORT).show()

            Log.d(TAG, "inside the add")
            athkarViewModel.addAthkar(item)

        }


    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class AthkarViewHolder(
        val binding: DuaaItemLayoutBinding,
        val athkarViewModel: DuaaViewModel,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(athkarModel: DuaaModel) {

            binding.athkarTextview.text = athkarModel.duaa
            binding.titleTextview.text = athkarModel.title
        }

    }
}


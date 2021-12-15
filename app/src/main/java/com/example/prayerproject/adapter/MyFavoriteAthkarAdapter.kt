package com.example.prayerproject.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.prayerproject.databinding.MyathkarItemLayoutBinding
import com.example.prayerproject.model.AthkarModel
import com.example.prayerproject.view.AthkarViewModel
import com.example.prayerproject.view.MyFavoriteAthkarViewModel


class MyFavoriteAthkarAdapter(val myFavoriteAthkarViewModel: MyFavoriteAthkarViewModel):RecyclerView.Adapter<MyFavoriteAthkarAdapter.MyFavoriteAthkarViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AthkarModel>(){

        override fun areItemsTheSame(oldItem: AthkarModel, newItem: AthkarModel): Boolean {
            // we should use id but it could be any thing unique like username
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AthkarModel, newItem: AthkarModel): Boolean {
            return oldItem == newItem        }
    }

    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    fun submitList(list: List<AthkarModel>){
        differ.submitList(list)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyFavoriteAthkarViewHolder {

        val binding = MyathkarItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyFavoriteAthkarViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MyFavoriteAthkarViewHolder, position: Int) {
        val item = differ.currentList[position]
   holder.binding.deleteImageButton.setOnClickListener {
    val myAthkar = mutableListOf<AthkarModel>()
    myAthkar.addAll(differ.currentList)
    myAthkar.remove(item)
    myFavoriteAthkarViewModel.deleteAthkar(item)
    differ.submitList(myAthkar)
}
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class MyFavoriteAthkarViewHolder(val binding: MyathkarItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(athkarModel: AthkarModel){

            binding.athkarTextview.text = athkarModel.athkar
            binding.titleTextview.text = athkarModel.title
            val deleteImageButton = binding.deleteImageButton

        }


    }
}
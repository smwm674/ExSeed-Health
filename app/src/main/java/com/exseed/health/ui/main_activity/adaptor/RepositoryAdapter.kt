package com.exseed.health.ui.main_activity.adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exseed.health.databinding.ItemLayoutBinding
import com.exseed.health.ui.main_activity.model.Items
import com.exseed.health.utils.Utils.setText
import com.exseed.health.utils.setImage

class RepositoryAdapter(
    private var modelList: ArrayList<Items>,
    private var context: Context,
    private var listener: repositoryListAction
) : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(modelList.get(position))
    }

    override fun getItemCount() = modelList.size

    interface repositoryListAction {
        fun onClick(data: Items)
    }

    inner class ViewHolder(val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Items) {
            binding.apply {
                setText(title, data.full_name)
                setText(description,data.description)
                context.setImage(icon, data?.owner.avatar_url)
                repositoryItem.setOnClickListener() {
                    listener.onClick(data)
                }
            }
        }
    }

    fun updateList(list: ArrayList<Items>) {
        modelList = list
        notifyDataSetChanged()
    }
}

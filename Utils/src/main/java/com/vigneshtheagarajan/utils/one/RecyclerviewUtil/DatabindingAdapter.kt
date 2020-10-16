package com.vigneshtheagarajan.utils.one.RecyclerviewUtil

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class DatabindingAdapter<T : BaseModel> :
    RecyclerView.Adapter<DatabindingAdapter.GenericVH<T>>() {

    private var items = mutableListOf<T>()
    private var layoutId: Int = 0
    private var itemViewModelId = 0


    fun setLayout(@LayoutRes layoutId: Int): DatabindingAdapter<T> {
        this.layoutId = layoutId
        return this
    }

    fun setViewModelId(itemViewModelId: Int): DatabindingAdapter<T> {
        this.itemViewModelId = itemViewModelId
        return this
    }

    fun setItems(items: ArrayList<T>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun updateItems(items: ArrayList<T>) {
        this.items = items
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: GenericVH<T>, position: Int) {
        val itemViewModel = items[position]
        holder.bind(itemViewModel, itemViewModelId)
    }


    class GenericVH<T>(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(itemViewModel: BaseModel, itemViewModelId: Int) {
            binding.setVariable(itemViewModelId, itemViewModel)
            binding.executePendingBindings()
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericVH<T> {
        if (layoutId == 0 || itemViewModelId == 0)
            throw Exception("setLayout(R.layout.name) && setViewModelId(BR.itemViewModel)")
        return GenericVH(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                layoutId,
                parent,
                false
            )
        )
    }
}



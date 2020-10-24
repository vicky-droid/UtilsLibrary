package com.vigneshtheagarajan.utils.one.RecyclerviewUtil

import androidx.recyclerview.widget.DiffUtil

class NewDiffUtil<T>(
    private val oldProductList: List<T>, private val newProductList: List<T>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldProductList[oldItemPosition] == newProductList[newItemPosition]
    }

    override fun getOldListSize(): Int = oldProductList.size

    override fun getNewListSize(): Int = newProductList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldProductList[oldItemPosition] == newProductList[newItemPosition]
    }

}
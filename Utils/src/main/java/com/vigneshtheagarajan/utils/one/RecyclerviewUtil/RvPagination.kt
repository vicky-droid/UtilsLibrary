package com.vigneshtheagarajan.utils.one.RecyclerviewUtil

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.paginationListener(listener: (loadMore: Boolean) -> Unit) {
    var layoutManager = this.layoutManager as LinearLayoutManager
    lateinit var scrollListener: RecyclerView.OnScrollListener
    scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            val totalItemCount = recyclerView.layoutManager!!.itemCount
            val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
            if (totalItemCount == lastVisibleItemPosition + 1) {
                listener.invoke(true)
            }
        }
    }
    this.addOnScrollListener(scrollListener)

}
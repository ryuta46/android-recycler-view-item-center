package com.ttechsoft.recyclerviewitemcenter

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = MyAdapter(this, 5)

        // To make item center
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)

        // Force to center even if the item is first or last.
        recyclerView.addItemDecoration(object: RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                val edgeMargin = (parent.width - view.layoutParams.width) / 2

                val position = parent.getChildAdapterPosition(view)
                if (position == 0) {
                    outRect.left = edgeMargin
                }
                if (position == state.itemCount - 1) {
                    outRect.right = edgeMargin
                }
            }
        })


    }

    class MyAdapter(private val context: Context, private val itemCount: Int) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

        override fun getItemCount(): Int {
            return itemCount
        }

        override fun onBindViewHolder(vh: ViewHolder, position: Int) {
            val text = "Page $position"
            vh.pageNumberText.text = text
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ViewHolder {
            val layoutInflater = LayoutInflater.from(context)
            val v = layoutInflater.inflate(R.layout.item_simple_card, parent, false)
            return ViewHolder(v)
        }

        class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
            val pageNumberText: TextView = v.findViewById(R.id.textPageNumber)
        }
    }
}

package com.ilkeruzer.motionApp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ilkeruzer.motionApp.data.local.model.MotionEntity
import com.ilkeruzer.motionApp.databinding.ItemMotionLayoutBinding
import javax.inject.Inject

class MotionAdapter @Inject constructor():
    PagingDataAdapter<MotionEntity, MotionAdapter.MotionViewHolder>(MotionComparator) {

    var motionClickListener: MotionClickListener? = null

    override fun onBindViewHolder(holder: MotionAdapter.MotionViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MotionAdapter.MotionViewHolder  =
        MotionViewHolder(
            ItemMotionLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    inner class MotionViewHolder(private val binding: ItemMotionLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                motionClickListener?.onMotionClicked(
                    binding,
                    getItem(absoluteAdapterPosition) as MotionEntity
                )
            }
        }

        fun bind(item: MotionEntity) = with(binding) {
            motionTextview.text = item.uid.toString()
        }
    }

    object MotionComparator : DiffUtil.ItemCallback<MotionEntity>() {
        override fun areItemsTheSame(oldItem: MotionEntity, newItem: MotionEntity) =
            oldItem.uid == newItem.uid

        override fun areContentsTheSame(oldItem: MotionEntity, newItem: MotionEntity) =
            oldItem == newItem
    }

    interface MotionClickListener {
        fun onMotionClicked(binding: ItemMotionLayoutBinding, motion: MotionEntity)
    }
}
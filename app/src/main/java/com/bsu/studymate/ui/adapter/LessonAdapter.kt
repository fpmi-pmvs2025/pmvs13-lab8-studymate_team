package com.bsu.studymate.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bsu.studymate.data.Lesson
import com.bsu.studymate.databinding.ItemLessonBinding

class LessonAdapter :
    ListAdapter<Lesson, LessonAdapter.Holder>(Diff) {

    inner class Holder(private val b: ItemLessonBinding)
        : RecyclerView.ViewHolder(b.root) {
        fun bind(item: Lesson) = with(b) {
            tvTime.text    = item.time
            tvSubject.text = item.subject
            tvRoom.text    = "ауд. ${item.room}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder(ItemLessonBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: Holder, position: Int) =
        holder.bind(getItem(position))

    private object Diff : DiffUtil.ItemCallback<Lesson>() {
        override fun areItemsTheSame(o: Lesson, n: Lesson) = o.id == n.id
        override fun areContentsTheSame(o: Lesson, n: Lesson) = o == n
    }
}
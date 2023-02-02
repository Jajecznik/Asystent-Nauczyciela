package com.example.asystent.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.asystent.databinding.NewStudentSubjectItemBinding
import com.example.asystent.model.SubjectModelCheck

class StudentSubjectAdapter: RecyclerView.Adapter<StudentSubjectAdapter.ViewHolder>() {

    private lateinit var binding: NewStudentSubjectItemBinding
    private var items : MutableList<SubjectModelCheck> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = NewStudentSubjectItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: NewStudentSubjectItemBinding) : RecyclerView.ViewHolder(itemView.root) {
        fun bind(item: SubjectModelCheck) {
            binding.apply {
                nameSubjectItemTV.text = item.name
                daySubjectItemTV.text = item.day
                hoursSubjectItemTV.text = item.hours
                checkBoxSubjectItem.isChecked = item.isSelected
            }
        }
        init {
            binding.checkBoxSubjectItem.setOnClickListener {v ->
                val isChecked = (v as CheckBox).isChecked
                items[position].isSelected = isChecked
                notifyDataSetChanged()

                for (i in items.indices) {
                    Log.d("TAG", items.toString())
                }
            }
        }
    }

    fun submitData(data: MutableList<SubjectModelCheck>) {
        items = data
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}
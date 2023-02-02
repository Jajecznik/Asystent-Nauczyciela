package com.example.asystent.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.asystent.StudentsList
import com.example.asystent.data.entity.Subject
import com.example.asystent.databinding.SubjectItemBinding


class SubjectA(key: String?): RecyclerView.Adapter<SubjectA.ViewHolder>() {

    private lateinit var binding: SubjectItemBinding
    private lateinit var context: Context
    var k = key

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = SubjectItemBinding.inflate(inflater, parent, false)
        context = parent.context
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ViewHolder: RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Subject) {
            binding.apply {
                nameSubjectItemTV.text = item.name
                daySubjectItemTV.text = item.day
                hoursSubjectItemTV.text = item.hours

                root.setOnClickListener {
                    val intent = Intent(context, StudentsList::class.java)
                    intent.putExtra("bundle_subject_id", item.id.toString())
                    intent.putExtra("key", k.toString())
                    intent.putExtra("subject", item.name)
                    context.startActivity(intent)
                }
            }
        }
    }

    private val differCallback = object: DiffUtil.ItemCallback<Subject>() {
        override fun areItemsTheSame(oldItem: Subject, newItem: Subject): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Subject, newItem: Subject): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}
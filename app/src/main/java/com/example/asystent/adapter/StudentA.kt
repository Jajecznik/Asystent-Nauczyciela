package com.example.asystent.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.asystent.NewMark
import com.example.asystent.StudentMarks
import com.example.asystent.data.entity.Student
import com.example.asystent.databinding.StudentItemBinding

class StudentA(key: String?, subj: String?): RecyclerView.Adapter<StudentA.ViewHolder>() {

    private lateinit var binding: StudentItemBinding
    private lateinit var context: Context
    var k = key
    var sub = subj

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = StudentItemBinding.inflate(inflater, parent, false)
        context = parent.context
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ViewHolder: RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(item: Student) {
            binding.apply {
                nameStudentItemTV.text = item.name
                surnameStudentItemTV.text = item.surname
                numberStudentItemTV.text = item.number

                root.setOnClickListener {
                    if (k.equals("marks")) {
                        val intent = Intent(context, NewMark::class.java)
                        intent.putExtra("bundle_student_id", item.number)
                        intent.putExtra("subject", sub.toString())
                        context.startActivity(intent)
                    }
                    else if (k.equals("students")) {
                        val intent = Intent(context, StudentMarks::class.java)
                        intent.putExtra("bundle_student_id", item.number)
                        intent.putExtra("subject", sub.toString())
                        context.startActivity(intent)
                    }
                }
            }
        }
    }

    private val differCallback = object: DiffUtil.ItemCallback<Student>() {
        override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
            return oldItem.number == newItem.number
        }

        override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}
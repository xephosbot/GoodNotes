package com.xbot.goodnotes.ui.features.notes

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xbot.goodnotes.databinding.ItemNoteCardBinding
import com.xbot.goodnotes.domain.models.Note
import com.xbot.goodnotes.ui.utils.setTransitionNameCompat
import com.xbot.goodnotes.ui.utils.viewBinding

class NotesAdapter(
    private val onClick: (Note, View) -> Unit
) : ListAdapter<Note, NotesAdapter.NoteViewHolder>(DiffCallback) {

    class NoteViewHolder(
        private val binding: ItemNoteCardBinding,
        private val onClick: (Note, View) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private var currentNote: Note? = null

        init {
            binding.root.setOnClickListener { view ->
                currentNote?.let { note ->
                    onClick(note, view)
                }
            }
        }

        fun bind(note: Note) {
            currentNote = note
            binding.title.text = note.title
            binding.text.text = note.text
            binding.root.setCardBackgroundColor(note.color)
            binding.root.setTransitionNameCompat(note.id.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = viewBinding(parent, ItemNoteCardBinding::inflate)
        return NoteViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val file = getItem(position)
        holder.bind(file)
    }

    object DiffCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }
}
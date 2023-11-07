package com.xbot.goodnotes.ui.features.detail

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.transition.MaterialContainerTransform
import com.xbot.goodnotes.R
import com.xbot.goodnotes.databinding.FragmentNoteDetailBinding
import com.xbot.goodnotes.ui.utils.setIsAppearanceLightSystemBars
import com.xbot.goodnotes.ui.utils.setTransitionNameCompat
import com.xbot.goodnotes.ui.utils.viewBinding
import kotlinx.coroutines.launch


class NoteDetailFragment : Fragment(R.layout.fragment_note_detail) {

    private val binding: FragmentNoteDetailBinding by viewBinding(FragmentNoteDetailBinding::bind)
    private val viewModel: NoteDetailViewModel by hiltNavGraphViewModels(R.id.nav_graph)
    private val args: NoteDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.root.setTransitionNameCompat(args.sharedElementName)
        binding.toolbar.setNavigationOnClickListener {
            viewModel.onEvent(NoteDetailEvent.Save)
            findNavController().popBackStack()
        }
        binding.editableTitle.doAfterTextChanged { text ->
            viewModel.onEvent(NoteDetailEvent.SaveTitle(text?.toString() ?: ""))
        }
        binding.editableText.doAfterTextChanged { text ->
            viewModel.onEvent(NoteDetailEvent.SaveText(text?.toString() ?: ""))
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.onEvent(NoteDetailEvent.OpenNote(args.noteId))
                viewModel.state.collect { state ->
                    binding.editableTitle.setText(state.noteTitle)
                    binding.editableText.setText(state.noteText)
                    binding.root.setBackgroundColor(state.noteColor)
                }
            }
        }

        setIsAppearanceLightSystemBars(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        setIsAppearanceLightSystemBars()
    }
}
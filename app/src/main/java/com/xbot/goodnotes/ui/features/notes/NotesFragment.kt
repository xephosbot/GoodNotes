package com.xbot.goodnotes.ui.features.notes

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy
import com.google.android.material.transition.Hold
import com.xbot.goodnotes.R
import com.xbot.goodnotes.databinding.FragmentNotesBinding
import com.xbot.goodnotes.ui.utils.GridAutofitLayoutManager
import com.xbot.goodnotes.ui.utils.SpacesItemDecoration
import com.xbot.goodnotes.ui.utils.fitSystemBars
import com.xbot.goodnotes.ui.utils.px
import com.xbot.goodnotes.ui.utils.startPostponedTransitionAfterPreDraw
import com.xbot.goodnotes.ui.utils.viewBinding
import kotlinx.coroutines.launch


class NotesFragment : Fragment(R.layout.fragment_notes) {

    private val binding: FragmentNotesBinding by viewBinding(FragmentNotesBinding::bind)
    private val viewModel: NotesViewModel by hiltNavGraphViewModels(R.id.nav_graph)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exitTransition = Hold()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = NotesAdapter { note, transitionView ->
            navigateToDetail(note.id, transitionView)
        }
        adapter.stateRestorationPolicy = StateRestorationPolicy.PREVENT_WHEN_EMPTY
        binding.recycler.adapter = adapter

        binding.recycler.layoutManager = GridAutofitLayoutManager(context, DEFAULT_NOTE_CARD_WIDTH)
        binding.recycler.addItemDecoration(SpacesItemDecoration(DEFAULT_NOTE_CARD_SPACING))
        binding.recycler.fitSystemBars(Gravity.BOTTOM)

        binding.fab.setOnClickListener {
            navigateToDetail(-1, it)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                adapter.submitList(state.notes)
            }
        }

        //Fix return transition MaterialContainerTransform
        startPostponedTransitionAfterPreDraw(view)
    }

    private fun navigateToDetail(noteId: Long, transitionView: View) {
        val action = NotesFragmentDirections.actionNotesFragmentToNoteDetailFragment(noteId, transitionView.transitionName)
        val extras = FragmentNavigatorExtras(transitionView to transitionView.transitionName)
        findNavController().navigate(action, extras)
    }

    companion object {
        private val DEFAULT_NOTE_CARD_WIDTH = 180.px
        private val DEFAULT_NOTE_CARD_SPACING = 4.px
    }
}
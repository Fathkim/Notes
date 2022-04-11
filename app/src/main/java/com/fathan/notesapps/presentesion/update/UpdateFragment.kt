package com.fathan.notesapps.presentesion.update

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.fathan.notesapps.R
import com.fathan.notesapps.data.local.entity.Notes
import com.fathan.notesapps.databinding.FragmentUpdateBinding
import com.fathan.notesapps.presentesion.NotesViewModel
import com.fathan.notesapps.utils.ExtensionFunction.setActionBar
import com.fathan.notesapps.utils.HelperFunction.parseToPriority
import com.fathan.notesapps.utils.HelperFunction.setPriorityColor
import java.text.SimpleDateFormat
import java.util.*

class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding as FragmentUpdateBinding
    private val args by navArgs<UpdateFragmentArgs>()
    private val updateViewModel by viewModels<NotesViewModel    >()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        binding.updateArgs = args

        binding.apply {
            toolbarUpdate.setActionBar(requireActivity())
            spinnerPrioritiesUpdate.onItemSelectedListener =
                context?.let { setPriorityColor(it, priorityIndicator) }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_save, menu)
        val item = menu.findItem(R.id.menu_save)
        item.actionView.findViewById<AppCompatImageButton>(R.id.btn_save).setOnClickListener{
            updateNote()
        }
    }

    private fun updateNote() {
        binding.apply {
            val title = edtTitleUpdate.text.toString()
            val desc = edtDescriptionUpdate.text.toString()
            val priority = spinnerPrioritiesUpdate.selectedItem.toString()

            val date = Calendar.getInstance().time
            val formattedDate = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()).format(date)
            val currentData = Notes(
                args.notes.id, title, desc, formattedDate, parseToPriority(
                    context, priority
                )
            )
            updateViewModel.updateData(currentData)
            val action = UpdateFragmentDirections.actionUpdateFragmentToDetailFragment3(currentData)
            findNavController().navigate(action)
        }

        Toast.makeText(context, "Note has been update", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}



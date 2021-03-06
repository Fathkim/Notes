package com.fathan.notesapps.utils

import android.view.View
import androidx.appcompat.widget.AppCompatSpinner
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.fathan.notesapps.R
import com.fathan.notesapps.data.local.entity.Notes
import com.fathan.notesapps.data.local.entity.Priority
import com.fathan.notesapps.presentesion.home.HomeFragmentDirections
import com.google.android.material.card.MaterialCardView

object BindingAdapeters {

        @BindingAdapter("android:parsePriorityColor")
        @JvmStatic
        fun parsePriorityColor(cardView: MaterialCardView, priority: Priority) {
            when (priority) {
                Priority.HIGH -> cardView.setCardBackgroundColor(cardView.context.getColor(R.color.pink))
                Priority.MEDIUM -> cardView.setCardBackgroundColor(cardView.context.getColor(R.color.yellow))
                Priority.LOW -> cardView.setCardBackgroundColor(cardView.context.getColor(R.color.green))
            }
        }

    @BindingAdapter("android:sendDataToDetail")
    @JvmStatic
    fun sendDataToDetail (view: ConstraintLayout, currentTime: Notes) {
        view.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(currentTime)
            view.findNavController().navigate(action)
        }

    }

    @BindingAdapter("android:parsePriorityToInt")
    @JvmStatic
    fun parsePriorityToInt(view: AppCompatSpinner, priority: Priority) {
        when (priority) {
            Priority.HIGH -> {
                view.setSelection(0)
            }
            Priority.MEDIUM -> {
                view.setSelection(1)
            }
            Priority.LOW -> {
                view.setSelection(2)
            }
        }
    }

    @BindingAdapter("android:emptyDataBase")
    @JvmStatic
    fun emptyDataBase(view: View, emptyDataBase: MutableLiveData<Boolean>) {
        when (emptyDataBase.value) {
            true -> view.visibility = View.VISIBLE
            else -> view.visibility = View.INVISIBLE
        }
    }
}
package com.genarosoft.dms.ui.preferences

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.genarosoft.dms.databinding.ItemPreferenceBinding
import com.google.android.material.checkbox.MaterialCheckBox.STATE_UNCHECKED

// todo: change to object Preference with all info
class PreferenceAdapter(private val preferences: List<String>) :
    RecyclerView.Adapter<PreferenceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPreferenceBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(preferences[position])
    }

    override fun getItemCount(): Int {
        return preferences.size
    }

    class ViewHolder(private val binding: ItemPreferenceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(preference: String) {
            binding.preferenceTitle.text = preference
            // todo: borrar
            if (preference.length % 5 == 0) binding.preferenceValue.checkedState = STATE_UNCHECKED

            binding.root.setOnLongClickListener {
                val context = it.context
                val clipboardManager =
                    context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                // todo: copy all info
                val clip: ClipData = ClipData.newPlainText("Company key", preference)
                clipboardManager.setPrimaryClip(clip)
                // todo: translate
                Toast.makeText(context, "Copied '$preference' key to clipboard", Toast.LENGTH_SHORT)
                    .show()
                true
            }
        }
    }
}
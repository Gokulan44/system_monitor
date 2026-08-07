package com.soc.agent.ui.applock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.soc.agent.R
import com.soc.agent.databinding.FragmentUnlockHistoryBinding
import com.soc.agent.database.AppDatabase
import com.soc.agent.database.entity.UnlockHistoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UnlockHistoryFragment : Fragment() {

    private var _binding: FragmentUnlockHistoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: UnlockHistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUnlockHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = UnlockHistoryAdapter()
        binding.rvHistory.layoutManager = LinearLayoutManager(requireContext())
        binding.rvHistory.adapter = adapter
        loadHistory()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun loadHistory() {
        viewLifecycleOwner.lifecycleScope.launch {
            val history = withContext(Dispatchers.IO) {
                AppDatabase.getInstance(requireContext()).unlockHistoryDao().getRecentHistory(100)
            }
            if (_binding != null) {
                adapter.submitList(history)
                binding.tvEmpty.isVisible = history.isEmpty()
            }
        }
    }

    private class UnlockHistoryAdapter : ListAdapter<UnlockHistoryEntity, UnlockHistoryAdapter.VH>(DIFF) {

        companion object {
            private val DIFF = object : DiffUtil.ItemCallback<UnlockHistoryEntity>() {
                override fun areItemsTheSame(oldItem: UnlockHistoryEntity, newItem: UnlockHistoryEntity): Boolean =
                    oldItem.id == newItem.id

                override fun areContentsTheSame(oldItem: UnlockHistoryEntity, newItem: UnlockHistoryEntity): Boolean =
                    oldItem == newItem
            }
        }

        inner class VH(view: View) : RecyclerView.ViewHolder(view) {
            val tvAppName: TextView = view.findViewById(R.id.tv_app_name)
            val tvGateMethod: TextView = view.findViewById(R.id.tv_gate_method)
            val tvTimestamp: TextView = view.findViewById(R.id.tv_timestamp)
            val tvAuto: TextView = view.findViewById(R.id.tv_auto)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_unlock_history, parent, false)
            return VH(view)
        }

        override fun onBindViewHolder(holder: VH, position: Int) {
            val item = getItem(position)
            holder.tvAppName.text = item.appName
            holder.tvGateMethod.text = item.gateMethod.uppercase()
            holder.tvTimestamp.text = android.text.format.DateUtils.formatDateTime(
                holder.itemView.context,
                item.timestamp,
                android.text.format.DateUtils.FORMAT_SHOW_DATE or
                    android.text.format.DateUtils.FORMAT_SHOW_TIME or
                    android.text.format.DateUtils.FORMAT_ABBREV_ALL
            )
            holder.tvAuto.isVisible = item.autoUnlock
        }
    }
}
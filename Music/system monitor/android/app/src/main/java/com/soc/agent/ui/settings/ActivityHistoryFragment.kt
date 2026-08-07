package com.soc.agent.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.soc.agent.R

/**
 * ActivityHistoryFragment — Displays history logs and events.
 */
class ActivityHistoryFragment : Fragment() {

    private var toolbar: MaterialToolbar? = null
    private var recyclerView: RecyclerView? = null
    private var emptyLayout: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_activity_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
        setupToolbar()
        setupRecyclerView()
    }

    private fun bindViews(view: View) {
        toolbar = view.findViewById(R.id.toolbar_activity_history)
        recyclerView = view.findViewById(R.id.rv_activity_history)
        emptyLayout = view.findViewById(R.id.layout_empty_history)
    }

    private fun setupToolbar() {
        toolbar?.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun setupRecyclerView() {
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
    }
}

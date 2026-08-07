package com.soc.agent.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.soc.agent.R

/**
 * Reports — placeholder fragment.
 */
class ReportsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_placeholder, container, false).also {
            it.findViewById<TextView>(R.id.tvPlaceholder).text = "Reports"
        }
    }
}
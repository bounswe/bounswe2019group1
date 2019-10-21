package com.project.khajit_app.activity.ui.mailbox

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.project.khajit_app.R

class MailboxFragment : Fragment() {

    private lateinit var mailboxViewModel: MailboxViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        mailboxViewModel =
            ViewModelProviders.of(this).get(MailboxViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_mailbox, container, false)
        var loader = root.findViewById(R.id.progress_loader) as ProgressBar
        loader.visibility = View.GONE
        return root
    }
}
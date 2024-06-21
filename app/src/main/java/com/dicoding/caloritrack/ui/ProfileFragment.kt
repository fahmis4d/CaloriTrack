package com.dicoding.caloritrack.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.dicoding.caloritrack.R
import com.dicoding.caloritrack.databinding.FragmentNewsBinding
import com.dicoding.caloritrack.databinding.FragmentProfileBinding
import com.dicoding.caloritrack.ui.viewmodel.NewsViewModel
import com.dicoding.caloritrack.ui.viewmodel.ProfileViewModel
import com.google.firebase.auth.FirebaseAuth


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        auth = FirebaseAuth.getInstance()

        val iconButton = binding.iconButton
        iconButton.setOnClickListener {
            // Log out the user
            auth.signOut()

            // Move to LoginActivity
            val intent = Intent(activity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            activity?.finish()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.dicoding.caloritrack.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.caloritrack.R
import com.dicoding.caloritrack.adapter.ListNewsAdapter
import com.dicoding.caloritrack.databinding.FragmentHomeBinding
import com.dicoding.caloritrack.databinding.FragmentNewsBinding
import com.dicoding.caloritrack.ui.viewmodel.HomeViewModel
import com.dicoding.caloritrack.ui.viewmodel.NewsViewModel

class NewsFragment : Fragment() {
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newsAdapter: ListNewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        newsAdapter = ListNewsAdapter()

        binding.rvNewsHeadline.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
        }

        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        newsViewModel.newsList.observe(viewLifecycleOwner, Observer {
            newsAdapter.listNews = it
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
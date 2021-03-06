package com.example.hiltretrofitmvvm.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hiltretrofitmvvm.databinding.ActivityMainBinding
import com.example.hiltretrofitmvvm.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        postAdapter = PostAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = postAdapter
        }
        binding.retry.setOnClickListener {
            mainViewModel.getPosts()
        }

        mainViewModel.res.observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progress.visibility = View.GONE
                    binding.errorMessage.visibility = View.GONE
                    binding.retry.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                    if (!it.data?.body().isNullOrEmpty()) {
                        it.data?.body()?.let { it1 ->
                            postAdapter.submitList(it1)
                        }
                    }
                }
                Status.LOADING -> {
                    binding.progress.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                    binding.errorMessage.visibility = View.GONE
                    binding.retry.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding.errorMessage.visibility = View.VISIBLE
                    binding.progress.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE
                    binding.retry.visibility = View.VISIBLE
                    binding.errorMessage.text = if (it.message.isNullOrEmpty()) {
                        "Unable to connect to server. Please check your internet connection and try again"
                    } else {
                        it.message
                    }
                }
            }
        })
    }

}
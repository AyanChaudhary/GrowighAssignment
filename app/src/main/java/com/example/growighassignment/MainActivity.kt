package com.example.growighassignment

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.growighassignment.databinding.ActivityMainBinding
import com.example.growighassignment.repository.ItemRepository
import com.example.growighassignment.ui.ItemViewModel
import com.example.growighassignment.ui.ItemViewModelProviderFactory


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel : ItemViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
        val navHostFragment=supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController=navHostFragment.navController

        val repository = ItemRepository()
        val viewModelProviderFactory=ItemViewModelProviderFactory(repository)
        viewModel=ViewModelProvider(this,viewModelProviderFactory).get(ItemViewModel::class.java)

    }
}
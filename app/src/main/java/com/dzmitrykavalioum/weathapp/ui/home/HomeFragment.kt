package com.dzmitrykavalioum.weathapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dzmitrykavalioum.weathapp.R
import com.dzmitrykavalioum.weathapp.repository.Repository
import com.dzmitrykavalioum.weathapp.utils.Constants.Companion.APP_ID

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
    ): View? {
//        homeViewModel =
//            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
//        val textView: TextView = root.findViewById(R.id.text_home)
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//          textView.text = it
//        })
      val repository = Repository()
      val viewModelFactory = HomeViewModelFactory(repository)
      homeViewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
      homeViewModel.getTodayWeatherByCity("Minsk",APP_ID)
      homeViewModel.myResponce.observe(viewLifecycleOwner, Observer { responce ->
        Log.d("Responce", responce.main.temp.toString())
        Log.d("Responce", responce.main.humidity.toString())
        Log.d("Responce", responce.main.temp.toString())

      })
        return root
    }
}
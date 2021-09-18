package com.example.apprendevcursoll.spinnersample.ui.spinner

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.apprendevcursoll.R
import com.example.apprendevcursoll.databinding.SpinnerFragmentBinding

class SpinnerFragment : Fragment(), AdapterView.OnItemSelectedListener {


    private lateinit var binding:SpinnerFragmentBinding
    private lateinit var viewModel: SpinnerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = with(SpinnerFragmentBinding.inflate(inflater,container,false)){
       binding = this
        binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpComponents()
    }
    fun setUpComponents(){
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.soccer_players,
            android.R.layout.simple_spinner_item
        ).also { playerSoccerAdapter ->
            playerSoccerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerPlayers.adapter = playerSoccerAdapter
            binding.spinnerPlayers.onItemSelectedListener = this
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SpinnerViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        Log.d(
            SpinnerFragment::class.java.simpleName,
            "onItemSelected() called with: p0 = $p0, p1 = $p1, p2 = $p2, p3 = $p3")
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        Log.d(
            SpinnerFragment::class.java.simpleName,
            "onNothingSelected() called with: p0 = $p0")
    }


}
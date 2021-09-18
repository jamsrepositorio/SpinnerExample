package com.example.apprendevcursoll.tablayoutsample.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.apprendevcursoll.databinding.FragmentPlayersBinding
import com.example.apprendevcursoll.tablayoutsample.model.TeamSoccer
import com.example.apprendevcursoll.tablayoutsample.ui.main.adapters.SoccerAdapter


class PlaceholderFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private lateinit var binding: FragmentPlayersBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = with(FragmentPlayersBinding::class.simpleName){

        binding = FragmentPlayersBinding.inflate(inflater, container, false)
        binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpComponent()
    }

    private fun setUpComponent() {
        val teamSoccers: List<TeamSoccer> = listOf(
            TeamSoccer("PSG", "https://www.google.com.mx/url?sa=i&url=https%3A%2F%2Fwww.prensa-latina.cu%2Findex.php%2Fcomponent%2Fcontent%2F%3Fo%3Drn%26id%3D444684%26SEO%3Dpsg-intenta-en-metz-presionar-a-lider-del-futbol-frances&psig=AOvVaw0onj-X210PgNxc_cTKlFOl&ust=1632006555645000&source=images&cd=vfe&ved=0CAkQjRxqFwoTCNC0sa6Qh_MCFQAAAAAdAAAAABAD"),
            TeamSoccer("ManCity","https://www.google.com.mx/url?sa=i&url=https%3A%2F%2Falternativo.mx%2F2020%2F02%2Fuefa-expulsa-al-manchester-city-por-2-anos%2F&psig=AOvVaw0EUAHkYxOL6Ca-UHX1WPaH&ust=1632006124462000&source=images&cd=vfe&ved=0CAkQjRxqFwoTCJDckd2Oh_MCFQAAAAAdAAAAABAJ")
        )
            val soccerAdapter = SoccerAdapter(requireContext(),teamSoccers)
            binding.spinnerSoccers.adapter = soccerAdapter

    }




}
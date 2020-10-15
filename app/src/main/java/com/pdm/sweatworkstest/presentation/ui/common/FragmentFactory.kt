package com.pdm.sweatworkstest.presentation.ui.common

import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.pdm.sweatworkstest.presentation.ui.details.DetailsFragment
import com.pdm.sweatworkstest.presentation.ui.home.HomeFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@ExperimentalCoroutinesApi
class FragmentFactory
@Inject
constructor(
    private val viewModelFactory: ViewModelProvider.Factory
): FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String) =

        when(className){

            HomeFragment::class.java.name -> {
                val fragment = HomeFragment(viewModelFactory)
                fragment
            }

            DetailsFragment::class.java.name -> {
                val fragment = DetailsFragment(viewModelFactory)
                fragment
            }

            else -> {
                super.instantiate(classLoader, className)
            }
        }
}
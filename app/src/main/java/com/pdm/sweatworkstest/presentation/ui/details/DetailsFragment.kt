package com.pdm.sweatworkstest.presentation.ui.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.pdm.sweatworkstest.databinding.FragmentDetailsBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import androidx.navigation.fragment.findNavController
import com.pdm.sweatworkstest.R
import com.pdm.sweatworkstest.presentation.ui.common.BaseFragment
import com.pdm.sweatworkstest.presentation.util.toast

@ExperimentalCoroutinesApi
class DetailsFragment(private val viewModelFactory: ViewModelProvider.Factory) : BaseFragment() {

    private var thumbnailImage: String? = null
    private var name: String? = null
    private var lastName: String? = null
    private var username: String? = null
    private var uuid: String? = null
    private var phone: String? = null
    private var title: String? = null
    private var email: String? = null
    private var largeImage: String? = null
    private var mediumImage: String? = null

    private val viewModel: DetailsViewModel by viewModels() {
        viewModelFactory
    }

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            uuid = it.getString(UUID)
            title = it.getString(TITLE)
            thumbnailImage = it.getString(THUMB_IMG)
            mediumImage = it.getString(MEDIUM_IMG)
            largeImage = it.getString(LARGE_IMG)
            name = it.getString(NAME)
            lastName = it.getString(LAST_NAME)
            email = it.getString(EMAIL)
            phone = it.getString(PHONE)
            username = it.getString(USERNAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!::navController.isInitialized) navController = Navigation.findNavController(view)
        setupOnBackPressDispatcher()
        viewModel.userIsFavorite(uuid!!)
        initViews()
        subscribeObservers()
    }

    private fun initViews() {

        binding.toolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }

        binding.txtName.text = "$title $name $lastName"
        binding.txtEmail.text = email
        binding.txtPhone.text = phone
        binding.txtUserName.text = username
        binding.fabAdd.setOnClickListener {
            viewModel.addFavoriteUser(
                uuid!!,
                username!!,
                title!!,
                name!!,
                lastName!!,
                email!!,
                phone!!,
                largeImage!!,
                mediumImage!!,
                thumbnailImage!!
            )
            context?.toast("User added to favorites")
        }
        binding.fabRemove.setOnClickListener {
            viewModel.deleteFromFavoriteUser(
                uuid!!
            )
            context?.toast("User removed from favorites")
        }
        largeImage?.let {
            Glide.with(this).load(it).into(binding.backdrop)
            startPostponedEnterTransition()
        } ?: run {
            startPostponedEnterTransition()
        }
    }

    private fun subscribeObservers() {
        viewModel.isFavorite.observe(viewLifecycleOwner, { count ->
            if (count > 0 ) {
                binding.fabAdd.visibility = View.GONE
                binding.fabRemove.visibility = View.VISIBLE
            } else {
                binding.fabAdd.visibility = View.VISIBLE
                binding.fabRemove.visibility = View.GONE
            }
        })
    }

    override fun inject() {
        getAppComponent().inject(this)
    }

    private fun onBackPressed() {
        findNavController().popBackStack()
    }

    private fun setupOnBackPressDispatcher() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackPressed()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val THUMB_IMG: String = "THUMB_IMG"
        const val LARGE_IMG: String = "LARGE_IMG"
        const val MEDIUM_IMG: String = "MEDIUM_IMG"
        const val TITLE: String = "TITLE"
        const val EMAIL: String = "EMAIL"
        const val NAME: String = "NAME"
        const val LAST_NAME: String = "LAST_NAME"
        const val USERNAME: String = "USERNAME"
        const val UUID: String = "UUID"
        const val PHONE: String = "PHONE"
    }
}
package com.pdm.sweatworkstest.presentation.ui.home

import android.content.Intent
import android.os.Bundle
import android.os.Debug
import android.provider.ContactsContract
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pdm.sweatworkstest.R
import com.pdm.sweatworkstest.core.domain.FavoriteUser
import com.pdm.sweatworkstest.core.domain.User
import com.pdm.sweatworkstest.core.domain.util.DataState
import com.pdm.sweatworkstest.databinding.FragmentHomeBinding
import com.pdm.sweatworkstest.databinding.ItemFavoriteUserBinding
import com.pdm.sweatworkstest.databinding.ItemUserCardBinding
import com.pdm.sweatworkstest.presentation.adapters.FavoritesUsersAdapter
import com.pdm.sweatworkstest.presentation.adapters.UserAdapter
import com.pdm.sweatworkstest.presentation.ui.common.BaseFragment
import com.pdm.sweatworkstest.presentation.ui.details.DetailsFragment
import com.pdm.sweatworkstest.presentation.util.hide
import com.pdm.sweatworkstest.presentation.util.show
import com.pdm.sweatworkstest.presentation.util.snackbar
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
class HomeFragment(private val viewModelFactory: ViewModelProvider.Factory) : BaseFragment(), SearchView.OnQueryTextListener {

    private val viewModel: HomeViewModel by viewModels() {
        viewModelFactory
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var layoutManager:GridLayoutManager

    private val lastVisibleItemPosition: Int
        get() = layoutManager.findLastVisibleItemPosition()

    private val favoritesUsersAdapter by lazy {
        FavoritesUsersAdapter { user, view ->
            // SELECT FAVORITE USER
            navigateToDetailsFromFavorite(user,view)
        }
    }

    private val userAdapter by lazy {
        UserAdapter({ user, view ->
            // SELECT USER
            navigateToDetails(user,view)
        }, { user ->
            // ADD CONTACT
            addContact(user)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!::navController.isInitialized) navController = Navigation.findNavController(view)
        layoutManager = GridLayoutManager(context, 3)
        initViews()
        viewModel.setStateEvent(MainStateEvent.GetUsers)
        viewModel.setStateEvent(MainStateEvent.GetFavoriteUsers)
        subscribeObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        val searchItem = menu.findItem(R.id.appSearchBar)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
    }

    override fun inject() {
        getAppComponent().inject(this)
    }

    private fun initViews() {
        binding.recyclerViewUsers.layoutManager = layoutManager
        binding.recyclerViewUsers.adapter = userAdapter
        binding.recyclerViewUsers.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = recyclerView.layoutManager?.itemCount
                if (totalItemCount == lastVisibleItemPosition + 1) {
                    viewModel.setStateEvent(MainStateEvent.GetUsers)
                }
            }
        })

        binding.recyclerViewFavorites.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.recyclerViewFavorites.adapter = favoritesUsersAdapter
    }


    private fun subscribeObservers() {
        viewModel.dataStateUsers.observe(viewLifecycleOwner, Observer { dataState ->
            when (dataState) {
                is DataState.Success<List<User>> -> {
                    binding.progressBar.hide()
                    userAdapter.addUsers(dataState.data)
                    Log.d("USERS", dataState.data.toString())
                }
                is DataState.Error -> {
                    binding.progressBar.show()
                    binding.root.snackbar(dataState.exception.message!!)
                }
                is DataState.Loading -> {
                    binding.progressBar.show()
                }
            }
        })
        viewModel.dataStateFavoriteUsers.observe(viewLifecycleOwner, { dataState ->
            when (dataState) {
                is DataState.Success<List<FavoriteUser>> -> {
                    favoritesUsersAdapter.addUsers(dataState.data)
                    if (dataState.data.isNotEmpty()) binding.titleFavorites.visibility = View.VISIBLE else binding.titleFavorites.visibility = View.GONE
                    Log.d("USERS", dataState.data.toString())
                }
                is DataState.Error -> {
                    binding.root.snackbar(dataState.exception.message!!)
                }
                is DataState.Loading -> {
                }
            }
        })
    }

    private fun navigateToDetails(user: User, view: ItemUserCardBinding) {
        val bundle = bundleOf(
            DetailsFragment.UUID to user.login.uuid,
            DetailsFragment.TITLE to user.name.title,
            DetailsFragment.EMAIL to user.email,
            DetailsFragment.NAME to user.name.first,
            DetailsFragment.LAST_NAME to user.name.last,
            DetailsFragment.USERNAME to user.login.username,
            DetailsFragment.PHONE to user.phone,
            DetailsFragment.THUMB_IMG to user.picture.thumbnail,
            DetailsFragment.LARGE_IMG to user.picture.large,
            DetailsFragment.MEDIUM_IMG to user.picture.medium
        )
        navController.navigate(
            R.id.action_homeFragment_to_detailsFragment,
            bundle
        )
    }

    private fun navigateToDetailsFromFavorite(user: FavoriteUser, view: ItemFavoriteUserBinding) {
        val bundle = bundleOf(
            DetailsFragment.UUID to user.uuid,
            DetailsFragment.TITLE to user.titleName,
            DetailsFragment.EMAIL to user.email,
            DetailsFragment.NAME to user.firstName,
            DetailsFragment.LAST_NAME to user.lastName,
            DetailsFragment.USERNAME to user.username,
            DetailsFragment.PHONE to user.phone,
            DetailsFragment.THUMB_IMG to user.thumbnailImage,
            DetailsFragment.LARGE_IMG to user.largeImage,
            DetailsFragment.MEDIUM_IMG to user.mediumImage
        )
        navController.navigate(
            R.id.action_homeFragment_to_detailsFragment,
            bundle
        )
    }

    private fun addContact(user: User) {
        val contactIntent = Intent(ContactsContract.Intents.Insert.ACTION)
        contactIntent.type = ContactsContract.RawContacts.CONTENT_TYPE
        contactIntent
            .putExtra(ContactsContract.Intents.Insert.NAME, "${user.name.first} ${user.name.last}")
            .putExtra(ContactsContract.Intents.Insert.PHONE, user.phone)

        startActivityForResult(contactIntent, 1)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        userAdapter.filter.filter(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        userAdapter.filter.filter(newText)
        return false
    }
}
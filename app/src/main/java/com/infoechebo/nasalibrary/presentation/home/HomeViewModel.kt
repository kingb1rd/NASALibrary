package com.infoechebo.nasalibrary.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infoechebo.nasalibrary.domain.model.NasaImage
import com.infoechebo.nasalibrary.domain.usecase.NasaSearch
import com.infoechebo.nasalibrary.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val nasaSearch: NasaSearch) : ViewModel() {
    private val _homeState = MutableStateFlow<HomeState>(HomeState.Empty)
    val homeState: StateFlow<HomeState> = _homeState.asStateFlow()

    fun onSearch(query: String) = viewModelScope.launch {
        nasaSearch(query).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _homeState.value = HomeState.Success(result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _homeState.value = HomeState.Error(result.message ?: "Unknown error")
                }
                is Resource.Loading -> {
                    _homeState.value = HomeState.Loading
                }
            }
        }.launchIn(this)
    }

    sealed class HomeState {
        class Success(val nasaImages: List<NasaImage>) : HomeState()
        class Error(val message: String) : HomeState()
        object Loading : HomeState()
        object Empty : HomeState()
    }
}
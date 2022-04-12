package com.exseed.health.ui.main_activity

import androidx.lifecycle.ViewModel
import com.exseed.health.data.repos.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private var repository: AppRepository,
) : ViewModel() {
    fun getRepositoriesList(query: String, per_page: Int, page: Int) =
        repository.getRepositoriesList(query, per_page, page)
}
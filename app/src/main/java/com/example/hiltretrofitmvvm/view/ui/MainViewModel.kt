package com.example.hiltretrofitmvvm.view.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiltretrofitmvvm.data.Post
import com.example.hiltretrofitmvvm.repo.MainRepository
import com.example.hiltretrofitmvvm.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _res = MutableLiveData<Resource<Response<List<Post>>>>()

    val res: LiveData<Resource<Response<List<Post>>>>
        get() = _res

    init {
        getEmployees()
    }

    private fun getEmployees() = viewModelScope.launch {
        _res.postValue(Resource.loading(null))
        mainRepository.getPosts().let {
            if (it.isSuccessful) {
                _res.postValue(Resource.success(it))
            } else {
                _res.postValue(Resource.error("", null))
            }
        }
    }
}
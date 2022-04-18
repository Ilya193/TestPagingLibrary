package com.xlwe.testpaginglibrary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn

class MainViewModel : ViewModel() {

    val data = Pager(PagingConfig(pageSize = 1)) {
        TestPagingSource()
    }.flow.cachedIn(viewModelScope)
}
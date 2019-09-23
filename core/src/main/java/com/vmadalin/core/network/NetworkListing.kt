package com.vmadalin.core.network

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class NetworkListing<T : Any>(
    val pagedList: LiveData<PagedList<T>>,
    val networkState: LiveData<NetworkState>,
    val refreshState: LiveData<NetworkState>,
    val refreshData: suspend () -> Unit
)

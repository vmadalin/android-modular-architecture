/*
 * Copyright 2019 vmadalin.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vmadalin.core.ui.bindings

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

@BindingAdapter("showRefreshing")
fun setShowRefreshing(swipeRefreshLayout: SwipeRefreshLayout, refresh: Boolean) {
    if (refresh && !swipeRefreshLayout.isRefreshing) {
        swipeRefreshLayout.isRefreshing = refresh
    } else if (!refresh && swipeRefreshLayout.isRefreshing) {
        swipeRefreshLayout.isRefreshing = refresh
    }
}

@BindingAdapter("onRefresh")
fun setOnRefresh(swipeRefreshLayout: SwipeRefreshLayout, onRefresh: () -> Unit) {
    swipeRefreshLayout.setOnRefreshListener {
        onRefresh()
    }
}

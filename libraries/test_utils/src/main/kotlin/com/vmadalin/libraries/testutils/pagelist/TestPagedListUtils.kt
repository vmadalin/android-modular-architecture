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

package com.vmadalin.libraries.testutils.pagelist

import android.annotation.SuppressLint
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.room.RoomDatabase
import androidx.room.RoomSQLiteQuery
import com.vmadalin.libraries.testutils.datasource.TestLimitDataSource
import com.vmadalin.libraries.testutils.livedata.getOrAwaitValue
import io.mockk.mockk

fun <T> pagedListOf(vararg elements: T, config: PagedList.Config? = null): PagedList<T>? {
    val defaultConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(elements.count())
        .setMaxSize(elements.count() + 2)
        .setPrefetchDistance(1)
        .build()
    return LivePagedListBuilder<Int, T>(
        createMockDataSourceFactory(elements.toList()),
        config ?: defaultConfig
    ).build().getOrAwaitValue()
}

private fun <T> createMockDataSourceFactory(itemList: List<T>): DataSource.Factory<Int, T> =
    object : DataSource.Factory<Int, T>() {
        @SuppressLint("RestrictedApi")
        override fun create(): DataSource<Int, T> {
            val mockQuery = mockk<RoomSQLiteQuery>(relaxed = true)
            val mockDb = mockk<RoomDatabase>(relaxed = true)

            return TestLimitDataSource(
                db = mockDb,
                query = mockQuery,
                itemList = itemList
            )
        }
    }

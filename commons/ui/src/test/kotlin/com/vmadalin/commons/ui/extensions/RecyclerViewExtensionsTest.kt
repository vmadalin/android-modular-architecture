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

package com.vmadalin.commons.ui.extensions

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vmadalin.libraries.testutils.robolectric.TestRobolectric
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class RecyclerViewExtensionsTest : TestRobolectric() {

    private lateinit var recyclerView: RecyclerView

    @Before
    fun setUp() {
        recyclerView = RecyclerView(context)
    }

    @Test
    fun obtainRecyclerLayoutManagerAsGridType() {
        recyclerView.layoutManager = GridLayoutManager(context, 2)

        val gridLayoutManager = recyclerView.gridLayoutManager
        assertNotNull(gridLayoutManager)
        assertThat(gridLayoutManager, instanceOf(GridLayoutManager::class.java))
    }

    @Test
    fun obtainNullRecyclerGridLayoutManagerWithoutConfigured() {
        val gridLayoutManager = recyclerView.gridLayoutManager
        assertNull(gridLayoutManager)
    }

    @Test
    fun obtainRecyclerLayoutManagerAsLinearType() {
        recyclerView.layoutManager = LinearLayoutManager(context)

        val linearLayoutManager = recyclerView.linearLayoutManager
        assertNotNull(linearLayoutManager)
        assertThat(linearLayoutManager, instanceOf(LinearLayoutManager::class.java))
    }

    @Test
    fun obtainNullRecyclerLinearLayoutManagerWithoutConfigured() {
        val gridLayoutManager = recyclerView.linearLayoutManager
        assertNull(gridLayoutManager)
    }
}

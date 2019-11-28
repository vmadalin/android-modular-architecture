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

package com.vmadalin.commons.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.recyclerview.widget.RecyclerView
import com.nhaarman.mockitokotlin2.after
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.same
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.vmadalin.libraries.testutils.robolectric.TestRobolectric
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.anyString
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import org.robolectric.shadows.ShadowLooper

class BaseListAdapterTest : TestRobolectric() {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    interface Comparator : (String, String) -> Boolean {
        override fun invoke(p1: String, p2: String): Boolean = true
    }

    @Mock
    lateinit var viewHolder: RecyclerView.ViewHolder
    @Mock
    lateinit var itemsSame: Comparator
    @Mock
    lateinit var contentsSame: Comparator
    @Spy
    lateinit var adapter: TestBaseListAdapter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @After
    fun tearDown() {
        ShadowLooper.idleMainLooper()
    }

    @Test
    fun createViewHolder_ShouldInvokeAbstractMethod() {
        val parent = mock<ViewGroup>()
        val viewType = 1

        doReturn(context).whenever(parent).context
        adapter.onCreateViewHolder(parent, viewType)

        verify(adapter).onCreateViewHolder(same(parent), any(), same(viewType))
    }

    @Test
    fun listedRecycleView_ShouldInvokeItemsComparator() {
        adapter.submitList(listOf("item1", "item2"))
        adapter.submitList(listOf("item3", "item4"))

        verify(itemsSame, after(100).atLeastOnce()).invoke(anyString(), anyString())
    }

    @Test
    fun listedRecycleView_ShouldInvokeContentComparator() {
        doReturn(true).whenever(itemsSame).invoke(anyString(), anyString())

        adapter.submitList(listOf("item1", "item2"))
        adapter.submitList(listOf("item6", "item4", "item2"))

        verify(contentsSame, after(100).atLeastOnce()).invoke(anyString(), anyString())
    }

    @Test
    fun emptyRecycleView_ShouldNotInvokeAnyComparator() {
        verify(itemsSame, after(100).never()).invoke(anyString(), anyString())
        verify(contentsSame, after(100).never()).invoke(anyString(), anyString())
    }

    inner class TestBaseListAdapter : com.vmadalin.commons.ui.base.BaseListAdapter<String>(
        itemsSame = itemsSame,
        contentsSame = contentsSame
    ) {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            inflater: LayoutInflater,
            viewType: Int
        ): RecyclerView.ViewHolder {
            return viewHolder
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {}
    }
}

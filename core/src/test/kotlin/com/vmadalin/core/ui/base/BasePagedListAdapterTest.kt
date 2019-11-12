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

package com.vmadalin.core.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.vmadalin.core.base.BaseRobolectricTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.anyInt
import org.mockito.MockitoAnnotations

class BasePagedListAdapterTest : BaseRobolectricTest() {

    interface ItemComparator : (String, String) -> Boolean {
        override fun invoke(p1: String, p2: String): Boolean = false
    }

    @Mock
    lateinit var viewHolder: RecyclerView.ViewHolder
    @Mock
    lateinit var itemsSame: ItemComparator
    @Mock
    lateinit var contentsSame: ItemComparator
    @Mock
    lateinit var recyclerView: RecyclerView

    private lateinit var adapter: TestBasePagedListAdapter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        adapter = TestBasePagedListAdapter()
    }

    @Test
    fun initializeAdapterCorrectly() {
        assertTrue(adapter.hasStableIds())
    }

    @Test
    fun attachedRecycleView_ShouldStoreInstance() {
        val attachedRecyclerView: RecyclerView = mock()

        adapter.onAttachedToRecyclerView(attachedRecyclerView)

        assertEquals(attachedRecyclerView, adapter.recyclerView)
    }

    @Test
    fun detachedRecycleView_ShouldDestroyInstance() {
        val detachedRecyclerView: RecyclerView = mock()

        adapter.onDetachedFromRecyclerView(detachedRecyclerView)

        assertNull(adapter.recyclerView)
    }

    @Test
    fun submitEmptyList_ShouldNotScrollUp() {
        val pagedList = mock<PagedList<String>>()
        doReturn(false).whenever(pagedList).isNullOrEmpty()

        adapter.submitList(pagedList)

        verify(recyclerView, never()).scrollToPosition(anyInt())
    }

    @Test
    fun submitList_ShouldScrollUp() {
        val pagedList = mock<PagedList<String>>()
        val positionCaptor = argumentCaptor<Int>()
        doReturn(true).whenever(pagedList).isNullOrEmpty()

        adapter.submitList(pagedList)

        verify(recyclerView).scrollToPosition(positionCaptor.capture())
        assertEquals(0, positionCaptor.lastValue)
    }

    inner class TestBasePagedListAdapter : BasePagedListAdapter<String>(
        itemsSame = itemsSame,
        contentsSame = contentsSame,
        recyclerView = recyclerView
    ) {

        override fun onCreateViewHolder(
            parent: ViewGroup,
            inflater: LayoutInflater,
            viewType: Int
        ): RecyclerView.ViewHolder {
            return viewHolder
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) { }
    }
}

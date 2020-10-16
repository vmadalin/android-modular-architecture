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
import com.vmadalin.libraries.testutils.pagelist.pagedListOf
import com.vmadalin.libraries.testutils.robolectric.TestRobolectric
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BasePagedListAdapterTest : TestRobolectric() {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    interface Comparator : (String, String) -> Boolean {
        override fun invoke(p1: String, p2: String): Boolean = false
    }

    @MockK(relaxed = true)
    lateinit var viewHolder: RecyclerView.ViewHolder
    @MockK(relaxed = true)
    lateinit var itemsSame: Comparator
    @MockK(relaxed = true)
    lateinit var contentsSame: Comparator
    @MockK(relaxed = true)
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: TestBasePagedListAdapter

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        adapter = spyk(TestBasePagedListAdapter(), recordPrivateCalls = true)
    }

    @Test
    fun initializeAdapter_WithoutRecycleView() {
        assertTrue(adapter.hasStableIds())
        assertNull(adapter.recyclerView)
    }

    @Test
    fun initializeAdapter_WithRecycleView() {
        adapter.recyclerView = recyclerView

        assertTrue(adapter.hasStableIds())
        assertEquals(recyclerView, adapter.recyclerView)
    }

    @Test
    fun createViewHolder_ShouldInvokeAbstractMethod() {
        val parent = mockk<ViewGroup>()
        val viewType = 1

        every { parent.context } returns context
        adapter.onCreateViewHolder(parent, viewType)

        verify { adapter.onCreateViewHolder(parent, any(), viewType) }
    }

    @Test
    fun listedRecycleView_ShouldInvokeItemsComparator() {
        adapter.submitList(pagedListOf("item1", "item2"))
        adapter.submitList(pagedListOf("item3", "item4"))

        verify(timeout = 300, atLeast = 1) { itemsSame.invoke(any(), any()) }
    }

    @Test
    fun listedRecycleView_ShouldInvokeContentComparator() {
        every { itemsSame.invoke(any(), any()) } returns true

        adapter.submitList(pagedListOf("item1", "item2"))
        adapter.submitList(pagedListOf("item6", "item4", "item2"))

        verify(timeout = 300, atLeast = 1) { contentsSame.invoke(any(), any()) }
    }

    @Test
    fun emptyRecycleView_ShouldNotInvokeAnyComparator() {
        verify(timeout = 300, exactly = 0) { itemsSame.invoke(any(), any()) }
        verify(timeout = 300, exactly = 0) { contentsSame.invoke(any(), any()) }
    }

    @Test
    fun attachedRecycleView_ShouldStoreInstance() {
        val attachedRecyclerView: RecyclerView = mockk()

        adapter.recyclerView = recyclerView
        adapter.onAttachedToRecyclerView(attachedRecyclerView)

        assertEquals(attachedRecyclerView, adapter.recyclerView)
    }

    @Test
    fun detachedRecycleView_ShouldDestroyInstance() {
        val detachedRecyclerView: RecyclerView = mockk()

        adapter.recyclerView = recyclerView
        adapter.onDetachedFromRecyclerView(detachedRecyclerView)

        assertNull(adapter.recyclerView)
    }

    @Test
    fun submitNull_ShouldScrollUp() {
        adapter.recyclerView = recyclerView
        adapter.submitList(null)

        verify { recyclerView.scrollToPosition(0) }
    }

    @Test
    fun submitNull_ShouldNotScrollUp() {
        adapter.submitList(null)

        verify(exactly = 0) { recyclerView.scrollToPosition(any()) }
    }

    inner class TestBasePagedListAdapter : BasePagedListAdapter<String>(
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

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) { }
    }
}

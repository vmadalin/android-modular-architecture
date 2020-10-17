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

package com.vmadalin.dynamicfeatures.characterslist.ui.list.adapter.holders

import android.view.LayoutInflater
import android.view.View
import androidx.databinding.ViewDataBinding
import com.vmadalin.dynamicfeatures.characterslist.databinding.ListItemErrorBinding
import com.vmadalin.dynamicfeatures.characterslist.ui.list.CharactersListViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ErrorViewHolderTest {

    @MockK
    lateinit var view: View

    @MockK
    lateinit var layoutInflater: LayoutInflater

    @MockK(relaxed = true)
    lateinit var binding: ListItemErrorBinding
    lateinit var viewHolder: ErrorViewHolder

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun createViewHolder_ShouldInitializeCorrectly() {
        mockkStatic(ListItemErrorBinding::class)
        every { (binding as ViewDataBinding).root } returns view
        every { ListItemErrorBinding.inflate(layoutInflater) } returns binding

        viewHolder = ErrorViewHolder(layoutInflater)

        assertEquals(binding, viewHolder.binding)
    }

    @Test
    fun bindViewHolder_ShouldBindingDataVariable() {
        mockkStatic(ListItemErrorBinding::class)
        every { (binding as ViewDataBinding).root } returns view
        every { ListItemErrorBinding.inflate(layoutInflater) } returns binding

        val viewModel = mockk<CharactersListViewModel>()
        viewHolder = ErrorViewHolder(layoutInflater)
        viewHolder.bind(viewModel)

        verify { binding.viewModel = viewModel }
        verify { binding.executePendingBindings() }
    }
}

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

import android.view.View
import androidx.databinding.ViewDataBinding
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class BaseViewHolderTest {

    @MockK
    lateinit var binding: ViewDataBinding
    @MockK(relaxed = true)
    lateinit var rootView: View

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun createBaseViewHolder_ShouldInitializeCorrectly() {
        every { binding.root } returns rootView

        val baseViewHolder = TestBaseViewHolder()

        assertEquals(binding, baseViewHolder.binding)
        assertEquals(binding.root, baseViewHolder.itemView)
    }

    inner class TestBaseViewHolder : BaseViewHolder<ViewDataBinding>(
        binding = binding
    )
}

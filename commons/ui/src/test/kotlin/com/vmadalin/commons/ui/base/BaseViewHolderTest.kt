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
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseViewHolderTest {

    private val binding: ViewDataBinding = mock()
    private val rootView: View = mock()

    @Test
    fun createBaseViewHolder_ShouldInitializeCorrectly() {
        doReturn(rootView).whenever(binding).root

        val baseViewHolder = TestBaseViewHolder()

        assertEquals(binding, baseViewHolder.binding)
        assertEquals(binding.root, baseViewHolder.itemView)
    }

    inner class TestBaseViewHolder : com.vmadalin.commons.ui.base.BaseViewHolder<ViewDataBinding>(
        binding = binding
    )
}

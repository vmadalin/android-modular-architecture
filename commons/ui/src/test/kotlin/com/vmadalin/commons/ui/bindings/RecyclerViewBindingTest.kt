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

package com.vmadalin.commons.ui.bindings

import androidx.recyclerview.widget.RecyclerView
import com.vmadalin.commons.ui.recyclerview.RecyclerViewItemDecoration
import com.vmadalin.libraries.testutils.robolectric.TestRobolectric
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RecyclerViewBindingTest : TestRobolectric() {

    private lateinit var recyclerView: RecyclerView

    @Before
    fun setUp() {
        recyclerView = RecyclerView(context)
    }

    @Test
    fun setItemDecoration_ShouldAddProperlySpacing() {
        val spacingPx = 10f

        recyclerView.setItemDecorationSpacing(spacingPx)

        assertEquals(1, recyclerView.itemDecorationCount)

        val decoration = recyclerView.getItemDecorationAt(0)
        assertThat(decoration, instanceOf(RecyclerViewItemDecoration::class.java))
        assertEquals(spacingPx.toInt(), (decoration as RecyclerViewItemDecoration).spacingPx)
    }
}

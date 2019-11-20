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

package com.vmadalin.dynamicfeatures.caractersfavorites.ui.favorite.adapter

import androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_IDLE
import androidx.recyclerview.widget.ItemTouchHelper.LEFT
import androidx.recyclerview.widget.ItemTouchHelper.RIGHT
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.vmadalin.dynamicfeatures.charactersfavorites.ui.favorite.adapter.CharactersFavoriteTouchHelper
import io.mockk.Called
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyAll
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class CharactersFavoriteTouchHelperTest {

    @MockK(relaxed = true)
    lateinit var onSwiped: (Int) -> Unit
    lateinit var touchHelper: CharactersFavoriteTouchHelper

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        touchHelper = CharactersFavoriteTouchHelper(onSwiped)
    }

    @Test
    fun createTouchHelper_ShouldInitializeCorrectly() {
        val recycleView = mockk<RecyclerView>()
        val viewHolder = mockk<RecyclerView.ViewHolder>()

        touchHelper.getDragDirs(recycleView, viewHolder).run {
            assertEquals(ACTION_STATE_IDLE, this)
        }

        touchHelper.getSwipeDirs(recycleView, viewHolder).run {
            assertEquals(LEFT or RIGHT, this)
        }
    }

    @Test
    fun moveTouchEvent_ShouldIgnoreIt() {
        val recycleView = mockk<RecyclerView>()
        val viewHolder = mockk<RecyclerView.ViewHolder>()
        val target = mockk<RecyclerView.ViewHolder>()

        touchHelper.onMove(recycleView, viewHolder, target).run {
            assertTrue(this)
        }

        verifyAll {
            recycleView wasNot Called
            viewHolder wasNot Called
            target wasNot Called
        }
    }

    @Test
    fun swipeTouchEvent_OnAlreadyRemovedItem_ShouldInvokeWithNoPosition() {
        val viewHolder = mockk<RecyclerView.ViewHolder>()
        val direction = LEFT

        every { viewHolder.adapterPosition } returns NO_POSITION

        touchHelper.onSwiped(viewHolder, direction)

        verify {
            onSwiped.invoke(NO_POSITION)
        }
    }

    @Test
    fun swipeTouchEvent_OnExistItem_ShouldInvokeWithPosition() {
        val viewHolder = mockk<RecyclerView.ViewHolder>()
        val direction = LEFT
        val swipePosition = 2

        every { viewHolder.adapterPosition } returns swipePosition

        touchHelper.onSwiped(viewHolder, direction)

        verify {
            onSwiped.invoke(swipePosition)
        }
    }
}

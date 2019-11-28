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

import android.view.View
import com.vmadalin.libraries.testutils.robolectric.TestRobolectric
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ViewBindingsTest : TestRobolectric() {

    private lateinit var view: View

    @Before
    fun setUp() {
        view = View(context)
    }

    @Test
    fun onViewVisibilityVisible_ShouldBeVisible() {
        view.visibility = View.VISIBLE
        assertTrue(view.visible)
        assertFalse(view.invisible)
        assertFalse(view.gone)
    }

    @Test
    fun onViewVisibilityInvisible_ShouldBeInvisible() {
        view.visibility = View.INVISIBLE
        assertTrue(view.invisible)
        assertFalse(view.visible)
        assertFalse(view.gone)
    }

    @Test
    fun onViewVisibilityGone_ShouldBeGone() {
        view.visibility = View.GONE
        assertTrue(view.gone)
        assertFalse(view.visible)
        assertFalse(view.invisible)
    }

    @Test
    fun forceViewVisibility_AsVisible() {
        view.visible = true
        assertTrue(view.visibility == View.VISIBLE)
    }

    @Test
    fun forceViewVisibility_AsNonVisible() {
        view.visible = false
        assertTrue(view.visibility == View.GONE)
    }

    @Test
    fun forceViewVisibility_AsInvisible() {
        view.invisible = true
        assertTrue(view.visibility == View.INVISIBLE)
    }

    @Test
    fun forceViewVisibility_AsNonInvisible() {
        view.invisible = false
        assertTrue(view.visibility == View.VISIBLE)
    }

    @Test
    fun forceViewVisibility_AsGone() {
        view.gone = true
        assertTrue(view.visibility == View.GONE)
    }

    @Test
    fun forceViewVisibility_AsNonGone() {
        view.gone = false
        assertTrue(view.visibility == View.VISIBLE)
    }
}

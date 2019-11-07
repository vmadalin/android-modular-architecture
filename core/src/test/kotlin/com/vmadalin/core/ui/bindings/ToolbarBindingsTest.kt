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

package com.vmadalin.core.ui.bindings

import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.view.get
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.verify
import com.vmadalin.core.base.BaseRobolectricTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.never

class ToolbarBindingsTest : BaseRobolectricTest() {

    private lateinit var toolbar: Toolbar

    @Before
    fun setUp() {
        toolbar = Toolbar(context)
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_add)
    }

    @Test
    fun performNoClickOnToolbar_ShouldNotInterceptAsNavigationClick() {
        val clickListener = mock(View.OnClickListener::class.java)

        toolbar.setNavigationOnClick(clickListener)

        verify(clickListener, never()).onClick(any())
    }

    @Test
    fun performClickOnToolbar_ShouldNotInterceptAsNavigationClick() {
        val clickListener = mock(View.OnClickListener::class.java)

        toolbar.setNavigationOnClick(clickListener)
        toolbar.performClick()

        verify(clickListener, never()).onClick(any())
    }

    @Test
    fun performClickOnNavigationToolbar_ShouldInterceptAsClick() {
        val clickListener = mock(View.OnClickListener::class.java)
        val clickListenerArgumentCaptor = argumentCaptor<Toolbar>()
        val toolbarNavigationButtonView = toolbar[0]

        toolbar.setNavigationOnClick(clickListener)
        toolbarNavigationButtonView.performClick()

        verify(clickListener).onClick(clickListenerArgumentCaptor.capture())
        assertEquals(toolbarNavigationButtonView, clickListenerArgumentCaptor.firstValue)
    }
}

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

package com.vmadalin.commons.ui.extensions

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.times
import com.vmadalin.libraries.testutils.lifecycle.TestLifecycleOwner
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.verify

class LifecycleOwnerExtensionsTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var lifecycleOwner: LifecycleOwner

    @Before
    fun setUp() {
        lifecycleOwner = TestLifecycleOwner()
    }

    @Test
    fun observingMutableLiveData_WhenPostStringValue_ShouldTrigger() {
        val mutableLiveData = MutableLiveData<String>()
        val observerPostValue = "Event Value"
        val observer = mock<(String) -> Unit>()
        val observerCaptor = argumentCaptor<String>()

        lifecycleOwner.observe(mutableLiveData, observer)
        mutableLiveData.postValue(observerPostValue)

        verify(observer).invoke(observerCaptor.capture())
        assertEquals(observerPostValue, observerCaptor.lastValue)
    }

    @Test
    fun observingMutableLiveData_WhenPostMultipleIntValue_ShouldTriggerMultipleTimes() {
        val mutableLiveData = MutableLiveData<Int>()
        val observerPostValue = 3
        val observer = mock<(Int) -> Unit>()
        val observerCaptor = argumentCaptor<Int>()

        lifecycleOwner.observe(mutableLiveData, observer)
        mutableLiveData.postValue(observerPostValue)
        mutableLiveData.postValue(observerPostValue)

        verify(observer, times(2)).invoke(observerCaptor.capture())
        assertEquals(observerPostValue, observerCaptor.lastValue)

        mutableLiveData.postValue(observerPostValue)

        verify(observer, times(3)).invoke(observerCaptor.capture())
        assertEquals(observerPostValue, observerCaptor.lastValue)
    }

    @Test
    fun observingMutableLiveDat_WhenPostNullValue_ShouldNotTrigger() {
        val mutableLiveData = MutableLiveData<Int>()
        val observer = mock<(Int) -> Unit>()

        lifecycleOwner.observe(mutableLiveData, observer)
        mutableLiveData.postValue(null)

        verify(observer, never()).invoke(anyInt())
    }

    @Test
    fun observingMutableLiveData_WithoutPostValue_ShouldNotTrigger() {
        val mutableLiveData = MutableLiveData<Int>()
        val observer = mock<(Int) -> Unit>()

        lifecycleOwner.observe(mutableLiveData, observer)

        verify(observer, never()).invoke(anyInt())
    }

    @Test
    fun observingLiveData_WhenPostStringValue_ShouldTrigger() {
        val mutableLiveData = MutableLiveData<String>()
        val liveData: LiveData<String> = mutableLiveData
        val observerPostValue = "Event Value"
        val observer = mock<(String) -> Unit>()
        val observerCaptor = argumentCaptor<String>()

        lifecycleOwner.observe(liveData, observer)
        mutableLiveData.postValue(observerPostValue)

        verify(observer).invoke(observerCaptor.capture())
        assertEquals(observerPostValue, observerCaptor.lastValue)
    }

    @Test
    fun observingLiveData_WhenPostMultipleIntValue_ShouldTriggerMultipleTimes() {
        val mutableLiveData = MutableLiveData<Int>()
        val liveData: LiveData<Int> = mutableLiveData
        val observerPostValue = 3
        val observer = mock<(Int) -> Unit>()
        val observerCaptor = argumentCaptor<Int>()

        lifecycleOwner.observe(liveData, observer)
        mutableLiveData.postValue(observerPostValue)
        mutableLiveData.postValue(observerPostValue)

        verify(observer, times(2)).invoke(observerCaptor.capture())
        assertEquals(observerPostValue, observerCaptor.lastValue)

        mutableLiveData.postValue(observerPostValue)

        verify(observer, times(3)).invoke(observerCaptor.capture())
        assertEquals(observerPostValue, observerCaptor.lastValue)
    }

    @Test
    fun observingLiveData_WhenPostNullValue_ShouldNotTrigger() {
        val mutableLiveData = MutableLiveData<Int>()
        val liveData: LiveData<Int> = mutableLiveData
        val observer = mock<(Int) -> Unit>()

        lifecycleOwner.observe(liveData, observer)
        mutableLiveData.postValue(null)

        verify(observer, never()).invoke(anyInt())
    }

    @Test
    fun observingLiveData_WithoutPostValue_ShouldNotTrigger() {
        val mutableLiveData = MutableLiveData<Int>()
        val liveData: LiveData<Int> = mutableLiveData
        val observer = mock<(Int) -> Unit>()

        lifecycleOwner.observe(liveData, observer)

        verify(observer, never()).invoke(anyInt())
    }
}

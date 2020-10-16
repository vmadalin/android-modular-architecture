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

package com.vmadalin.commons.ui.livedata

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import com.vmadalin.commons.ui.extensions.observe
import com.vmadalin.libraries.testutils.lifecycle.TestLifecycleOwner
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SingleLiveDataTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var lifecycleOwner: LifecycleOwner

    @Before
    fun setUp() {
        lifecycleOwner = TestLifecycleOwner()
    }

    @Test
    fun observingSingleLiveData_WhenPostStringValue_ShouldTriggerOneEvent() {
        val singleLiveData = SingleLiveData<String>()
        val observerPostValue = "Event Value"
        val observer = mockk<(String) -> Unit>(relaxed = true)
        val observerCaptor = slot<String>()

        lifecycleOwner.observe(singleLiveData, observer)
        singleLiveData.postValue(observerPostValue)

        verify { observer.invoke(capture(observerCaptor)) }
        assertEquals(observerPostValue, observerCaptor.captured)
    }

    @Test
    fun observingSingleLiveData_WhenPostMultipleIntValue_ShouldTriggerMultipleTimes() {
        val singleLiveData = SingleLiveData<Int>()
        val observerPostValue = 1
        val observer = mockk<(Int) -> Unit>(relaxed = true)
        val observerCaptor = slot<Int>()

        lifecycleOwner.observe(singleLiveData, observer)
        singleLiveData.postValue(observerPostValue)
        singleLiveData.postValue(observerPostValue)

        verify(exactly = 2) { observer.invoke(capture(observerCaptor)) }
        assertEquals(observerPostValue, observerCaptor.captured)

        singleLiveData.postValue(observerPostValue)

        verify(exactly = 3) { observer.invoke(capture(observerCaptor)) }
        assertEquals(observerPostValue, observerCaptor.captured)
    }

    @Test
    fun multipleObservingSingleLiveData_WhenPostIntValue_ShouldTriggerOnlyFirstObserver() {
        val singleLiveData = SingleLiveData<String>()
        val observerPostValue = "Event Value"
        val observer1 = mockk<(String) -> Unit>(relaxed = true)
        val observer2 = mockk<(String) -> Unit>()
        val observer3 = mockk<(String) -> Unit>()
        val observer1Captor = slot<String>()

        lifecycleOwner.observe(singleLiveData, observer1)
        lifecycleOwner.observe(singleLiveData, observer2)
        lifecycleOwner.observe(singleLiveData, observer3)
        singleLiveData.postValue(observerPostValue)

        verify { observer1.invoke(capture(observer1Captor)) }
        verify(exactly = 0) { observer2.invoke(any()) }
        verify(exactly = 0) { observer3.invoke(any()) }

        assertEquals(observerPostValue, observer1Captor.captured)
    }

    @Test
    fun observingSingleLiveData_WithoutPostValue_ShouldNotTrigger() {
        val singleLiveData = SingleLiveData<Int>()
        val observer = mockk<(Int) -> Unit>()

        lifecycleOwner.observe(singleLiveData, observer)

        verify(exactly = 0) { observer.invoke(any()) }
    }
}

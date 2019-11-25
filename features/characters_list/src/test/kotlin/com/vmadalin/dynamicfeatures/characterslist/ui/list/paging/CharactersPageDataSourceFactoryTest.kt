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

package com.vmadalin.dynamicfeatures.characterslist.ui.list.paging

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.same
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import javax.inject.Provider
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CharactersPageDataSourceFactoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Spy
    lateinit var providerDataSource: Provider<CharactersPageDataSource>
    @Spy
    lateinit var sourceLiveData: MutableLiveData<CharactersPageDataSource>
    @InjectMocks
    lateinit var dataSourceFactory: CharactersPageDataSourceFactory

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun initializeFactory_WithoutCreate_ShouldNotHaveDataSource() {
        verify(dataSourceFactory.sourceLiveData, never())
        assertNull(dataSourceFactory.sourceLiveData.value)
    }

    @Test
    fun initializeFactory_WithCreate_ShouldHaveDataSource() {
        doReturn(
            CharactersPageDataSource(mock(), mock(), mock())
        ).whenever(providerDataSource).get()

        val dataSource = dataSourceFactory.create() as CharactersPageDataSource

        verify(dataSourceFactory.sourceLiveData).postValue(same(dataSource))
    }

    @Test
    fun refreshDataSource_ShouldInvalidateData() {
        val dataSource = mock<CharactersPageDataSource>()
        doReturn(dataSource).whenever(sourceLiveData).value

        dataSourceFactory.refresh()

        verify(dataSource).invalidate()
        verify(dataSource, never()).retry()
    }

    @Test
    fun retryDataSource_ShouldRetryData() {
        val dataSource = mock<CharactersPageDataSource>()
        doReturn(dataSource).whenever(sourceLiveData).value

        dataSourceFactory.retry()

        verify(dataSource).retry()
        verify(dataSource, never()).invalidate()
    }
}

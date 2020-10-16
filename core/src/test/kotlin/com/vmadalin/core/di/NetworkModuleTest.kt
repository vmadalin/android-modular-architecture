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

package com.vmadalin.core.di

import com.vmadalin.core.BuildConfig
import com.vmadalin.core.di.modules.NetworkModule
import com.vmadalin.core.network.services.MarvelService
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit

class NetworkModuleTest {

    private lateinit var networkModule: NetworkModule

    @Before
    fun setUp() {
        networkModule = NetworkModule()
    }

    @Test
    fun verifyProvidedHttpLoggingInterceptor() {
        val interceptor = networkModule.provideHttpLoggingInterceptor()
        assertEquals(HttpLoggingInterceptor.Level.BODY, interceptor.level)
    }

    @Test
    fun verifyProvidedHttpClient() {
        val interceptor = mockk<HttpLoggingInterceptor>()
        val httpClient = networkModule.provideHttpClient(interceptor)

        assertEquals(1, httpClient.interceptors.size)
        assertEquals(interceptor, httpClient.interceptors.first())
    }

    @Test
    fun verifyProvidedRetrofitBuilder() {
        val retrofit = networkModule.provideRetrofitBuilder()

        assertEquals(BuildConfig.MARVEL_API_BASE_URL, retrofit.baseUrl().toUrl().toString())
    }

    @Test
    fun verifyProvidedMarvelService() {
        val retrofit = mockk<Retrofit>()
        val marvelService = mockk<MarvelService>()
        val serviceClassCaptor = slot<Class<*>>()

        every { retrofit.create<MarvelService>(any()) } returns marvelService

        networkModule.provideMarvelService(retrofit)

        verify { retrofit.create(capture(serviceClassCaptor)) }
        assertEquals(MarvelService::class.java, serviceClassCaptor.captured)
    }

    @Test
    fun verifyProvidedMarvelRepository() {
        val marvelService = mockk<MarvelService>()
        val marvelRepository = networkModule.provideMarvelRepository(marvelService)

        assertEquals(marvelService, marvelRepository.service)
    }
}

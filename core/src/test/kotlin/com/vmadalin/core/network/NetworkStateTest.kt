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

package com.vmadalin.core.network

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class NetworkStateTest {

    @Test
    fun defaultInitializeSuccessState_ShouldHaveDefaultValues() {
        val networkState = NetworkState.Success()

        assertTrue(networkState.isSuccess())
        assertEquals(false, networkState.isAdditional)
        assertEquals(false, networkState.isEmptyResponse)
    }

    @Test
    fun initializeSuccessState_ShouldHaveCorrectValues() {
        val isAdditional = true
        val isEmptyResponse = true
        val networkState = NetworkState.Success(
            isAdditional = isAdditional,
            isEmptyResponse = isEmptyResponse
        )

        assertTrue(networkState.isSuccess())
        assertEquals(isAdditional, networkState.isAdditional)
        assertEquals(isEmptyResponse, networkState.isEmptyResponse)
    }

    @Test
    fun defaultInitializeLoadingState_ShouldHaveDefaultValues() {
        val networkState = NetworkState.Loading()

        assertTrue(networkState.isLoading())
        assertEquals(false, networkState.isAdditional)
    }

    @Test
    fun initializeLoadingState_ShouldHaveCorrectValues() {
        val isAdditional = true
        val networkState = NetworkState.Loading(isAdditional)

        assertTrue(networkState.isLoading())
        assertEquals(isAdditional, networkState.isAdditional)
    }

    @Test
    fun defaultInitializeErrorState_ShouldHaveDefaultValues() {
        val networkState = NetworkState.Error()

        assertTrue(networkState.isError())
        assertEquals(false, networkState.isAdditional)
    }

    @Test
    fun initializeErrorState_ShouldHaveDefaultValues() {
        val isAdditional = true
        val networkState = NetworkState.Error(isAdditional)

        assertTrue(networkState.isError())
        assertEquals(isAdditional, networkState.isAdditional)
    }
}

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

/**
 * Different states for any network request.
 */
sealed class NetworkState {

    /**
     * Success network state.
     *
     * @param isAdditional Is additional request.
     * @param isEmptyResponse Is the body of response empty.
     */
    data class Success(
        val isAdditional: Boolean = false,
        val isEmptyResponse: Boolean = false
    ) : NetworkState()

    /**
     * Loading network state.
     *
     * @param isAdditional Is additional request.
     */
    data class Loading(
        val isAdditional: Boolean = false
    ) : NetworkState()

    /**
     * Error network state.
     *
     * @param isAdditional Is additional request.
     */
    data class Error(
        val isAdditional: Boolean = false
    ) : NetworkState()

    // ============================================================================================
    //  Public helpers methods
    // ============================================================================================

    /**
     * Check if current network state is [Success].
     *
     * @return True if is success state, otherwise false.
     */
    fun isSuccess() = this is Success

    /**
     * Check if current network state is [Loading].
     *
     * @return True if is loading state, otherwise false.
     */
    fun isLoading() = this is Loading

    /**
     * Check if current network state is [Error].
     *
     * @return True if is error state, otherwise false.
     */
    fun isError() = this is Error
}

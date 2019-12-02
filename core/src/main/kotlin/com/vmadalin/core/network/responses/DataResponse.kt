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

package com.vmadalin.core.network.responses

import com.vmadalin.core.annotations.OpenForTesting

/**
 * Marvel API data network response format.
 *
 * @param offset The requested offset (number of skipped results) of the call.
 * @param limit The requested result limit.
 * @param total The total number of resources available given the current filter set.
 * @param count The total number of results returned by this call.
 * @param results The list of [T] returned by the call.
 */
@OpenForTesting
data class DataResponse<T>(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<T>
)

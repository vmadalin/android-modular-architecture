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

import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class DataResponseTest {

    @Test
    fun createDataResponse_ShouldAddCorrectAttributes() {
        val offset = 10
        val limit = 50
        val total = 1000
        val count = 50
        val results: List<String> = mockk()

        val dataResponse = DataResponse(
            offset = offset,
            limit = limit,
            total = total,
            count = count,
            results = results
        )

        Assert.assertEquals(offset, dataResponse.offset)
        Assert.assertEquals(limit, dataResponse.limit)
        Assert.assertEquals(total, dataResponse.total)
        Assert.assertEquals(count, dataResponse.count)
        Assert.assertEquals(results, dataResponse.results)
    }
}

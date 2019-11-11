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

import org.junit.Assert
import org.junit.Test

class CharacterThumbnailResponseTest {

    @Test
    fun createCharacterThumbnailResponse_ShouldAddCorrectAttributes() {
        val path = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784"
        val extension = "jpg"

        val characterThumbnailResponse = CharacterThumbnailResponse(
            path = path,
            extension = extension
        )

        Assert.assertEquals(path, characterThumbnailResponse.path)
        Assert.assertEquals(extension, characterThumbnailResponse.extension)
    }
}

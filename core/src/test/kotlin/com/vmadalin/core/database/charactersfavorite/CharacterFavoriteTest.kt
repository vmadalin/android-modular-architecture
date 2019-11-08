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

package com.vmadalin.core.database.charactersfavorite

import com.vmadalin.core.database.characterfavorite.CharacterFavorite
import org.junit.Assert.assertEquals
import org.junit.Test

class CharacterFavoriteTest {

    @Test
    fun createCharacterFavorite_ShouldAddCorrectAttributes() {
        val characterId = 0L
        val characterName = "A.I.M"
        val characterImageUrl = "http://i.annihil.us/535fecbbb9784.jpg"

        val characterFavorite = CharacterFavorite(
            id = characterId,
            name = characterName,
            imageUrl = characterImageUrl
        )

        assertEquals(characterId, characterFavorite.id)
        assertEquals(characterName, characterFavorite.name)
        assertEquals(characterImageUrl, characterFavorite.imageUrl)
    }
}

package com.vmadalin.core.database.characterfavorite

import androidx.lifecycle.LiveData
import javax.inject.Inject

class CharacterFavoriteRepository @Inject constructor(
    private val characterFavoriteDao: CharacterFavoriteDao
) {

    suspend fun getCharacterFavorite(characterFavoriteId: Long): CharacterFavorite {
        return characterFavoriteDao.getCharacterFavorite(characterFavoriteId)
    }

    suspend fun getAllCharactersFavorite(): List<CharacterFavorite> {
        return characterFavoriteDao.getAllCharactersFavorite()
    }

    suspend fun deleteAllCharactersFavorite() {
        characterFavoriteDao.deleteAllCharactersFavorite()
    }

    suspend fun deleteCharacterFavoriteById(characterFavoriteId: Long) {
        characterFavoriteDao.deleteCharacterFavoriteById(characterFavoriteId)
    }

    suspend fun deleteCharacterFavorite(character: CharacterFavorite) {
        characterFavoriteDao.deleteCharacterFavorite(character)
    }

    suspend fun insertCharacterFavorite(id: Long, name: String, imageUrl: String) {
        val characterFavorite = CharacterFavorite(
            id = id,
            name = name,
            imageUrl = imageUrl
        )
        characterFavoriteDao.insertCharacterFavorite(characterFavorite)
    }
}

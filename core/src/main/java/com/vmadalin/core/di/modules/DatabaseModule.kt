package com.vmadalin.core.di.modules

import android.content.Context
import com.vmadalin.core.database.MarvelDatabase
import com.vmadalin.core.database.characterfavorite.CharacterFavoriteDao
import com.vmadalin.core.database.characterfavorite.CharacterFavoriteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideCharacterFavoriteDao(context: Context): CharacterFavoriteDao {
        return MarvelDatabase.getInstance(context).characterFavoriteDao()
    }

    @Singleton
    @Provides
    fun provideCharacterFavoriteRepository(
        characterFavoriteDao: CharacterFavoriteDao
    ): CharacterFavoriteRepository {
        return CharacterFavoriteRepository(
            characterFavoriteDao
        )
    }
}

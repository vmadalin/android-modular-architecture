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

package com.vmadalin.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vmadalin.core.BuildConfig
import com.vmadalin.core.database.characterfavorite.CharacterFavorite
import com.vmadalin.core.database.characterfavorite.CharacterFavoriteDao
import com.vmadalin.core.database.migrations.MIGRATION_1_2

@Database(
    entities = [CharacterFavorite::class],
    exportSchema = BuildConfig.MARVEL_DATABASE_EXPORT_SCHEMA,
    version = BuildConfig.MARVEL_DATABASE_VERSION
)
abstract class MarvelDatabase : RoomDatabase() {
    abstract fun characterFavoriteDao(): CharacterFavoriteDao

    companion object {

        @Volatile
        private var instance: MarvelDatabase? = null

        private val dbMigrations by lazy {
            listOf(MIGRATION_1_2)
        }

        fun getInstance(context: Context): MarvelDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): MarvelDatabase {
            return Room.databaseBuilder(
                context,
                MarvelDatabase::class.java,
                BuildConfig.MARVEL_DATABASE_NAME
            ).addMigrations(*dbMigrations.toTypedArray())
                .build()
        }
    }
}

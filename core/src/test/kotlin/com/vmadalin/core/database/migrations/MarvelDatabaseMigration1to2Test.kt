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

package com.vmadalin.core.database.migrations

import androidx.sqlite.db.SupportSQLiteDatabase
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MarvelDatabaseMigration1to2Test {

    @MockK
    lateinit var supportSQLiteDatabase: SupportSQLiteDatabase
    private val migration = MIGRATION_1_2

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun checkMigrationDatabaseVersions() {
        assertEquals(1, migration.startVersion)
        assertEquals(2, migration.endVersion)
    }

    @Test
    fun executeMigrationDatabase() {
        migration.migrate(supportSQLiteDatabase)

        verify(exactly = 0) { supportSQLiteDatabase.beginTransaction() }
    }
}

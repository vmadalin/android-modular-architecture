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

import extensions.getLocalProperty

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

plugins {
    id("common-android-library")
}

android {
    buildTypes.forEach {
        try {
            it.buildConfigField("String", "MARVEL_API_BASE_URL", "\"https://gateway.marvel.com\"")
            it.buildConfigField("String", "MARVEL_API_KEY_PUBLIC", "\"${getLocalProperty("marvel.key.public")}\"")
            it.buildConfigField("String", "MARVEL_API_KEY_PRIVATE", "\"${getLocalProperty("marvel.key.private")}\"")

            it.buildConfigField("boolean", "MARVEL_DATABASE_EXPORT_SCHEMA", "false")
            it.buildConfigField("String", "MARVEL_DATABASE_NAME", "\"characters-db\"")
            it.buildConfigField("int", "MARVEL_DATABASE_VERSION", "1")
        } catch (ignored: Exception) {
            throw InvalidUserDataException("You should define 'marvel.key.public' and 'marvel.key.private' in local.properties. Visit 'https://developer.marvel.com' to obtain them.")
        }
    }
}

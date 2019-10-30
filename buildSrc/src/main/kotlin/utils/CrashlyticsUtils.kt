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

package utils

import org.gradle.api.Project
import java.io.File
import java.lang.Exception

private const val FABRIC_PROPERTIES_FILE_NAME = "app/fabric.properties"
private const val FABRIC_API_KEY_PROPERTY_NAME = "fabric.key"
private const val FABRIC_API_SECRET_PROPERTY_NAME = "fabric.secret"

/**
 * Util to create `fabric.properties` file using the declared values on
 * `$projectRoot/local.properties` by the following key names: `fabric.key` and `fabric.secret`.
 *
 * @param project the project reference
 */
fun createFabricProperties(project: Project) {
    try {
        val fabricPropertiesFile = File(FABRIC_PROPERTIES_FILE_NAME)
        val fabricApiKey = getLocalProperty(FABRIC_API_KEY_PROPERTY_NAME, project)
        val fabricApiSecret = getLocalProperty(FABRIC_API_SECRET_PROPERTY_NAME, project)

        if (!fabricPropertiesFile.exists()) {
            fabricPropertiesFile
                .printWriter()
                .use { output ->
                    output.println("apiKey=$fabricApiKey")
                    output.println("apiSecret=$fabricApiSecret")
                }
        }
    } catch (e: Exception) {
        throw Exception("You should define 'fabric.key' and 'fabric.secret' in local.properties. Visit 'https://fabric.io' to obtain them.")
    }
}

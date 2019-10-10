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

import org.gradle.api.Project
import java.io.File
import java.lang.Exception

private const val FABRIC_PROPERTIES_FILE_NAME = "app/fabric.properties"
private const val FABRIC_API_KEY_PROPERTY_NAME = "fabric.key"
private const val FABRIC_API_SECRET_PROPERTY_NAME = "fabric.secret"

fun createFabricProperties(project: Project) {
    val fabricPropertiesFile = File(FABRIC_PROPERTIES_FILE_NAME)
    val fabricApiKey = getLocalProperty(FABRIC_API_KEY_PROPERTY_NAME, project)
    val fabricApiSecret = getLocalProperty(FABRIC_API_SECRET_PROPERTY_NAME, project)

    if (!fabricPropertiesFile.exists()) {
        if (fabricApiKey.isEmpty() || fabricApiSecret.isEmpty()) {
            throw Exception()
        } else {
            fabricPropertiesFile.printWriter().use { output ->
                output.println("apiKey=$fabricApiKey")
                output.println("apiSecret=$fabricApiSecret")
            }
        }
    }
}

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

package dependencies

/**
 * Project test dependencies, makes it easy to include external binaries or
 * other library modules to build.
 */
object TestDependencies {
    const val JUNIT = "junit:junit:${BuildDependenciesVersions.JUNIT}"
    const val MOCKK = "io.mockk:mockk:${BuildDependenciesVersions.MOCKK}"
    const val ASSERTJ = "org.assertj:assertj-core:${BuildDependenciesVersions.ASSERTJ}"
    const val ROBOELECTRIC = "org.robolectric:robolectric:${BuildDependenciesVersions.ROBOELECTRIC}"
    const val ROOM = "androidx.room:room-testing:${BuildDependenciesVersions.ROOM}"
    const val EXT = "androidx.test.ext:junit:${BuildDependenciesVersions.EXT}"
    const val CORE = "androidx.test:core:${BuildDependenciesVersions.TEST}"
    const val RUNNER = "androidx.test:runner:${BuildDependenciesVersions.TEST}"
    const val RULES = "androidx.test:rules:${BuildDependenciesVersions.TEST}"
    const val COROUTINES_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${BuildDependenciesVersions.COROUTINES}"
    const val ARCH_CORE = "androidx.arch.core:core-testing:${BuildDependenciesVersions.ARCH_CORE}"
    const val FRAGMENT_TEST = "androidx.fragment:fragment-testing:${BuildDependenciesVersions.FRAGMENT_TEST}"
    const val MOCK_WEB_SERVER = "com.squareup.okhttp3:mockwebserver:${BuildDependenciesVersions.MOCK_WEB_SERVER}"
}

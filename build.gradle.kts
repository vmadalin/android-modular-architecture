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

plugins.apply("git-hooks-config")
plugins.apply("update-dependencies-config")

allprojects {
    repositories.applyDefault()

    plugins.apply("detekt-config")
    plugins.apply("dokka-config")
    plugins.apply("ktlint-config")
    plugins.apply("spotless-config")
}

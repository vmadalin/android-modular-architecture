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

package com.vmadalin.core.mapper

/**
 * Helper class to transforms a specific input to desired object output, implementing for that
 * all operations required to transform.
 */
interface Mapper<F, T> {

    /**
     * Mapping object.
     *
     * @param from Initial object to from mapping.
     * @return An Object containing the results of applying the transformation.
     */
    suspend fun map(from: F): T
}

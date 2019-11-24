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

package com.vmadalin.dynamicfeatures.characterslist.ui.list.adapter

import org.junit.Assert
import org.junit.Test

class CharactersListAdapterStateTest {

    lateinit var state: CharactersListAdapterState

    @Test
    fun setStateAsAdded_ShouldBeSettled() {
        state = CharactersListAdapterState.Added

        Assert.assertTrue(state.hasExtraRow)
        Assert.assertTrue(state.isAdded())

        Assert.assertFalse(state.isAddLoading())
        Assert.assertFalse(state.isAddError())
        Assert.assertFalse(state.isNoMore())
    }

    @Test
    fun setStateAsAddLoading_ShouldBeSettled() {
        state = CharactersListAdapterState.AddLoading

        Assert.assertTrue(state.hasExtraRow)
        Assert.assertTrue(state.isAddLoading())

        Assert.assertFalse(state.isAdded())
        Assert.assertFalse(state.isAddError())
        Assert.assertFalse(state.isNoMore())
    }

    @Test
    fun setStateAsAddError_ShouldBeSettled() {
        state = CharactersListAdapterState.AddError

        Assert.assertTrue(state.hasExtraRow)
        Assert.assertTrue(state.isAddError())

        Assert.assertFalse(state.isAdded())
        Assert.assertFalse(state.isAddLoading())
        Assert.assertFalse(state.isNoMore())
    }

    @Test
    fun setStateAsNoMore_ShouldBeSettled() {
        state = CharactersListAdapterState.NoMore

        Assert.assertFalse(state.hasExtraRow)
        Assert.assertTrue(state.isNoMore())

        Assert.assertFalse(state.isAdded())
        Assert.assertFalse(state.isAddLoading())
        Assert.assertFalse(state.isAddError())
    }
}

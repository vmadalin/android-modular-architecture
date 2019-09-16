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

package com.vmadalin.dynamicfeatures.characterslist.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vmadalin.dynamicfeatures.characterslist.models.CharacterItem
import javax.inject.Inject

class CharactersListViewModel
@Inject constructor() :
    ViewModel() {

    private val _charactersList = MutableLiveData<List<CharacterItem>>()
    val charactersList: LiveData<List<CharacterItem>>
        get() = _charactersList

    fun loadCharactersList() {
    }

    override fun onCleared() {
        super.onCleared()
        // subscription.dispose()
    }
}

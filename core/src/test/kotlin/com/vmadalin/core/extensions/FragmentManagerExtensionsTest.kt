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

package com.vmadalin.core.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.launchFragmentInContainer
import com.vmadalin.core.base.BaseRobolectricTest
import com.vmadalin.core.utils.TestFragment
import org.junit.Assert.assertFalse
import org.junit.Test

class FragmentManagerExtensionsTest : BaseRobolectricTest() {

    @Test
    fun havingEmptyBackStackFragmentManager_NotExistStackName_ShouldNotFound() {
        val fragmentScenario = launchFragmentInContainer<TestFragment>()
        fragmentScenario.onFragment {
            val fragmentManager = it.childFragmentManager
            val noExistBackStackName = "No Exist Stack Name"
            val existOnBackStack = fragmentManager.isOnBackStack(noExistBackStackName)
            assertFalse(existOnBackStack)
        }
    }

    @Test
    fun havingAddedBackStackFragmentManager_ExistStackName_ShouldFound() {
        // TODO
//        val existBackStackName = "Exist Stack Name"
//        val fragmentScenario = launchFragmentInContainer<TestFragment>()
//        fragmentScenario.onFragment {
//            val fragmentManager = it.childFragmentManager
//            fragmentManager.beginTransaction().add(TestFragment(), "tst").commitNow()
//            fragmentManager.beginTransaction().add(TestFragment(), existBackStackName).commitNow()
//
//            val existOnBackStack = fragmentManager.isOnBackStack(existBackStackName)
//            assertTrue(existOnBackStack)
//        }
    }

    @Test
    fun havingAddedBackStackFragmentManager_NotExistStackName_ShouldNotFound() {
        val fragmentScenario = launchFragmentInContainer<TestFragment>()
        fragmentScenario.onFragment {
            val fragmentManager = it.childFragmentManager
            val noExistBackStackName = "No Exist Stack Name"
            fragmentManager.beginTransaction().add(Fragment(), "test").commitNow()

            val existOnBackStack = fragmentManager.isOnBackStack(noExistBackStackName)
            assertFalse(existOnBackStack)
        }
    }
}

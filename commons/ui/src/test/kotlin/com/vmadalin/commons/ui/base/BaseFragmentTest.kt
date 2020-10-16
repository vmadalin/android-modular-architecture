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

package com.vmadalin.commons.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.test.core.app.ActivityScenario
import com.vmadalin.libraries.testutils.TestCompatActivity
import com.vmadalin.libraries.testutils.TestFragmentActivity
import com.vmadalin.libraries.testutils.robolectric.TestRobolectric
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.SpyK
import io.mockk.mockk
import io.mockk.verify
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class BaseFragmentTest : TestRobolectric() {

    @SpyK
    var baseFragment = TestBaseFragment()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun initDependencyInjection_OnAttach_ShouldInvoke() {
        baseFragment.onAttach(context)

        verify { baseFragment.onInitDependencyInjection() }
    }

    @Test
    fun initDataBiding_OnViewCreated_ShouldInvoke() {
        val view = mockk<View>()
        val savedInstanceState = mockk<Bundle>()
        baseFragment.onViewCreated(view, savedInstanceState)

        verify { baseFragment.onInitDataBinding() }
    }

    @Test
    fun requireCompactActivity_FromCompactActivity_ShouldReturnIt() {
        val scenario = ActivityScenario.launch(TestCompatActivity::class.java)
        scenario.onActivity {
            it.supportFragmentManager
                .beginTransaction()
                .add(android.R.id.content, baseFragment)
                .commitNow()

            val compatActivity = baseFragment.requireCompatActivity()
            assertNotNull(compatActivity)
            assertThat(compatActivity, instanceOf(AppCompatActivity::class.java))
        }
    }

    @Test(expected = TypeCastException::class)
    fun requireCompactActivity_FromActivity_ShouldNotReturnIt() {
        val scenario = ActivityScenario.launch(TestFragmentActivity::class.java)
        scenario.onActivity {
            it.supportFragmentManager
                .beginTransaction()
                .add(android.R.id.content, baseFragment)
                .commitNow()

            baseFragment.requireCompatActivity()
        }
    }

    class TestBaseFragment : BaseFragment<ViewDataBinding, ViewModel>(
        layoutId = 0
    ) {
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? = null

        override fun onInitDependencyInjection() {}

        override fun onInitDataBinding() {}
    }
}

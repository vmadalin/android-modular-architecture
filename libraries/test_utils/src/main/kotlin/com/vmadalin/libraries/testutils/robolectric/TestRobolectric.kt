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

package com.vmadalin.libraries.testutils.robolectric

import android.app.Application
import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(
    manifest = "AndroidManifest.xml",
    application = TestRobolectric.ApplicationStub::class,
    sdk = [Build.VERSION_CODES.LOLLIPOP]
)
open class TestRobolectric {

    protected val application: Application by lazy {
        ApplicationProvider.getApplicationContext<ApplicationStub>()
    }
    protected val context: Context by lazy {
        application
    }

    internal class ApplicationStub : Application()
}

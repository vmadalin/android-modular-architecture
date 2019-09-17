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

package com.vmadalin.core.ui.bindings

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.vmadalin.core.extensions.setGone
import com.vmadalin.core.extensions.setInvisible

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    Glide
        .with(imageView.context)
        .load(url)
        .transition(withCrossFade())
        .into(imageView)
}

@BindingAdapter("visibleOrGone")
fun setGone(view: View, hide: Boolean) = view.setGone(hide)

@BindingAdapter("visible")
fun setInvisible(view: View, hide: Boolean) = view.setInvisible(hide)

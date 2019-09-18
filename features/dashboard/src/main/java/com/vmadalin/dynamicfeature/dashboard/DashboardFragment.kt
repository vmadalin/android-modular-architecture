package com.vmadalin.dynamicfeature.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vmadalin.core.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onInitDependencyInjection() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

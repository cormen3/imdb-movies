package hossein.gheisary.imdbfilms.base

import android.app.Dialog
import android.content.Context
import androidx.fragment.app.FragmentActivity
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import hossein.gheisary.imdbfilms.R

abstract class BaseFragment : DaggerFragment() {
    lateinit var mActivity: FragmentActivity
    lateinit var loadingDialog: Dialog

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        initLoading()
        mActivity = activity as FragmentActivity
    }

    private fun initLoading() {
        loadingDialog = Dialog(activity, R.style.AppThemeDialog)
        loadingDialog.setContentView(R.layout.loading_view)
        loadingDialog.setCancelable(false)
    }

    fun setLoading(isLoading: Boolean) {
        when(isLoading){
            true -> loadingDialog.show()
            false -> loadingDialog.dismiss()
        }
    }

}
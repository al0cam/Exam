package cz.mendelu.fbe.mytodo.architecture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import org.koin.android.viewmodel.ext.android.getViewModel
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
abstract class BaseFragment<B : ViewBinding,
        VM : ViewModel>(viewModelClass: KClass<VM>) : Fragment(){
    protected abstract val bindingInflater: (LayoutInflater) -> B
    private var baseBinding: ViewBinding? = null
    protected val binding: B
        get() = baseBinding as B
    val viewModel: VM by lazy { getViewModel(null, viewModelClass) }
    abstract fun initViews()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        baseBinding = bindingInflater(inflater)
        initViews()
        return baseBinding!!.root
    }
    override fun onDestroy() {
        super.onDestroy()
        baseBinding = null
    }
}
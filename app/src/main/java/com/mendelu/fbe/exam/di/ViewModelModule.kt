package cz.mendelu.fbe.mytodo.di

import com.mendelu.fbe.gettingdone.AddRideViewModel
import com.mendelu.fbe.gettingdone.ListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    
    viewModel{
        AddRideViewModel(get())
    }

    viewModel{
        ListViewModel(get())
    }

}
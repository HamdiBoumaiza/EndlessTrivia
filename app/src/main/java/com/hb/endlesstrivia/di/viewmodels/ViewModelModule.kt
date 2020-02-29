package com.hb.endlesstrivia.di.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hb.endlesstrivia.ui.details.DetailsViewModel
import com.hb.endlesstrivia.ui.filter_trivia.FilterViewModel
import com.hb.endlesstrivia.ui.list_trivia.ListTriviasViewModel
import com.hb.endlesstrivia.ui.onbaording.OnboardingViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


/**
 * Module used to define the connection between the framework's [ViewModelProvider.Factory] and
 * our own implementation: [DaggerViewModelFactory].
 */
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ListTriviasViewModel::class)
    abstract fun bindMainActivtyVM(mainActivityViewModel: ListTriviasViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    abstract fun bindDetailsVM(detailsViewModel: DetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FilterViewModel::class)
    abstract fun bindFilterVM(filterViewModel: FilterViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OnboardingViewModel::class)
    abstract fun bindOnboardingVM(onboardingViewModel: OnboardingViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory

}
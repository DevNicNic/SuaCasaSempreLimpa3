package com.nicnicdev.suacasasemprelimpa03.di

import com.nicnicdev.suacasasemprelimpa03.data.RegistrationRepository
import com.nicnicdev.suacasasemprelimpa03.domain.useCase.ValidadeRegistrationUseCase
import com.nicnicdev.suacasasemprelimpa03.ui.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    viewModel { RegisterViewModel(get(), get()) }

    single { ValidadeRegistrationUseCase() }

    single { RegistrationRepository() }


}
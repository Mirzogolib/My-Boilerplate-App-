package com.example.myboilerplateapp.presentation.auth

import androidx.lifecycle.MutableLiveData
import com.example.myboilerplateapp.extensions.toUserMessage
import com.example.myboilerplateapp.model.interactor.AuthInteractor
import com.example.myboilerplateapp.model.system.ResourceManager
import com.example.myboilerplateapp.presentation.base.BaseViewModel
import com.example.myboilerplateapp.presentation.base.SingleLiveEvent
import com.example.myboilerplateapp.router.MainFlow
import kotlinx.coroutines.launch
import ru.terrakok.cicerone.Router
import javax.inject.Inject
import com.example.myboilerplateapp.entity.Result

class AuthViewModel @Inject constructor(
    private val router: Router,
    private val authInteractor: AuthInteractor,
    private val resourceManager: ResourceManager
) : BaseViewModel() {
    val toast = SingleLiveEvent<String>()
    val progress = MutableLiveData<Boolean>()

    fun signIn(login: String, email: String, password: String) {
        progress.postValue(true)
        viewModelScope.launch {
            when (val tokenResult = authInteractor.signIn(login, email, password)) {
                is Result.Success -> router.newRootChain(MainFlow)
                is Result.Error -> {
                    toast.value = tokenResult.exception.toUserMessage(resourceManager)
                }


//                is Result.Error -> router.newRootChain(MainFlow)
            }
            progress.postValue(false)
        }
    }

    fun onBackPressed() {
        router.exit()
    }

    fun registerClicked() {
//        router.newRootScreen(Register)

    }

    fun resetClicked() {
//        router.newRootScreen(ResetPassword)
    }
//    fun finish(){
//        router.newRootChain(AuthFlow)
//    }
}
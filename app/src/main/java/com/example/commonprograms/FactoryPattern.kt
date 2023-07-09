package com.example.commonprograms

import java.io.InvalidClassException

/* ------ THEORY -------
Factory design pattern belongs to the category of Creational Design Patterns.
Here, the objects are created without exposing the logic of creation to the client.
The objects refer to the common interface.
* */

fun main() {
    // We are hiding login to create instance of view model
    val factory = ViewModelFactory()
    val loginViewModel by lazy { factory.create(LoginVM) }
    val registerViewModel by lazy { factory.create(RegVM) }
    val logoutViewModel by lazy { factory.create(LogoutVM) }
    val test by lazy { factory.create(Test) }

    print(loginViewModel.commonViewModelFunction())
    print(registerViewModel.commonViewModelFunction())
    print(logoutViewModel.commonViewModelFunction())
    //print(test.commonViewModelFunction())    // Try uncommenting this line and see what happens
}

class ViewModelFactory {
    fun create(typeArgs: ViewModels) : MyViewModel {
        return when(typeArgs) {
            LoginVM -> {
                LoginViewModel()
            }
            LogoutVM -> {
                LogoutViewModel()
            }
            RegVM -> {
                RegisterViewModel()
            }
            else -> {
                throw InvalidClassException("Invalid ViewModel class")
            }
        }
    }
}

interface MyViewModel {
    fun commonViewModelFunction()
}

class LoginViewModel: MyViewModel {
    override fun commonViewModelFunction(){
        println("Login observing")
    }
}

class RegisterViewModel: MyViewModel{
    override fun commonViewModelFunction() {
        println("Register observing")
    }
}

class LogoutViewModel: MyViewModel{
    override fun commonViewModelFunction() {
        println("Logout observing")
    }
}

sealed class ViewModels
object LoginVM: ViewModels()
object RegVM: ViewModels()
object LogoutVM: ViewModels()
object Test: ViewModels()
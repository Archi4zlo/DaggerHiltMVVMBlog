package com.example.daggerhiltmvvmblog.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MainFragmentFactory
@Inject constructor(
    private val someString: String
): FragmentFactory(){
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            MainFragment::class.java.name ->{
                val fragment = MainFragment(someString)
                fragment
            }
            else -> super.instantiate(classLoader, className)
        }
    }
}
package com.project.khajit_app

import android.content.Intent
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.project.khajit_app.activity.HelperFunctions
import com.project.khajit_app.activity.HomeFeedPageActivity
import com.project.khajit_app.activity.SignUpPageTraderActivity
import com.project.khajit_app.activity.ui.profile.UserProfile
import com.project.khajit_app.api.RetrofitClient
import com.project.khajit_app.data.models.*
import com.project.khajit_app.global.User
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    private var helper = HelperFunctions()
    @Test
    fun validIbanTest() {
        assertEquals(true, helper.validIban("1234123412341234"))
        assertEquals(false, helper.validIban("1234"))
    }

    @Test
    fun emailVerificationCheck() {
        assertEquals(true, helper.isEmailValid("emir@gmail.com"))
        assertEquals(false, helper.isEmailValid("emir.com"))
        assertEquals(false, helper.isEmailValid(""))
    }

    @Test
    fun articleVerificationCheck() {
        assertEquals(true, helper.isTitleValid(" New Title"))
        assertEquals(false, helper.isTitleValid(""))
    }

    @Test
    fun commentVerificationCheck() {
        assertEquals(true, helper.isCommentValid("This is a new comment!"))
        assertEquals(false, helper.isCommentValid(""))
    }

    @Test
    fun annotationVerificationCheck() {
        assertEquals(true, helper.isAnnotationValid("annotation 1"))
        assertEquals(false, helper.isAnnotationValid(""))
        assertEquals(true, helper.isAnnotationStartEndValid(3,9))
        assertEquals(false, helper.isAnnotationStartEndValid(0,0))
        assertEquals(false, helper.isAnnotationStartEndValid(8,6))

    }

}

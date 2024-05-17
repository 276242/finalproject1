package com.example.maddbtestapp2.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.maddbtestapp2.BaseActivity
import com.example.maddbtestapp2.R
import com.example.maddbtestapp2.user.UserActivity
import com.google.firebase.auth.FirebaseAuth

/**
 * LoginActivity is responsible for handling user login.
 * It extends BaseActivity to inherit common functionality.
 */
class LoginActivity : BaseActivity() {

    private var inputEmailL: EditText? = null
    private var inputPasswordL: EditText? = null
    private var loginButton: Button? = null

    /**
     * Called when the activity is starting.
     * This is where most initialization should go.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val registerTextView = findViewById<TextView>(R.id.registerTextView)
        registerTextView.setOnClickListener { goToRegister(it) }

        inputEmailL = findViewById(R.id.inputEmailL)
        inputPasswordL = findViewById(R.id.inputPasswordL)
        loginButton = findViewById(R.id.loginButton)

        loginButton?.setOnClickListener {
            loginUser()
        }
    }

    /**
     * Navigates to the RegisterActivity.
     */
    private fun goToRegister(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }

    /**
     * Validates the login details entered by the user.
     * @return Boolean indicating whether the login details are valid.
     */
    private fun validateLoginDetails(): Boolean {
        val email = inputEmailL?.text?.toString()?.trim { it <= ' ' }
        val password = inputPasswordL?.text?.toString()?.trim { it <= ' ' }

        if (email.isNullOrEmpty()) {
            showErrorToast(resources.getString(R.string.err_msg_enter_email))
            return false
        }

        if (password.isNullOrEmpty()) {
            showErrorToast(resources.getString(R.string.err_msg_enter_password))
            return false
        }

        return true
    }

    /**
     * Logs in the user using Firebase Authentication.
     */
    private fun loginUser() {
        if (validateLoginDetails()) {
            val email = inputEmailL?.text.toString().trim { it <= ' ' }
            val password = inputPasswordL?.text.toString().trim { it <= ' ' }

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        showErrorToast("Login successful!")

                        val intent = Intent(this, UserActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        showErrorSnackBar(task.exception!!.message.toString(), true)
                    }
                }
        }
    }
}
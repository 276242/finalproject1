/**
 * LoginActivity is a class that provides functionality for user login.
 *
 * This class provides methods to validate login details and log in the user.
 *
 * @property inputEmailL The EditText field for the email input.
 * @property inputPasswordL The EditText field for the password input.
 * @property loginButton The Button for the login action.
 */
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

class LoginActivity : BaseActivity() {

    private var inputEmailL: EditText? = null
    private var inputPasswordL: EditText? = null
    private var loginButton: Button? = null

    /**
     * Initializes the activity, sets the content view and initializes the UI elements.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). Note: Otherwise it is null.
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
     *
     * @param view The view that was clicked.
     */
    private fun goToRegister(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }

    /**
     * Validates the login details entered by the user.
     *
     * @return True if the login details are valid, false otherwise.
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
     * Logs in the user using the entered login details.
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


package com.example.maddbtestapp2

import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

/**
 * A base activity class providing common functionalities for activities in the application.
 */
open class BaseActivity : AppCompatActivity() {

    /**
     * Displays a Snackbar with an error message.
     *
     * @param message The error message to display.
     * @param success True if the operation was successful, false otherwise.
     */
    fun showErrorSnackBar(message: String, success: Boolean) {
        val snackBar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT)

        if (success) {
            snackBar.setBackgroundTint(ContextCompat.getColor(this@BaseActivity, R.color.snackBarSuccessful))
        } else {
            snackBar.setBackgroundTint(ContextCompat.getColor(this@BaseActivity, R.color.snackBarError))
        }

        snackBar.show()
    }

    /**
     * Displays a Toast with an error message.
     *
     * @param message The error message to display.
     */
    fun showErrorToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    /**
     * Displays a Toast with a success message.
     *
     * @param message The success message to display.
     */
    fun showSuccessToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

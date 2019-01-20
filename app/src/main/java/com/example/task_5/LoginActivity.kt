@file:Suppress("DEPRECATION")

package com.example.task_5

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.support.v7.app.AppCompatActivity
import android.app.LoaderManager.LoaderCallbacks
import android.content.Loader
import android.database.Cursor
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoaderCallbacks<Cursor> {
    private var mAuthTask: UserLoginTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Set up the login form.
        password.setOnEditorActionListener(TextView.OnEditorActionListener { _, id, _ ->
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptLogin()
                return@OnEditorActionListener true
            }
            false
        })

        sign_in_button.setOnClickListener { attemptLogin() }
    }

    private fun attemptLogin() {
        if (mAuthTask != null) {
            return
        }

        // Reset errors.
        login.error = null
        password.error = null

        // Store values at the time of the login attempt.
        val loginStr = login.text.toString()
        val passwordStr = password.text.toString()

        var cancel = false
        var focusView: View? = null

        if (TextUtils.isEmpty(loginStr) && TextUtils.isEmpty(passwordStr)) {
            Snackbar.make(password, R.string.error_field_required, Snackbar.LENGTH_SHORT).show()
            focusView = login
            cancel = true
        } else {
            // Check for a valid password.
            if (TextUtils.isEmpty(passwordStr)) {
                Snackbar.make(password, R.string.error_field_required_pass, Snackbar.LENGTH_SHORT).show()
                focusView = password
                cancel = true
            } else if (!isPasswordValid(passwordStr)) {
                Snackbar.make(password, R.string.error_invalid_password, Snackbar.LENGTH_SHORT).show()
                focusView = password
                cancel = true
            }

            // Check for a valid login address.
            if (TextUtils.isEmpty(loginStr)) {
                Snackbar.make(login, R.string.error_field_required_log, Snackbar.LENGTH_SHORT).show()
                focusView = login
                cancel = true
            } else if (!isLoginValid(loginStr)) {
                focusView = login
                cancel = true
            }
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView?.requestFocus()
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true)
            mAuthTask = UserLoginTask(loginStr, passwordStr)
            mAuthTask!!.execute(null as Void?)
        }
    }

    private fun isLoginValid(email: String): Boolean {
        //TODO: Replace this with your own logic
        return email.contains("")
    }

    private fun isPasswordValid(password: String): Boolean {
        //TODO: Replace this with your own logic
        return password.length >= 4
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private fun showProgress(show: Boolean) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

            scroll_form.visibility = if (show) View.GONE else View.VISIBLE
            scroll_form.animate()
                .setDuration(shortAnimTime)
                .alpha((if (show) 0 else 1).toFloat())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        scroll_form.visibility = if (show) View.GONE else View.VISIBLE
                    }
                })

            login_progress.visibility = if (show) View.VISIBLE else View.GONE
            login_progress.animate()
                .setDuration(shortAnimTime)
                .alpha((if (show) 1 else 0).toFloat())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        login_progress.visibility = if (show) View.VISIBLE else View.GONE
                    }
                })
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            login_progress.visibility = if (show) View.VISIBLE else View.GONE
            scroll_form.visibility = if (show) View.GONE else View.VISIBLE
        }
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLoadFinished(cursorLoader: Loader<Cursor>, cursor: Cursor) {
    }

    override fun onLoaderReset(cursorLoader: Loader<Cursor>) {
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    inner class UserLoginTask internal constructor(private val mEmail: String, private val mPassword: String) :
        AsyncTask<Void, Void, Boolean>() {

        override fun doInBackground(vararg params: Void): Boolean? {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000)
            } catch (e: InterruptedException) {
                return false
            }

            return DUMMY_CREDENTIALS
                .map { it.split(":") }
                .firstOrNull { it[0] == mEmail }
                ?.let {
                    // Account exists, return true if the password matches.
                    it[1] == mPassword
                }
                ?: false
        }

        override fun onPostExecute(success: Boolean?) {
            mAuthTask = null
            showProgress(false)

            if (success!!) {
                //finish()
            } else {
                Snackbar.make(password, R.string.error_incorrect_info, Snackbar.LENGTH_SHORT).show()
                password.requestFocus()
            }
        }

        override fun onCancelled() {
            mAuthTask = null
            showProgress(false)
        }
    }

    companion object {
        /**
         * A dummy authentication store containing known user names and passwords.
         * TODO: remove after connecting to a real authentication system.
         */
        private val DUMMY_CREDENTIALS = arrayOf("test:test")
    }
}

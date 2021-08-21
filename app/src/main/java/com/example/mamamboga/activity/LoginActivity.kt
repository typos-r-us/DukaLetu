package com.example.mamamboga.activity

import android.app.ProgressDialog
import android.content.Intent
import com.example.mamamboga.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {
    private val TAG = "LoginActivity"
    //global variables
    private var email: String? = null
    private var password: String? = null
    //UI elements
    private var etEmail: EditText? = null
    private var etPassword: EditText? = null
    private var btnLogin: Button? = null
    private var btnCreateAccount: Button? = null
    private var myProgressBar: ProgressDialog? = null
    //Firebase references
    private var myAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Switch to register screen
        val intentRegister = Intent(this, RegisterActivity::class.java)
        val registerText = findViewById<TextView>(R.id.tv_register)
        registerText.setOnClickListener(){
            startActivity(intentRegister)
        }
        //Login Button Logic

        // end Login Button Logic
    }
    private fun initialise() {
        etEmail = findViewById<View>(R.id.et_email) as EditText
        etPassword = findViewById<View>(R.id.et_password) as EditText
        btnLogin = findViewById(R.id.btn_login)
        btnCreateAccount = findViewById<View>(R.id.btn_register) as Button
        myProgressBar = ProgressDialog(this)
        myAuth = FirebaseAuth.getInstance()
        btnCreateAccount!!
            .setOnClickListener { startActivity(Intent(this@LoginActivity,
                RegisterActivity::class.java)) }
        btnLogin!!.setOnClickListener {
            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)

            loginUser()
        }
    }
    private fun loginUser() {
        email = etEmail?.text.toString()
        password = etPassword?.text.toString()

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {

            myProgressBar!!.setMessage("Registering User...")
            myProgressBar!!.show()

            Log.d(TAG, "Logging in user.")

            myAuth!!.signInWithEmailAndPassword(email!!, password!!)
                .addOnCompleteListener(this) { task ->

                    myProgressBar!!.hide()

                    if (task.isSuccessful) {
                        // Sign in success, update UI with signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        updateUI()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.e(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(this@LoginActivity, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            Toast.makeText(this, "Enter all details", Toast.LENGTH_SHORT).show()
        }
    }
    private fun updateUI() {
        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}
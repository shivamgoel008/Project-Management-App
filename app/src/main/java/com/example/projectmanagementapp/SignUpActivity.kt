package com.example.projectmanagementapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.et_email

class SignUpActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        setActionBar()
    }

    private fun setActionBar() {
        setSupportActionBar(toolbar_sign_up_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back)
        }
        toolbar_sign_up_activity.setNavigationOnClickListener {
            onBackPressed()
        }

        btn_sign_up.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val name: String = et_name.text.toString().trim { it <= ' ' }
        val email: String = et_email.text.toString().trim { it <= ' ' }
        val password: String = et_password_sign_up.text.toString().trim { it <= ' ' }

        if(validateFrom(name,email,password)){
            Toast.makeText(this, "Now we can register a new user. ",Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateFrom(name: String, email: String, password: String): Boolean {

        return when {
            TextUtils.isEmpty(name) -> {
                showErrorSnackBar("Please Enter a Name")
                false

            }

            TextUtils.isEmpty(email) -> {
                showErrorSnackBar("Please Enter a Name")
                false
            }
            TextUtils.isEmpty(password) -> {
                showErrorSnackBar("Please Enter a Name")
                false
            }
            else -> {
                true
            }
        }
    }

}

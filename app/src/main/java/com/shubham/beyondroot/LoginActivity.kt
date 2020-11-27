package com.shubham.beyondroot

import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var Name: String
    private lateinit var Password: String
    private lateinit var edtName: EditText
    private lateinit var  edtPassword: EditText
    private lateinit var msg: TextView

    var myDb = DatabaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        edtName = findViewById(R.id.LoginName)
        edtPassword = findViewById(R.id.LoginPassword)
        msg = findViewById(R.id.msg)

        Loginbutton.setOnClickListener(View.OnClickListener {
            Name = edtName.text.toString()
            Password = edtPassword.text.toString()
            if (TextUtils.isEmpty(Name)) {
                edtName.error = "Please Enter Name!"
                return@OnClickListener
            }
            if (TextUtils.isEmpty(Password)) {
                edtPassword.error = "Please Enter Password!"
                return@OnClickListener
            }

            val Login = myDb.checkUserLogin(Name, Password)

            if (Login) {
                val i = Intent(applicationContext, HomeScreen::class.java)
                startActivity(i)
                edtName.setText("")
                edtPassword.setText("")
                msg.text = ""
            } else {
                msg.text = "User not exist please register!"
                msg.setTextColor(Color.RED)
            }
        })

        Loginbuttonintent.setOnClickListener(View.OnClickListener {
            val i = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(i)
            edtName.setText("")
            edtPassword.setText("")
            msg.text = ""
        })
    }
}
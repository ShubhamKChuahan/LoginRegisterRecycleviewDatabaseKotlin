package com.shubham.beyondroot

import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity(), OnItemSelectedListener {

    private lateinit var edtName: EditText;
    private lateinit var edtEmail: EditText;
    private lateinit var edtPhone: EditText;
    private lateinit var edtPassword: EditText;
    private lateinit var msg: TextView;

    private lateinit var radioGenderGroup: RadioGroup;
    lateinit var radioGenderButton: RadioButton

    var Name: String? = null
    var Email: String? = null
    var Phone: String? = null
    var Password: String? = null
    var Gender: String? = null
    var City: String? = null

    var myDb = DatabaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        edtName = findViewById(R.id.name)
        edtEmail = findViewById(R.id.email)
        edtPhone = findViewById(R.id.phone)
        edtPassword = findViewById(R.id.password)

        radioGenderGroup = findViewById(R.id.radioGroup)


        msg = findViewById(R.id.enrollMsg)

        val spnCity = findViewById<Spinner>(R.id.city)

        spnCity!!.onItemSelectedListener = this@RegisterActivity

        val categories: MutableList<String> = ArrayList()
        categories.add("Ahmedabad")
        categories.add("Chandigarh")
        categories.add("Delhi")
        categories.add("Ghaziabad")
        categories.add("Gurugram")
        categories.add("Noida")
        categories.add("Mohali")

        val dataAdapter = ArrayAdapter(this, R.layout.spinner_list, categories)
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnCity!!.adapter = dataAdapter

        submit.setOnClickListener(View.OnClickListener {
            Name = edtName.getText().toString()
            Email = edtEmail.getText().toString().trim { it <= ' ' }
            Phone = edtPhone.getText().toString()
            Password = edtPassword.getText().toString()
            val selectedOption: Int = radioGroup!!.checkedRadioButtonId

            City = spnCity!!.selectedItem.toString()

            if (TextUtils.isEmpty(Name)) {
                edtName.setError("Name can not be empty!")
                return@OnClickListener
            }
            if (TextUtils.isEmpty(Email)) {
                edtEmail.setError("Email can not be empty!")
                return@OnClickListener
            }
            if (!isEmailValid(Email!!)) {
                edtEmail.setError("Invalid email")
                return@OnClickListener
            }
            if (TextUtils.isEmpty(Phone)) {
                edtPhone.setError("Phone can not be empty!")
                return@OnClickListener
            }
            if (TextUtils.isEmpty(Password)) {
                edtPassword.setError("Password can not be empty!")
                return@OnClickListener
            }
            if (selectedOption == -1){
                msg.text = "Select gender first"
                msg.setTextColor(Color.RED)
                return@OnClickListener
            }else{
                radioGenderButton = findViewById(selectedOption)
                Gender = radioGenderButton.getText().toString()
            }



            if (Name != null || Email != null || Phone != null || Password != null || Gender != null || City != null) {
                val Login = myDb.checkUserLogin(Name!!, Password!!)
                if (Login) {
                    msg.setText("User name exists!")
                    msg.setTextColor(Color.RED)
                } else {
                    myDb.insertData(Name, Email, Phone, Gender, City, Password)
                    msg.setText("User registered")
                    msg.setTextColor(Color.GREEN)
                    Toast.makeText(this@RegisterActivity, "User enrolled", Toast.LENGTH_LONG).show()
                    edtName.setText("")
                    edtEmail.setText("")
                    edtPassword.setText("")
                    edtPhone.setText("")
                    onBackPressed()
                }
            }
        })
    }
    fun isEmailValid(email: String): Boolean {
        return Pattern.compile(
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()
    }
    override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {}
    override fun onNothingSelected(parent: AdapterView<*>?) {}
}
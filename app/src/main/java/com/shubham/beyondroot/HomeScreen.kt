package com.shubham.beyondroot

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeScreen : AppCompatActivity() {

    private var mDatabase: DatabaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mDatabase = DatabaseHelper(this)

        val contactView = findViewById<RecyclerView>(R.id.recycler_view)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        contactView.layoutManager = LinearLayoutManager(this)
        contactView.setHasFixedSize(true)

        mDatabase = DatabaseHelper(this)

        var allContacts: ArrayList<Contacts> = ArrayList<Contacts>(5)
        allContacts = mDatabase!!.listContacts();
        if (allContacts!!.size > 0) {
            contactView.visibility = View.VISIBLE
            var mAdapter = ContactAdapter(this, allContacts)
            contactView.adapter = mAdapter
        } else {
            contactView.visibility = View.GONE
            Toast.makeText(this, "There is no contact in the database. Start adding now", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy(){
        super.onDestroy()
        if (mDatabase != null) {
            mDatabase!!.close()
        }
    }
}
package com.example.findnerds.signintask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_user__data_.*

class User_Data_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user__data_)

        var data = intent.extras!!.getString("user_data").toString()
        data=filterUsername(data)

        btn_to_showID.setOnClickListener {
            userName.text = data.toUpperCase()
            userName.isVisible=true
            welcome_banner.isVisible=true
        }
        signOut.setOnClickListener {
            signOut_fun()
        }
    }

    private fun signOut_fun() {
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(this,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun filterUsername(data: String): String {
        var v=0
        for (i in 0 until data.length){
            if (data[i]=='@')
                v=i
        }
        return data.removeRange(v,data.length)
    }

}

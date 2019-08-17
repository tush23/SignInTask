package com.example.findnerds.signintask

import android.R.attr.data
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.AuthResult
import androidx.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import android.R.attr.password
import android.content.Intent


class MainActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()

        sign_btn.setOnClickListener {
            val user_id = user_id.text.toString()+"@tushar.com"
            val pass = user_pass.text.toString()
            Sign_In(user_id,pass)
        }

    }

    private fun Sign_In(userId: String, pass: String) {
        mAuth?.signInWithEmailAndPassword(userId, pass)?.addOnCompleteListener(this
        ) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                //Log.d(FragmentActivity.TAG, "signInWithEmail:success")
                //Toast.makeText(this, "Authentication Success.",
                  //  Toast.LENGTH_SHORT).show()
                val user = mAuth?.currentUser
                Toast.makeText(this, user!!.email,
                    Toast.LENGTH_SHORT).show()
                updateUI(user)
            } else {
                // If sign in fails, display a message to the user.
                //Log.w(FragmentActivity.TAG, "signInWithEmail:failure", task.exception)
                Toast.makeText(this, "Authentication failed.",
                    Toast.LENGTH_SHORT).show()
                //updateUI(null)
            }
        }
    }



    override fun onStart() {
        super.onStart()
        val currentUser = mAuth?.currentUser
        //updateUI(currentUser)
    }
    private fun updateUI(user: FirebaseUser?) {
        val user_intent = Intent(this,User_Data_Activity::class.java)
        user_intent.putExtra("user_data",user?.email.toString())
        user_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        user_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        /*var data_bundle: Bundle? =null
        if (user != null) {
            data_bundle?.putString("userData",user.email)
        }
        user_intent.putExtras(data_bundle!!)*/
        startActivity(user_intent)
    }
}

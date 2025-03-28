package com.example.sshop

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_auth)

        val userLog: EditText = findViewById(R.id.user_log_auth)
        val userPassword: EditText = findViewById(R.id.user_pass_auth)
        val buttonAuth : Button = findViewById(R.id.button_auth)
        val buttonToReg: Button = findViewById(R.id.button_to_reg)

        buttonToReg.setOnClickListener {
            val intent  = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        buttonAuth.setOnClickListener{
            val log = userLog.text.toString().trim()
            val pass = userPassword.text.toString().trim()


            if (log == "" || pass == "") {
                Toast.makeText(this, "Заполните поля", Toast.LENGTH_LONG).show()
            }else{
                val db = DbHelper(this,null)
                val isAuth = db.getUser(log, pass)

                if (isAuth){
                    Toast.makeText(this, "Аторизация успешна", Toast.LENGTH_LONG).show()
                    userLog.text.clear()
                    userPassword.text.clear()

                    val intent = Intent(this, ItemsActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "Проверьте логин/пароль", Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}
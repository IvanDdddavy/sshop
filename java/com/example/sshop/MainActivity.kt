package com.example.sshop

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val userLog: EditText = findViewById(R.id.user_log)
        val userEmail: EditText = findViewById(R.id.user_email)
        val userPassword: EditText = findViewById(R.id.user_pass)
        val buttonReg : Button = findViewById(R.id.button_registration)
        val buttonToAuth: Button = findViewById(R.id.button_to_auth)

        buttonToAuth.setOnClickListener {
            val intent  = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }

        fun clearFields() {
            findViewById<EditText>(R.id.user_log).text.clear()
            findViewById<EditText>(R.id.user_email).text.clear()
            findViewById<EditText>(R.id.user_pass).text.clear()
        }

        buttonReg.setOnClickListener {
            val log = userLog.text.toString().trim()
            val email = userEmail.text.toString().trim()
            val pass = userPassword.text.toString().trim()

            if (log.isEmpty() || pass.isEmpty() || email.isEmpty()) {
                when {
                    log.isEmpty() -> Toast.makeText(this, "Напишите свой логин", Toast.LENGTH_LONG).show()
                    email.isEmpty() -> Toast.makeText(this, "Напишите свой Email", Toast.LENGTH_LONG).show()
                    pass.isEmpty() -> Toast.makeText(this, "Напишите свой пароль", Toast.LENGTH_LONG).show()
                }
                return@setOnClickListener
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Введите корректный Email", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            try {
                val db = DbHelper(this, null)

                if (db.getUser(log, email)) {
                    Toast.makeText(this, "Пользователь с таким логином/email уже существует", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }


                val user = User(log, email, pass)
                db.addUsers(user)

                Toast.makeText(this, "Регистрация успешна", Toast.LENGTH_LONG).show()
                clearFields()

                val intent = Intent(this, AuthActivity::class.java)
                startActivity(intent)

            } catch (e: Exception) {
                Toast.makeText(this, "Ошибка регистрации: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}
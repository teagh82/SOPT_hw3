package com.example.sopt.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sopt.data.RequestLogin
import com.example.Sopt.R
import com.example.sopt.main.EnterMainActivity
import com.example.sopt.extension.showToast
import com.example.sopt.extension.textChangedListener
import com.example.sopt.network.RequestToServer
import com.example.sopt.network.customEnqueue
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val requestToServer = RequestToServer   //싱글톤 그대로 가져옴

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        et_email.textChangedListener {s ->
            if(s.isNullOrBlank()){     //it을 원하는 이름으로 명시해줄 수 있습니다!
                showToast("아이디가 빈칸이네요")
            }
        }

        btn_login.setOnClickListener{
            if(et_email.text.isNullOrBlank()||et_pwd.text.isNullOrBlank()) {
                showToast("아이디/비밀번호를 확인하세요!")
            }else{
                requestToServer.service.requestLogin(
                    RequestLogin(
                        id = et_email.text.toString(),
                        password = et_pwd.text.toString()
                    )//로그인 정보를 전달
                ).customEnqueue(
                    onError = {showToast("올바르지 못한 요청입니다")},
                    onSuccess = {
                        if (it.success) {
                            showToast("로그인 성공")
                            val intent = Intent(this@MainActivity, EnterMainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            showToast("아이디/비밀번호를 확인하세요!")
                        }
                    }
                )
            }
        }

        tv_register.setOnClickListener{
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        val tmp: String? = intent.getStringExtra("check")

        if(tmp.equals("1")) {
            val email = intent.getStringExtra("email")
            val password = intent.getStringExtra("password")
            et_email.setText(email)
            et_pwd.setText(password)
        }
    }
}


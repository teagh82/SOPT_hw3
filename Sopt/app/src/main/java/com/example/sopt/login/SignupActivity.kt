package com.example.sopt.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.Sopt.R
import com.example.sopt.data.RequestSignup
import com.example.sopt.extension.showToast
import com.example.sopt.network.SignupRequestToServer
import com.example.sopt.network.customEnqueue
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {

    val signupRequestToServer = SignupRequestToServer   //싱글톤 그대로 가져옴

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)


        btn_register.setOnClickListener {
            if(et_id.text.isNullOrBlank()||et_pass.text.isNullOrBlank()||et_pass2.text.isNullOrBlank()
                ||et_name.text.isNullOrBlank()||et_newemail.text.isNullOrBlank()||et_pho.text.isNullOrBlank()) {
                showToast("내용을 확인하세요!")
            }else if(!et_pass.text.toString().equals(et_pass2.text.toString())) {
                showToast("비밀번호를 확인하세요!")
            }else{
                signupRequestToServer.service.requestSignup(
                    RequestSignup(
                        id = et_id.text.toString(),
                        password = et_pass.text.toString(),
                        name = et_name.text.toString(),
                        email = et_newemail.text.toString(),
                        phone = et_pho.text.toString()
                    )//정보를 전달
                ).customEnqueue(
                    onError = {showToast("올바르지 못한 요청입니다")},
                    onSuccess = {
                        if (it.success) {
                            showToast("회원가입 성공")
                            val intent = Intent(this@SignupActivity, MainActivity::class.java)
                            intent.putExtra("check", "1")
                            intent.putExtra("email", et_id.text.toString())
                            intent.putExtra("password", et_pass.text.toString())
                            startActivity(intent)
                            finish()
                        } else {
                            showToast("내용을 확인하세요!")
                        }
                    }
                )
            }

        }
    }
}


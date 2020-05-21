package com.example.sopt.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.Sopt.R
import kotlinx.android.synthetic.main.activity_enter_main.*

class EnterMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_main)


        main_viewPager.adapter = MainPagerAdapter(supportFragmentManager)
        main_viewPager.offscreenPageLimit = 2
        main_viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                //내비게이션 메뉴 아이템 체크
                bottomNavigationView.menu.getItem(position).isChecked = true
            }
        })

        bottomNavigationView.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.menu_home -> main_viewPager.currentItem = 0
                R.id.menu_book -> main_viewPager.currentItem = 1
                R.id.menu_person -> main_viewPager.currentItem = 2
            }
            true
        }
    }
}
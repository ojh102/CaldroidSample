package com.github.ojh.caldroidsample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.github.ojh.caldroidsample.CaldroidSampleActivity
import com.github.ojh.caldroidsample.R
import com.roomorama.caldroid.CaldroidFragment
import com.roomorama.caldroid.CaldroidListener
import kotlinx.android.synthetic.main.activity_main.*
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val caldroidFragment = CaldroidFragment()
        val args = Bundle()
        val cal = Calendar.getInstance()
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1)
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR))
        args.putInt(CaldroidFragment.THEME_RESOURCE, com.caldroid.R.style.CaldroidDefaultDark)
        caldroidFragment.arguments = args

        val disabledDates = ArrayList<Date>()
        disabledDates.add(getDate("2016-12-16"))
        disabledDates.add(getDate("2016-12-20"))
        disabledDates.add(getDate("2016-12-30"))
        caldroidFragment.setDisableDates(disabledDates)


        caldroidFragment.caldroidListener = object : CaldroidListener() {
            override fun onSelectDate(date: Date?, view: View?) {
                if(caldroidFragment.isSelectedDate(date)) {
                    caldroidFragment.clearSelectedDate(date)

                } else {
                    caldroidFragment.setSelectedDate(date)
                }
                caldroidFragment.refreshView()
            }
        }

        supportFragmentManager.beginTransaction()
                .replace(R.id.calendar, caldroidFragment)
                .commit()

        button.setOnClickListener {
            startActivity(Intent(this, CaldroidSampleActivity::class.java))
        }
    }

    private fun getDate(strDate: String): Date {
        val format = SimpleDateFormat("yyyy-MM-dd")
        val date = format.parse(strDate)

        val calendar = Calendar.getInstance()
        calendar.clear()

        calendar.time = date

        return calendar.time
    }
}

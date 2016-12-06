package com.github.yyyank.addict.blocker.activity


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.github.yyyank.addict.blocker.R
import com.github.yyyank.addict.blocker.preferences.AddictBlockerSettings
import com.github.yyyank.addict.blocker.preferences.SettingsType
import com.github.yyyank.addict.blocker.receiver.AppLaunchObserver


/**
 * Created by yy_yank on 2016/11/23.
 */
class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startService(Intent(this, AppLaunchObserver::class.java))
        setContentView(R.layout.main_layout);
        val c = applicationContext
        val intervalSelected = AddictBlockerSettings.load(c, "interval", SettingsType.String)?.value as String
        val countSelected = AddictBlockerSettings.load(c, "count", SettingsType.String)?.value as String
        val appSelected = AddictBlockerSettings.load(c, "targetApp", SettingsType.String)?.value as String
        val count = findViewById(R.id.countSpinner) as Spinner
        count.apply {
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val value = count.selectedItem;
                    AddictBlockerSettings.save(c, "count", value)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
            adapter = ArrayAdapter<String>(MainActivity@this.context, android.R.layout.simple_spinner_item).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                add("")
                add("1")
                add("5")
                add("10")
                add("15")
                add("20")
                add("30")
                add("40")
                add("50")
            }
            selectByValue(countSelected)
        }
        val interval = findViewById(R.id.intervalSpinner) as Spinner
        interval.apply {
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val value = interval.selectedItem;
                    AddictBlockerSettings.save(c, "interval", value)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
            adapter = ArrayAdapter<String>(MainActivity@this.context, android.R.layout.simple_spinner_item).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                add("")
                add("0.5h")
                add("1.0h")
                add("1.5h")
                add("2.0h")
                add("2.5h")
                add("3.0h")
                add("4.5h")
                add("5.0h")
            }
            selectByValue(intervalSelected)
        }

        val targetApp = findViewById(R.id.targetSpinner) as Spinner
        targetApp.apply {
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val value = targetApp.selectedItem;
                    AddictBlockerSettings.save(c, "targetApp", value)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
            adapter = ArrayAdapter<String>(MainActivity@this.context, android.R.layout.simple_spinner_item).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                add("")
                add("twitter")
            }
            selectByValue(appSelected)
        }


        val testButton = findViewById(R.id.button) as Button
        testButton.apply {
            setOnClickListener {
                Log.d("AddictBlocker", "count ${AddictBlockerSettings.load(c, "count", SettingsType.String)?.value.toString()}")
                Log.d("AddictBlocker", "interval ${AddictBlockerSettings.load(c, "interval", SettingsType.String)?.value.toString()}")
                Log.d("AddictBlocker", "targetApp ${AddictBlockerSettings.load(c, "targetApp", SettingsType.String)?.value.toString()}")
            }
        }
        startActivity(Intent("android.settings.USAGE_ACCESS_SETTINGS"))
    }

    override fun onResume() {
        super.onResume()
    }


    init {
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            Log.d("AddictBlocker", throwable.message)

            throwable.stackTrace.forEach {
                Log.d("AddictBlocker", "${it.fileName} : ${it.lineNumber.toString()} : ${it.methodName} ")
            }
        }
    }
}


fun Spinner.selectByValue(value: String) {
    for (i in 0..this.adapter.count - 1) {
        if (value == adapter.getItem(i)) {
            setSelection(i)
            return
        }
    }
}
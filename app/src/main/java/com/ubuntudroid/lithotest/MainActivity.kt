package com.ubuntudroid.lithotest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.facebook.litho.ComponentContext
import com.facebook.litho.LithoView
import com.ubuntudroid.lithotest.specs.ListItem

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val componentContext = ComponentContext(this)

        val component = ListItem.create(componentContext).build()

        setContentView(LithoView.create(this,component))
    }
}

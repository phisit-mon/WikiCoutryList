package com.phisit.wikicoutrylist.base

import androidx.test.espresso.intent.Intents
import org.junit.After
import org.junit.Before

abstract class BaseScreenTest {

    @Before
    open fun initial() {
        Intents.init()
    }

    @After
    open fun release(){
        Intents.release()
    }
}
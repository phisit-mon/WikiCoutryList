package com.phisit.wikicoutrylist.helper

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider

inline fun <reified A : Activity> launchApp(
    onBefore: () -> Unit = {},
    intentFactory: (Context) -> Intent = {
        Intent(
            ApplicationProvider.getApplicationContext(), A::class.java
        )
    },
): ActivityScenario<A> {
    onBefore()
    val context = ApplicationProvider.getApplicationContext<Context>()
    return ActivityScenario.launch(intentFactory(context))
}
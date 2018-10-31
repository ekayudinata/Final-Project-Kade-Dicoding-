package com.example.yudinata.footballsc

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.swipeDown
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.example.yudinata.footballsc.R.id.*
import com.example.yudinata.footballsc.feature.matchs.nextmatch.NextsFragment
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
@JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testAppBehaviour(){

        Thread.sleep(2000)
        onView(withId(prevss)).perform(click())
        Thread.sleep(5000)
        onView(withId(nextss)).perform(click())
       // pilih item ke 2
        Thread.sleep(4000)
        onView(withId(R.id.listEventt)).perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(2))
        onView(withId(R.id.listEventt)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
        Thread.sleep(4000)
        onView(withId(add_to_favorite)).perform(click())
        Thread.sleep(4000)
        pressBack()
        Thread.sleep(4000)
        onView(withId(favorites)).perform(click())
    }
}
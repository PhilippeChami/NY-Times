package com.nytimes.android

import android.app.Activity
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.nytimes.android.news.presentation.ui.activities.MostViewedNewsActivity
import com.nytimes.android.news.presentation.ui.adapters.MostViewedNewsAdapter
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class MostViewedInstrumentedTest {
    private var itemCount = 0

    @Rule
    @JvmField
    var mActivityRule: ActivityTestRule<MostViewedNewsActivity> = ActivityTestRule(MostViewedNewsActivity::class.java)

    var activity:Activity? = null
    var recyclerView :RecyclerView? = null

    @Before
    fun init() {
        activity = mActivityRule.activity
        recyclerView = activity?.findViewById(R.id.rvMostViewed)
    }

    @Test
    fun recyclerViewNewsClickTest() {

            Espresso.onView(ViewMatchers.withId(R.id.srlMostViewed))
                .perform(ViewActions.swipeDown())

            Thread.sleep(4000)

            itemCount = recyclerView!!.adapter!!.itemCount

            for (i in 0 until itemCount) {
                Espresso.onView(ViewMatchers.withId(R.id.rvMostViewed))
                    .check(hasItemsCount()).perform(
                        RecyclerViewActions.actionOnItemAtPosition<MostViewedNewsAdapter.MostViewedNewsHolder>(i, ViewActions.click()))
                Espresso.pressBack()
            }
    }

    companion object {
        fun hasItemsCount(): ViewAssertion {
            return ViewAssertion { view, e ->
                if (view !is RecyclerView) {
                    throw e
                }
                Assert.assertTrue(view.adapter!!.itemCount > 0)
            }
        }
    }
}
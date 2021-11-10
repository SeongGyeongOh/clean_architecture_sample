package com.gyeong.architecturekotlin

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.gyeong.architecturekotlin.presenter.main.MainActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import tools.fastlane.screengrab.Screengrab
import tools.fastlane.screengrab.UiAutomatorScreenshotStrategy
import tools.fastlane.screengrab.locale.LocaleTestRule

@RunWith(AndroidJUnit4::class)
class ScreenshotsInstrumentedTest {
    @Rule
    @JvmField
    val localeTestRule = LocaleTestRule()

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java, false, false)

    @Test
    fun testTakeScreenshot() {
        activityRule.launchActivity(null)
        Screengrab.setDefaultScreenshotStrategy(UiAutomatorScreenshotStrategy())

        Espresso.onView(ViewMatchers.withId(R.id.main_go_barcode))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Screengrab.screenshot("mainpage")

        Espresso.onView(ViewMatchers.withId(R.id.main_go_barcode))
            .perform(ViewActions.click())

        Screengrab.screenshot("secondPage")
    }

    @Test
    fun useAppContest() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.gyeong.architecturekotlin", appContext.packageName)
    }
}
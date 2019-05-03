package com.example.conor.softwareapp.log;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.example.conor.softwareapp.R;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class resetPwordTest {

    @Rule
    public ActivityTestRule rule = new ActivityTestRule(resetPword.class,true,false);

    @Test
    public void shouldCheckIfEmailEditTextIsDisplayed() throws Exception{
        rule.launchActivity(new Intent());
        Espresso.onView(withId(R.id.sendEmail)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldCheckIfResetButtonIsDisplayed() throws Exception{
        rule.launchActivity(new Intent());
        Espresso.onView(withId(R.id.resetBtn)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldCheckTextViewIsDisplayed() throws Exception{
        rule.launchActivity(new Intent());
        Espresso.onView(withText(R.string.by_clicking_reset_you_will_receive_an_email_to_reset_your_password)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldThrowEmaildErrorInEditText() throws Exception{
        rule.launchActivity(new Intent());
        Espresso.onView(withId(R.id.sendEmail)).perform(typeText(""),closeSoftKeyboard());
        Espresso.onView(withId(R.id.resetBtn)).perform(click());

        Espresso.onView(withId(R.id.sendEmail)).check(matches(hasErrorText("Please Enter An Email")));
    }

}
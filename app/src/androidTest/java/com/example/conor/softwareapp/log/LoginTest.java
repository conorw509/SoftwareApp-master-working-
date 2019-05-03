package com.example.conor.softwareapp.log;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import android.support.test.espresso.Espresso;
import android.support.test.runner.AndroidJUnit4;
import com.example.conor.softwareapp.R;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class LoginTest {

    @Rule
    public ActivityTestRule rule = new ActivityTestRule(Login.class,true,false);

    @Test
    public void shouldRenderTextMyMentalHealth() throws Exception{
        rule.launchActivity(new Intent());
        Espresso.onView(withText(R.string.my_mental_health)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldRendeTextForgotPassword() throws Exception{
        rule.launchActivity(new Intent());
        Espresso.onView(withText(R.string.forgot_password)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldRendeEditTextEmail() throws Exception{
        rule.launchActivity(new Intent());
        Espresso.onView(withId(R.id.emailFieldEnt)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldRendeEditTextPassword() throws Exception{
        rule.launchActivity(new Intent());
        Espresso.onView(withId(R.id.RegPword)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldCheckLoginButtonIsDisplayed() throws Exception{
        rule.launchActivity(new Intent());
        Espresso.onView(withId(R.id.LoginBtn)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldCheckBackButtonIsDisplayed() throws Exception{
        rule.launchActivity(new Intent());
        Espresso.onView(withId(R.id.BackToLoginBtn)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldCheckBackButtonWorks() throws Exception{
        rule.launchActivity(new Intent());
        Espresso.onView(withId(R.id.BackToLoginBtn)).perform(click());
        rule.launchActivity(new Intent(String.valueOf(loginInHome.class)));
    }

    @Test
    public void shouldThrowPasswordErrorInEditText() throws Exception{
        rule.launchActivity(new Intent());
        Espresso.onView(withId(R.id.emailFieldEnt)).perform(typeText("fakeEmail@gmail.com"),closeSoftKeyboard());
        Espresso.onView(withId(R.id.LoginBtn)).perform(click());

        Espresso.onView(withId(R.id.RegPword)).check(matches(hasErrorText("Please enter a Password")));
    }

    @Test
    public void shouldThrowEmailErrorInEditText() throws Exception{
        rule.launchActivity(new Intent());
        Espresso.onView(withId(R.id.RegPword)).perform(typeText("Password1123.4"),closeSoftKeyboard());
        Espresso.onView(withId(R.id.LoginBtn)).perform(click());

        Espresso.onView(withId(R.id.emailFieldEnt)).check(matches(hasErrorText("Please enter an Email")));
    }

}
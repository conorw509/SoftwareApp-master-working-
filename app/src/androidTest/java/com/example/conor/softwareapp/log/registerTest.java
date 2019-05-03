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
public class registerTest {

    @Rule
    public ActivityTestRule rule = new ActivityTestRule(register.class,true,false);

    @Test
    public void shouldRenderRegisterTextView() throws Exception{
        rule.launchActivity(new Intent());
        Espresso.onView(withId(R.id.textGrid)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldRendeEditTextEmail() throws Exception{
        rule.launchActivity(new Intent());
        Espresso.onView(withId(R.id.RegEmail)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldRendeEditTextPassword() throws Exception{
        rule.launchActivity(new Intent());
        Espresso.onView(withId(R.id.RegPword)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldCheckRegEx1isDisplayed() throws Exception{
        rule.launchActivity(new Intent());
        Espresso.onView(withText(R.string.password_must_be_at_least_10_characters_with_no_spaces)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldCheckRegEx2isDisplayed() throws Exception{
        rule.launchActivity(new Intent());
        Espresso.onView(withText(R.string.password_must_have_at_least_one_capital_letter)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldCheckRegEx3isDisplayed() throws Exception{
        rule.launchActivity(new Intent());
        Espresso.onView(withText(R.string.password_must_have_at_least_one_number)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldCheckBackButtonIsDisplayed() throws Exception{
        rule.launchActivity(new Intent());
        Espresso.onView(withId(R.id.LoginBk)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldCheckRegisterIsDisplayed() throws Exception{
        rule.launchActivity(new Intent());
        Espresso.onView(withId(R.id.RegBtn)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldCheckBackButtonWorks() throws Exception{
        rule.launchActivity(new Intent());
        Espresso.onView(withId(R.id.LoginBk)).perform(click());
        rule.launchActivity(new Intent(String.valueOf(loginInHome.class)));
    }

    @Test
    public void shouldThrowPasswordErrorInEditText() throws Exception{
        rule.launchActivity(new Intent());
        Espresso.onView(withId(R.id.RegEmail)).perform(typeText("fakeEmail@gmail.com"),closeSoftKeyboard());
        Espresso.onView(withId(R.id.RegBtn)).perform(click());

        Espresso.onView(withId(R.id.RegPword)).check(matches(hasErrorText("Please enter a Password")));
    }

    @Test
    public void shouldThrowEmailErrorInEditText() throws Exception{
        rule.launchActivity(new Intent());
        Espresso.onView(withId(R.id.RegPword)).perform(typeText("Luke.1.2.76"),closeSoftKeyboard());
        Espresso.onView(withId(R.id.RegBtn)).perform(click());

        Espresso.onView(withId(R.id.RegEmail)).check(matches(hasErrorText("Please enter an Email")));
    }

    @Test
    public void shouldThrowEmailErrorIfEmailIsInvalid() throws Exception{
        rule.launchActivity(new Intent());
        Espresso.onView(withId(R.id.RegEmail)).perform(typeText("hello"),closeSoftKeyboard());
        Espresso.onView(withId(R.id.RegPword)).perform(typeText("WhatsUp.1.256"),closeSoftKeyboard());
        Espresso.onView(withId(R.id.RegBtn)).perform(click());
        Espresso.onView(withId(R.id.RegEmail)).check(matches(hasErrorText("Please enter a Valid email")));
    }

    @Test
    public void shouldThrowPasswordErrorIfPasswordIsWeak() throws Exception{
        rule.launchActivity(new Intent());
        Espresso.onView(withId(R.id.RegEmail)).perform(typeText("fakeEmail@gmail.com"),closeSoftKeyboard());
        Espresso.onView(withId(R.id.RegPword)).perform(typeText("weak"),closeSoftKeyboard());
        Espresso.onView(withId(R.id.RegBtn)).perform(click());

        Espresso.onView(withId(R.id.RegPword)).check(matches(hasErrorText("Password is to weak")));
    }




}
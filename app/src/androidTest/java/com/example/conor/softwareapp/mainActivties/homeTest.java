package com.example.conor.softwareapp.mainActivties;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.Gravity;
import android.view.KeyEvent;

import com.example.conor.softwareapp.R;
import com.example.conor.softwareapp.log.loginInHome;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.contrib.DrawerMatchers.isOpen;
import static android.support.test.espresso.contrib.NavigationViewActions.navigateTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class homeTest {
    FirebaseUser firebaseUser;
    @Rule
    public ActivityTestRule rule = new ActivityTestRule(home.class,true,true);

    @Test
    public void shouldCheckIfAudioBtnDisplayed() throws Exception{
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        rule.launchActivity(new Intent());
        Espresso.onView(withId(R.id.audioBtn)).check(matches(isDisplayed()));
    }
    @Test
    public void shouldCheckIfChatBtnDisplayed() throws Exception{
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        rule.launchActivity(new Intent());
        Espresso.onView(withId(R.id.chatBtn)).check(matches(isDisplayed()));
    }
    @Test
    public void shouldCheckIfJournalBtnDisplayed() throws Exception{
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        rule.launchActivity(new Intent());
        Espresso.onView(withId(R.id.journalBtn)).check(matches(isDisplayed()));
    }
    @Test
    public void shouldCheckIfSupportlBtnDisplayed() throws Exception{
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        rule.launchActivity(new Intent());
        Espresso.onView(withId(R.id.supportBtn)).check(matches(isDisplayed()));
    }
    @Test
    public void shouldCheckIfTextViewlBtnDisplayed() throws Exception{
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        rule.launchActivity(new Intent());
        Espresso.onView(withId(R.id.textGrid)).check(matches(isDisplayed()));
    }
    @Test
    public void shouldCheckIfNavViewDisplayed() throws Exception{
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        rule.launchActivity(new Intent());
        Espresso.onView(withId(R.id.drawerLayout)).check(matches(isClosed(Gravity.RIGHT)))
                .perform(DrawerActions.open()).check(matches(isOpen()));
    }
    @Test
    public void shouldCheckAudiokButtonWorks() throws Exception{
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        rule.launchActivity(new Intent());
        Espresso.onView(withId(R.id.audioBtn)).perform(click());
        rule.launchActivity(new Intent(String.valueOf(audio.class)));
    }
    @Test
    public void shouldCheckChatkButtonWorks() throws Exception{
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        rule.launchActivity(new Intent());
        Espresso.onView(withId(R.id.chatBtn)).perform(click());
        rule.launchActivity(new Intent(String.valueOf(chat.class)));
    }
    @Test
    public void shouldCheckSuppotkButtonWorks() throws Exception{
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        rule.launchActivity(new Intent());
        Espresso.onView(withId(R.id.supportBtn)).perform(click());
        rule.launchActivity(new Intent(String.valueOf(support.class)));
    }
    @Test
    public void shouldCheckJournalkButtonWorks() throws Exception{
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        rule.launchActivity(new Intent());
        Espresso.onView(withId(R.id.journalBtn)).perform(click());
        rule.launchActivity(new Intent(String.valueOf(journal.class)));
    }

    @Test
    public void clickOnYourNavigationItemProfile() {
       Espresso.onView(withId(R.id.drawerLayout))
                .check(matches(isClosed(Gravity.RIGHT)))
                .perform(DrawerActions.open());

        Espresso. onView(withId(R.id.navView))
                .perform(pressKey(KeyEvent.KEYCODE_HOME)).perform(click());
        rule.launchActivity(new Intent(String.valueOf(profile.class)));
        Espresso.pressBack();
    }
    @Test
    public void clickOnYourNavigationItemFeedback() {
        Espresso.onView(withId(R.id.drawerLayout))
                .check(matches(isClosed(Gravity.RIGHT)))
                .perform(DrawerActions.open());
        Espresso.onView(withId(R.id.navView))
                .perform(pressKey(KeyEvent.KEYCODE_2)).perform(click());
        rule.launchActivity(new Intent(String.valueOf(feedback.class)));
        Espresso.pressBack();
    }
}
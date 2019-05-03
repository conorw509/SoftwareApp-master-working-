package com.example.conor.softwareapp.mainActivties;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.Gravity;
import com.example.conor.softwareapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.contrib.DrawerMatchers.isOpen;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class homeTest {
    FirebaseUser firebaseUser;

    @Rule
    public ActivityTestRule rule = new ActivityTestRule(home.class,true,false);

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
        Espresso.onView(withId(R.id.navView)).check(matches(isClosed(Gravity.RIGHT)))
                .perform(DrawerActions.open()).check(matches(isOpen()));
    }
}
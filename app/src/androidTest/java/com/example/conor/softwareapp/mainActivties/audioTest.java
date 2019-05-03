package com.example.conor.softwareapp.mainActivties;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.KeyEvent;
import android.widget.ListView;
import com.example.conor.softwareapp.R;
import com.example.conor.softwareapp.players.musicPlayer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


@RunWith(AndroidJUnit4.class)
public class audioTest {

    FirebaseUser firebaseUser;
    @Rule
    public ActivityTestRule rule = new ActivityTestRule(audio.class,true,true);

    @Test
    public void assertsAllItemsInTheList() throws Exception{
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        rule.launchActivity(new Intent());
        ListView listview = (ListView) rule.getActivity().findViewById(R.id.mainList);

        assertThat(listview.getCount(), is(15));
    }

    @Test
    public void assertAnItemCanBeClickedOnTheList() throws Exception{
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        rule.launchActivity(new Intent());
        final ListView listview = (ListView) rule.getActivity().findViewById(R.id.mainList);

        instrumentation.runOnMainSync(new Runnable() {
            @Override
            public void run() {
                int position = 0;
                listview.performItemClick(listview.getChildAt(position), position, listview.getAdapter().getItemId(position));
            }
        });
    }
}
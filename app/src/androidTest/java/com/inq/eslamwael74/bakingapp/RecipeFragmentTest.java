package com.inq.eslamwael74.bakingapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.inq.eslamwael74.bakingapp.Activity.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Eslam Wael on 11/14/2017.
 */

@RunWith(AndroidJUnit4.class)
public class RecipeFragmentTest {

    @Rule public ActivityTestRule<MainActivity> mActivtyTestRule
            = new ActivityTestRule<MainActivity>(MainActivity.class);


    @Test
    public void checkOnMain(){
        //find the view and perform; action on the view
        onView((withId(R.id.bar_text))).perform(click());

        //check what is expected
        onView(withId(R.id.bar_text)).check(matches(withText("BakingApp")));
    }




}

package com.inq.eslamwael74.bakingapp;

import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.espresso.Espresso;

import com.inq.eslamwael74.bakingapp.Activity.RecipeActivity;
import com.inq.eslamwael74.bakingapp.Model.Recipe;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Eslam Wael on 12/1/2017.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RecipeActivityTest {

    IdlingResource idlingResource;

    @Rule public ActivityTestRule<RecipeActivity> recipeActivityRecipeTestRule =
            new ActivityTestRule(RecipeActivity.class);

    @Before
    public void registerIdleingResource(){
            Espresso.registerIdlingResources(idlingResource);
    }
    @Test
    void itemsCheckStepActivity(){
        onView(ViewMatchers.withId(R.id.recylerview_steps)).perform(RecyclerViewActions.scrollToPosition(0));
        onView(withText("Recipe Introduction")).check(matches(isDisplayed()));
    }

    @After
    void unRegIdilingRes(){
        if (idlingResource != null){
            Espresso.unregisterIdlingResources(idlingResource);
        }
    }

}

package com.example.cardioassistant;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import java.util.ArrayList;

import android.os.SystemClock;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class HomeActivityTest {

    @Rule
    public ActivityScenarioRule<SplashActivity> activityRule =
            new ActivityScenarioRule<>(SplashActivity.class);

    @Test
    public void test4checkappname(){
        SystemClock.sleep(1000);
        onView(withText("Cardiac Assistant")).check(matches(isDisplayed()));
    }

    @Test
    public void test2addmeasurement(){

        SystemClock.sleep(5000);
        Espresso.onView(withId(R.id.login_email_view)).perform(clearText()).perform(ViewActions.typeText("ridoy@gmail.com"));
        Espresso.pressBack();
        Espresso.onView(withId(R.id.login_password_view)).perform(clearText()).perform(ViewActions.typeText("123456"));
        Espresso.pressBack();
        Espresso.onView(withId(R.id.login_loginButton)).perform(click());
        SystemClock.sleep(5000);

        Espresso.onView(withId(R.id.home_add_record)).perform(click());
        SystemClock.sleep(1000);

        Espresso.onView(withId(R.id.Add_Enter_Systolic_pressure)).perform(ViewActions.typeText("120"));
        Espresso.pressBack();
        Espresso.onView(withId(R.id.Add_Enter_Diastolic_pressure)).perform(ViewActions.typeText("80"));
        Espresso.pressBack(); //Back button
        Espresso.onView(withId(R.id.Add_Enter_Heart_Rate)).perform(ViewActions.typeText("80"));
        Espresso.pressBack(); //Back button
        Espresso.onView(withId(R.id.ADD_Enter_Measure_Time)).perform(ViewActions.typeText("9:51"));
        Espresso.pressBack(); //Back button
        Espresso.onView(withId(R.id.ADD_Enter_Measure_Date)).perform(ViewActions.typeText("22/07/2022"));
        Espresso.pressBack(); //Back button
        Espresso. onView(withId(R.id.ADD_Enter_Comment)).perform(ViewActions.typeText("Good"));
        Espresso.pressBack(); //Back button
        Espresso.onView(withId(R.id.ADD_ADDButton)).perform(click());
        SystemClock.sleep(2000);
    }

//    @Test
//    public void test(){
//        SystemClock.sleep(5000);
//        Espresso.onView(withId(R.id.login_email_view)).perform(clearText()).perform(ViewActions.typeText("ridoy@gmail.com"));
//        Espresso.pressBack();
//        Espresso.onView(withId(R.id.login_password_view)).perform(clearText()).perform(ViewActions.typeText("123456"));
//        Espresso.pressBack();
//        Espresso.onView(withId(R.id.login_loginButton)).perform(click());
//        SystemClock.sleep(5000);
//
//
//        Espresso.onView(withId(R.id.home_add_record)).perform(click());
//        SystemClock.sleep(1000);
//
//        Espresso.onView(withId(R.id.Add_Enter_Systolic_pressure)).perform(ViewActions.typeText("120"));
//        Espresso.pressBack();
//        Espresso.onView(withId(R.id.Add_Enter_Diastolic_pressure)).perform(ViewActions.typeText("80"));
//        Espresso.pressBack(); //Back button
//        Espresso.onView(withId(R.id.Add_Enter_Heart_Rate)).perform(ViewActions.typeText("80"));
//        Espresso.pressBack(); //Back button
//        Espresso.onView(withId(R.id.ADD_Enter_Measure_Time)).perform(ViewActions.typeText("9:51"));
//        Espresso.pressBack(); //Back button
//        Espresso.onView(withId(R.id.ADD_Enter_Measure_Date)).perform(ViewActions.typeText("22/07/2022"));
//        Espresso.pressBack(); //Back button
//        Espresso. onView(withId(R.id.ADD_Enter_Comment)).perform(ViewActions.typeText("Good"));
//        Espresso.pressBack(); //Back button
//        Espresso.onView(withId(R.id.ADD_ADDButton)).perform(click());
//        SystemClock.sleep(2000);
//
//    }

    @Test
    public void test3updatemeasurement(){
        SystemClock.sleep(5000);
        Espresso.onView(withId(R.id.login_email_view)).perform(clearText()).perform(ViewActions.typeText("ridoy@gmail.com"));
        Espresso.pressBack();
        Espresso.onView(withId(R.id.login_password_view)).perform(clearText()).perform(ViewActions.typeText("123456"));
        Espresso.pressBack();
        Espresso.onView(withId(R.id.login_loginButton)).perform(click());


        SystemClock.sleep(5000);
        Espresso.onView(withId(R.id.record_recycler_view)).perform(click());
        SystemClock.sleep(1000);

        Espresso.onView(withId(R.id.Update_Enter_Systolic_pressure)).perform(clearText()).perform(ViewActions.typeText("140"));

        Espresso.onView(withId(R.id.Update_Enter_Diastolic_pressure)).perform(clearText()).perform(ViewActions.typeText("100"));

        Espresso.onView(withId(R.id.Update_Enter_Heart_Rate)).perform(clearText()).perform(ViewActions.typeText("80"));

        Espresso.onView(withId(R.id.Update_Enter_Measure_Time)).perform(clearText()).perform(ViewActions.typeText("11:52"));
        Espresso.pressBack();
        Espresso.onView(withId(R.id.Update_Enter_Measure_Date)).perform(clearText()).perform(ViewActions.typeText("31/11/2022"));

        Espresso.pressBack(); //Back button
        Espresso.onView(withId(R.id.Update_Enter_Comment)).perform(clearText()).perform(ViewActions.typeText("Sick"));
        Espresso.pressBack(); //Back button
        SystemClock.sleep(2000);
        onView(withId(R.id.Update_UpdateButton)).perform(click());
        SystemClock.sleep(5000);
    }

    @Test
    public void test4deletemeasurement(){
        SystemClock.sleep(5000);
        Espresso.onView(withId(R.id.login_email_view)).perform(clearText()).perform(ViewActions.typeText("ridoy@gmail.com"));
        Espresso.pressBack();
        Espresso.onView(withId(R.id.login_password_view)).perform(clearText()).perform(ViewActions.typeText("123456"));
        Espresso.pressBack();
        Espresso.onView(withId(R.id.login_loginButton)).perform(click());

        SystemClock.sleep(5000);
        Espresso.onView(withId(R.id.record_recycler_view)).perform(click());
        SystemClock.sleep(1000);

        onView(withId(R.id.Update_DeleteButton)).perform(click());

        SystemClock.sleep(5000);
    }
}

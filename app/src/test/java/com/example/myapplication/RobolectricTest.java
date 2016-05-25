package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.robolectric.Robolectric.buildActivity;

import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class RobolectricTest {
    @Test
    public void testFirst() {
        Activity activity = Robolectric.setupActivity(MainActivity.class);
        TextView results=(TextView) activity.findViewById(R.id.textView);
        String resulttext=results.getText().toString();
        assertThat(resulttext,equalTo("Hello world!"));

    }
    @Test
    public void testSecond()
    {
        Activity activity = Robolectric.setupActivity(MainActivity.class);
        TextView results = (TextView)activity.findViewById(R.id.textView);
        Button but = (Button)activity.findViewById(R.id.button);

        but.performClick();
        String resultsText = results.getText().toString();
        assertThat(resultsText,equalTo("bye world"));
    }

    @Test
    public void testThird()
    {
        Activity activity = Robolectric.setupActivity(MainActivity.class);
        ShadowActivity shadowActivity = shadowOf(activity);

        Button but = (Button)activity.findViewById(R.id.button2);

        but.performClick();

        ShadowActivity shadowActivity1=shadowOf(activity);

        Intent startedIntent = shadowActivity.getNextStartedActivity();

        assertThat(startedIntent.getComponent().getClassName(), equalTo(Main2Activity.class.getName()));


    }



}

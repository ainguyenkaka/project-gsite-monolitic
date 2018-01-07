package app.discovery.com.idiscovery;

import android.support.test.espresso.action.ViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isSelected;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void testEnterTextField(){
        onView(withId(R.id.activity_name)).check(matches(withText("")));

        String text  =  "AAAAAA";
        onView(withId(R.id.activity_name)).perform(ViewActions.typeText(text));
        onView(withId(R.id.activity_name)).check(matches(withText(text)));
    }

    @Test
    public void testEnterInvalidTextAndSubmit() {
        // click with all empty fields
        onView(withId(R.id.btn_submit)).perform(click());
        onView(withId(R.id.activity_name)).check(matches(hasErrorText(MainValidError.REQUIRED)));
        onView(withId(R.id.activity_name)).perform(ViewActions.typeText("AAAAAA")).perform(closeSoftKeyboard());

        // click when only having activity name
        onView(withId(R.id.btn_submit)).perform(click());
        onView(withId(R.id.report_name)).check(matches(hasErrorText(MainValidError.REQUIRED)));
        onView(withId(R.id.report_name)).perform(ViewActions.typeText("AAAAAA")).perform(closeSoftKeyboard());

        // click when only having activity name and report name
        onView(withId(R.id.btn_submit)).perform(click());
        onView(withId(R.id.activity_date)).check(matches(hasErrorText(MainValidError.DATE)));
        onView(withId(R.id.activity_date)).perform(ViewActions.typeText("AAAAAA")).perform(closeSoftKeyboard());
        onView(withId(R.id.activity_date)).check(matches(hasErrorText(MainValidError.DATE)));
        onView(withId(R.id.activity_date)).perform(ViewActions.typeText("12/12/2016")).perform(closeSoftKeyboard());

        // click when having invalid time
        onView(withId(R.id.attending_time)).perform(ViewActions.typeText("AAAAAA")).perform(closeSoftKeyboard());
        onView(withId(R.id.btn_submit)).perform(click());
        onView(withId(R.id.attending_time)).check(matches(hasErrorText(MainValidError.TIME)));
    }

    @Test
    public void testSubmitEmptyForm() {
        onView(withId(R.id.btn_submit)).perform(click());
        onView(withId(R.id.dialog)).check(doesNotExist());
    }

    @Test
    public void testEnterTextEachFieldAndSubmitForm() {
        // enter activity name and submit
        onView(withId(R.id.activity_name)).perform(typeText("AAAAAA")).perform(closeSoftKeyboard());
        onView(withId(R.id.btn_submit)).perform(click());
        onView(withId(R.id.dialog)).check(doesNotExist());

        // enter report name and submit
        onView(withId(R.id.report_name)).perform(ViewActions.typeText("AAAAAA")).perform(closeSoftKeyboard());
        onView(withId(R.id.btn_submit)).perform(click());
        onView(withId(R.id.dialog)).check(doesNotExist());

        // enter date and submit successfully
        onView(withId(R.id.activity_date)).perform(ViewActions.typeText("12/12/2017")).perform(closeSoftKeyboard());
        onView(withId(R.id.btn_submit)).perform(click());
        onView(withId(R.id.dialog)).check(matches(isDisplayed()));
        onView(withId(R.id.dialog)).perform(pressBack());

        // enter location and submit
        onView(withId(R.id.location_name)).perform(ViewActions.typeText("AAAAAA")).perform(closeSoftKeyboard());
        onView(withId(R.id.btn_submit)).perform(click());
        onView(withId(R.id.dialog)).check(matches(isDisplayed()));
        onView(withId(R.id.dialog)).perform(pressBack());

        // enter time and submit
        onView(withId(R.id.attending_time)).perform(ViewActions.typeText("12:30")).perform(closeSoftKeyboard());
        onView(withId(R.id.btn_submit)).perform(click());
        onView(withId(R.id.dialog)).check(matches(isDisplayed()));

    }


}

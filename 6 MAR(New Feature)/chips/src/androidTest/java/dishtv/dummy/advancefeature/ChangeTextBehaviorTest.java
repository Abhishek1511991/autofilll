package dishtv.dummy.advancefeature;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ChangeTextBehaviorTest {

    private static final String stringToBeTyped = "Espresso";

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void changeText()
    {
        Espresso.onView(ViewMatchers.withId(R.id.editTextUserInput)).perform(ViewActions.typeText(stringToBeTyped), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.changeTextBt)).perform(ViewActions.click());
        // Check that the text was changed.
        Espresso.onView(ViewMatchers.withId(R.id.textToBeChanged)).check(ViewAssertions.matches(ViewMatchers.withText(stringToBeTyped)));

    }
}

package dishtv.dummy.advancefeature;

import android.content.Context;
import android.graphics.Color;
/*import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

// for assertions on Java 8 types
import static com.google.common.truth.Truth8.assertThat;*/

import android.graphics.Color;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("dishtv.dummy.advancefeature", appContext.getPackageName());
    }


    @Test
    public void useOfAsserts()
    {
       /* String string = "awesome";
        assertThat(string).startsWith("awe");
        assertWithMessage("Without me, it's just aweso").that(string).contains("me");

        Iterable<Color> googleColors = googleLogo.getColors();
        assertThat(googleColors)
                .containsExactly(Color.BLUE, Color.RED, Color.YELLOW,Color. BLUE, Color.GREEN, Color.RED)
                .inOrder();*/

    }




}

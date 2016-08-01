package base;

import android.content.*;
import android.os.*;
import android.support.test.*;
import android.support.test.rule.*;
import android.support.test.runner.*;
import android.test.*;

import com.adjust.sdk.*;

import org.junit.*;
import org.junit.runner.*;

import java.util.*;

import static java.util.UUID.*;

/**
 * Robolectric + JUnit Tests.
 */
@RunWith(AndroidJUnit4.class)
public class UnitTestBase
        extends ActivityInstrumentationTestCase2<UnitTestActivity> {

    protected Context context;

    // Testing Comparisons
    protected static final int NUMBER_NEGATIVE_ONE = -1;
    protected static final int NUMBER_ZERO = 0;
    protected static final int NUMBER_ONE = 1;
    protected static final int DENSITY_LDPI = 36;
    protected static final int DENSITY_MDPI = 48;
    protected static final int DENSITY_HDPI = 72;
    protected static final int DENSITY_XHDPI = 96;
    protected static final int DENSITY_XXHDPI = 144;
    protected static final int DENSITY_XXXHDPI = 192;
    protected static final String STRING_EMPTY = "";
    protected static final String STRING_NULL = null;
    protected static final String STRING_UNIQUE = randomUUID().toString();
    protected static final String STRING_UNIQUE2 = randomUUID().toString() + randomUUID().toString();
    protected static final String STRING_UNIQUE3 = randomUUID().toString();
    protected static final Integer INTEGER_RANDOM = new Random().nextInt();
    protected static final Integer INTEGER_RANDOM_POSITIVE = new Random().nextInt(Integer.SIZE - 1);
    protected static final Long LONG_RANDOM = new Random().nextLong();
    protected static final Double DOUBLE_RANDOM = new Random().nextDouble();

    public UnitTestBase() {
        super(UnitTestActivity.class);
    }

    public UnitTestBase(Class<UnitTestActivity> mainActivity) {
        super(mainActivity);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        context = getActivity();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
}

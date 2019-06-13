import android.util.Log
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.task.square.black.taskmanagment.MainActivity
import com.task.square.black.taskmanagment.R
import org.junit.*
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class FragTest {
//    @Test
//    fun testDismissDialogFragment() {
//        launchFragmentInContainer<TasksFragment>()
//
//        // now use espresso to look for the fragment's text view and verify it is displayed
//        onView(withId(com.task.square.black.taskmanagment.R.id.textView)).check(matches(withText("I am a fragment")));
//    }
//    @Rule
//    var activityTestRule = ActivityTestRule(MainActivity::class.java)
//
//    // Looks for an EditText with id = "R.id.etInput"
//    // Types the text "Hello" into the EditText
//    // Verifies the EditText has text "Hello"
//    @Test
//    fun validateEditText() {
//        onView(withId(com.task.square.black.taskmanagment.R.id.username_edittext)).perform(typeText("Hello")).check(matches(withText("Hello")))
//        onView(withId(com.task.square.black.taskmanagment.R.id.login_button_add)).perform(click())
//
//    }
//    @Test fun successfulLogin() {
//        // GIVEN
//        val scenario =
//                ActivityScenario.launch(MainActivity::class.java)
//        // WHEN
//        onView(withId(R.id.username_edittext)).perform(typeText("sste"))
//
//        onView(withId(R.id.login_button_add)).perform(click())
//        // THEN
//        ass(getIntents().first())
//                .hasComponentClass(TaskActivity::class.java)
//    }
@Rule
@JvmField
public val rule  = getRule()
    private val username_tobe_typed="Ajesh"

    private fun getRule(): ActivityTestRule<MainActivity> {
        Log.e("Initalising rule","getting Mainactivity")
        return ActivityTestRule(MainActivity::class.java)
    }
    companion object {

        @BeforeClass
        @JvmStatic
        fun before_class_method(){
            Log.e("@Before Class","Hi this is run before anything")
        }

        @AfterClass
        @JvmStatic
        fun after_class_method(){
            Log.e("@After Class","Hi this is run after everything")
        }

    }

    @Before
    fun before_test_method(){
        Log.e("@Before","Hi this is run before every test function")
    }


    @Test
    fun login_success(){
        Log.e("@Test","Performing login success test")
        Espresso.onView((withId(R.id.username_edittext)))
                .perform(ViewActions.typeText(username_tobe_typed))
        onView(withId(R.id.username_edittext)).perform( closeSoftKeyboard());
        onView(withId(R.id.login_button_add))
                .perform(click());
    }

    @After
    fun after_test_method() {
        Log.e("@After", "Hi this is run after every test function")
    }
}
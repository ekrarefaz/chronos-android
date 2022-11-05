import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.icedtea.chronos.R
import com.icedtea.chronos.view.AddFormActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class AddFormActivityTest {
    private var watchName = 0
    private var watchDes = 0
    private var watchDial = 0
    private var watchColor = 0
    private var watchPrice = 0
    private var addBtn = 0

    @get:Rule
    var activityRule: ActivityScenarioRule<AddFormActivity>
            = ActivityScenarioRule(AddFormActivity::class.java)
    @Before
    fun initValidString() {
        watchName = R.id.watch_brand
        watchDes = R.id.watch_name
        watchDial = R.id.watch_dial
        watchColor = R.id.watch_color
        watchPrice = R.id.price
        addBtn = R.id.add_watch_btn
    }
    @Test
    fun addWatchTest(){
        onView(withId(watchName)).perform(typeText("Rolex"))
        onView(withId(watchDes)).perform(typeText("Daytona 1996"))
        onView(withId(watchDial)).perform(typeText("32"))
        onView(withId(watchColor)).perform(typeText("Gold"))
        onView(withId(watchPrice)).perform(typeText("1992"))
        onView(withId(addBtn)).perform(click())
    }
}
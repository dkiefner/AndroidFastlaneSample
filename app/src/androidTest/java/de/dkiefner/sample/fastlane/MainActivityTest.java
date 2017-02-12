package de.dkiefner.sample.fastlane;


import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import de.dkiefner.sample.fastlane.login.LoginFixture;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest extends BaseTest {

	@Test
	public void thatUsernameIsShown_whenLoginWasSuccessful() {
		// given
		LoginFixture loginFixture = new LoginFixture();
		String expectedUsername = loginFixture.getUsername();

		// when
		loginFixture.load();

		// then
		onView(withId(R.id.username)).check(matches(withText(expectedUsername)));
	}
}

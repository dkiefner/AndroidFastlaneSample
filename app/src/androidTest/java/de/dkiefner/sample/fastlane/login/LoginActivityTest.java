package de.dkiefner.sample.fastlane.login;


import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import de.dkiefner.sample.fastlane.BaseTest;
import de.dkiefner.sample.fastlane.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest extends BaseTest {

	@Test
	public void thatMainActivityIsShown_whenLoginUsingCorrectUsernameAndPassword() {
		// given
		LoginFixture loginFixture = new LoginFixture();

		// when
		loginFixture.load();

		// then
		onView(withId(R.id.activity_main)).check(matches(isDisplayed()));
	}

	@Test
	public void thatErrorIsShown_whenLoginWithInvalidUsernameAndPassword() {
		// given
		LoginFixture loginFixture = new LoginFixture("wrong", "credentials");

		// when
		loginFixture.load();

		// then
		onView(withId(R.id.login_error)).check(matches(isDisplayed()));
	}
}

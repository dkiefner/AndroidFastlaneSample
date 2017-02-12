package de.dkiefner.sample.fastlane.login;

import android.support.test.espresso.ViewInteraction;

import de.dkiefner.sample.fastlane.R;
import de.dkiefner.sample.fastlane.common.Fixture;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

public class LoginFixture implements Fixture {

	private String username = "foo";
	private String password = "bar";

	public LoginFixture() {
	}

	public LoginFixture(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	public void load() {
		ViewInteraction usernameInput = onView(
				allOf(
						withId(R.id.username),
						isDisplayed()
				));
		ViewInteraction passwordInput = onView(
				allOf(
						withId(R.id.password),
						isDisplayed()
				));
		ViewInteraction loginButton = onView(
				allOf(
						withId(R.id.login_action),
						isDisplayed()
				));

		usernameInput.perform(replaceText(username));
		passwordInput.perform(replaceText(password));
		loginButton.perform(click());
	}

	public String getUsername() {
		return username;
	}
}

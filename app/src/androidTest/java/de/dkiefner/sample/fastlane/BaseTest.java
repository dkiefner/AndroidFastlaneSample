package de.dkiefner.sample.fastlane;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;

import de.dkiefner.sample.fastlane.login.LoginActivity;

public class BaseTest {

	@Rule
	public ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<>(LoginActivity.class);
}

package de.dkiefner.sample.fastlane.login;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginViewModelTest {

	private LoginViewModel testee;

	@BeforeClass
	public static void setupClass() {
		RxAndroidPlugins.setInitMainThreadSchedulerHandler(
				__ -> Schedulers.trampoline());
	}

	@Before
	public void setup() {
		testee = new LoginViewModel();
	}

	@Test
	public void thatDoLoginThrowsNoError_whenUsernameAndPasswordAreCorrect() throws Exception {
		// given
		testee.setUsername("foo");
		testee.setPassword("bar");

		TestObserver testObserver = new TestObserver();

		// when
		testee.doLogin().subscribe(testObserver);

		// then
		testObserver.assertNoErrors();
	}

	@Test
	public void thatDoLoginThrowsError_whenUsernameAndPasswordAreNotCorrect() throws Exception {
		// given
		testee.setUsername("wrong");
		testee.setPassword("credentials");

		TestObserver testObserver = new TestObserver();

		// when
		testee.doLogin().subscribe(testObserver);

		// then
		testObserver.assertError(BadCredentialsException.class);
	}

	@Test
	public void thatDoLoginThrowsError_whenUsernameAndPasswordAreEmpty() throws Exception {
		// given
		testee.setUsername("");
		testee.setPassword("");

		TestObserver testObserver = new TestObserver();

		// when
		testee.doLogin().subscribe(testObserver);

		// then
		testObserver.assertError(BadCredentialsException.class);
	}

	@Test
	public void thatDoLoginThrowsError_whenUsernameAndPasswordAreNull() throws Exception {
		// given
		TestObserver testObserver = new TestObserver();

		// when
		testee.doLogin().subscribe(testObserver);

		// then
		testObserver.assertError(BadCredentialsException.class);
	}

	@Test
	public void thatDoLoginShowsNoError_whenUsernameAndPasswordAreCorrect() throws Exception {
		// given
		testee.setUsername("foo");
		testee.setPassword("bar");

		// when
		testee.doLogin().subscribe(new TestObserver());

		// then
		assertThat(testee.isErrorShown.get()).isFalse();
	}

	@Test
	public void thatDoLoginShowsError_whenUsernameAndPasswordAreNotCorrect() throws Exception {
		// given
		testee.setUsername("wrong");
		testee.setPassword("credentials");

		// when
		testee.doLogin().subscribe(new TestObserver());

		// then
		assertThat(testee.isErrorShown.get()).isTrue();
	}

}
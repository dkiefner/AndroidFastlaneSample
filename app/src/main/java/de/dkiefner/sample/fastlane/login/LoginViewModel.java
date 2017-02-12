package de.dkiefner.sample.fastlane.login;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;

import io.reactivex.Completable;

public class LoginViewModel extends BaseObservable {

	public ObservableBoolean isErrorShown = new ObservableBoolean(false);

	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Completable doLogin() {
		return Completable.fromAction(() -> {
			if (!("foo".equals(username) && "bar".equals(password))) {
				isErrorShown.set(true);
				throw new BadCredentialsException();
			}
		});
	}
}

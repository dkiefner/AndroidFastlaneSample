package de.dkiefner.sample.fastlane;

import android.databinding.BaseObservable;

public class MainViewModel extends BaseObservable {

	private String name;

	public MainViewModel(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}

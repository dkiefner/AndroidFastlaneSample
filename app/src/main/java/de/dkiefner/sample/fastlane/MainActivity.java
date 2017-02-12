package de.dkiefner.sample.fastlane;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import de.dkiefner.sample.fastlane.databinding.MainActivityBinding;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		String username = getIntent().getStringExtra("username");

		MainActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.main_activity);
		MainViewModel viewModel = new MainViewModel(username);
		binding.setViewModel(viewModel);
	}
}

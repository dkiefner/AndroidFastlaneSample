package de.dkiefner.sample.fastlane.login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;

import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import de.dkiefner.sample.fastlane.MainActivity;
import de.dkiefner.sample.fastlane.R;
import de.dkiefner.sample.fastlane.databinding.LoginActivityBinding;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class LoginActivity extends AppCompatActivity {

	private final CompositeDisposable disposables = new CompositeDisposable();
	private LoginViewModel viewModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LoginActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.login_activity);
		viewModel = new LoginViewModel();
		binding.setViewModel(viewModel);
		binding.loginAction.setOnClickListener(v -> onLoginCLick());

		ButterKnife.bind(this);
	}

	@Override
	protected void onDestroy() {
		disposables.clear();
		super.onDestroy();
	}

	@OnTextChanged(R.id.username)
	public void onUsernameChanged(Editable editable) {
		viewModel.setUsername(editable.toString());
	}

	@OnTextChanged(R.id.password)
	public void onPasswordChanged(Editable editable) {
		viewModel.setPassword(editable.toString());
	}

	private void openMainActivity() {
		Intent openMain = new Intent(this, MainActivity.class);

		openMain.putExtra("username", viewModel.getUsername());

		startActivity(openMain);
		finish();
	}

	private void onLoginCLick() {
		disposables.add(viewModel.doLogin()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::onLoginSuccess, this::onLoginError));
	}

	private void onLoginSuccess() {
		openMainActivity();
	}

	private void onLoginError(Throwable throwable) {
	}

}

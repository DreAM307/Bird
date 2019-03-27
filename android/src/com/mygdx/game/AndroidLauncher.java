package com.mygdx.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygdx.game.Flappy;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useCompass = false; // выключается компас для меньшей траты батареи устройства
		config.useAccelerometer = false; // выключается акселерометр для меньшей траты батареи устройства
		initialize(new Flappy(), config);
	}
}

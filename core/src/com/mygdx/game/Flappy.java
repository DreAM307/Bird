package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.MenuState;

public class Flappy extends ApplicationAdapter { // класс будет унаследовать из класса ApplicationAdapter
	public static final int WIDTH = 480; // константа, где хранится ширина экрана
	public static final int HEIGHT = 800; // константа, где хранится высота экрана
	public static final String TITLE = "Flappy"; // константа, где хранится название окна
    private GameStateManager gsm; // объявление приватной переменной gsm
	private SpriteBatch batch; // объявление приватной переменной batch
    private Music music; // объявление приватной переменной music
	
	@Override // Имплементированный метод
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager(); // объявление нового объекта класса
        Gdx.gl.glClearColor(1, 0, 0, 1); // очищение экрана
        gsm.push(new MenuState(gsm)); // создание нового экрана меню и помещение его в вершину стека
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3")); // добавление фоновой музыки
		music.setLooping(true); // бесконечный повтор музыки
		music.setVolume(0.15f); // установка громкости на значении 15 процентов
		music.play(); // запуск музыки при включении приложения
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime()); // объявление метода update класса GameStateManager, т.е. для экрана, который виден пользователю
		// куда передаём метод, который возвращает время, прошедшее между последним и текущим кадром в секундах
		gsm.render(batch); // выполнение рендера класса GameStateManager, на вход передаём SpriteBatch
	}

	@Override
	public void dispose() {
		super.dispose();
		music.dispose();
	}
}

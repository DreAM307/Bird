package com.mygdx.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {

    protected OrthographicCamera camera; // создание окна в игровой мир
    protected Vector3 mouse; // Vector3 - используется для ортографической камеры, которая поддерживает 3D
    protected GameStateManager gsm; // управление окнами, состояниями игры

    public State (GameStateManager gsm){ // конструктор для класса State
        this.gsm = gsm;
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }

    protected abstract void handleInput(); // метод для распазнования нажатия
    public abstract void update(float dt); // метод для обновления картинки через определённое количество времени
    public abstract void render(SpriteBatch sb); // метод, который будет рисовать экран, на вход чего идёт SpriteBatch
    // где SpriteBatch - класс, который предоставляет текстуру и её координаты для рисования
    public abstract void dispose(); // освобождает ресурсы путём уничтожения экземпляра класса, который не будет использоваться в дальнейшем
}

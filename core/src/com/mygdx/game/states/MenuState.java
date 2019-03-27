package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Flappy;

public class MenuState extends State { // класс будет унаследовать из класса State

    private Texture background; // объявление текстуры фона
    private Texture logo; // объявление текстуры старта игры

    public MenuState(GameStateManager gsm) { //создание конструктора
        super(gsm);
        camera.setToOrtho(false, Flappy.WIDTH / 2, Flappy.HEIGHT / 2); // инициализируем камеру для запуска игры в нужном разрешении
        background = new Texture("bg.png"); // создание текстуры фона
        logo = new Texture("logo.png"); // создание текстуры старта игры
    }

    @Override // Имплементированный метод из State
    protected void handleInput() {  //обрабатывание нажатия на экран
        if(Gdx.input.justTouched()){ // проверяем, было ли нажатие на экран
            gsm.set(new PlayState(gsm)); // убираем верхний экран из стека и перемещаем PlayState в вершину стека, он станет виден пользователю
        }

    }

    @Override
    public void update(float dt) {
        handleInput(); // вызов метода handleInput

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined); // установка матрицы проекции
        sb.begin(); // начинаем рисование
        sb.draw(background, 0, 0); // отрисовываем текстуру фона
        sb.draw(logo, camera.position.x - logo.getWidth() / 2, camera.position.y - 125); // отрисовываем текстуру старта игры
        sb.end(); // заканчиваем рисование

    }

    @Override
    public void dispose() {
        background.dispose(); // высвобождаем текстуру из памяти
        logo.dispose(); // высвобождаем текстуру из памяти

    }
}

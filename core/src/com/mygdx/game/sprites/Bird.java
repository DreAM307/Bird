package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird {
    private static final int MOVEMENT = 100; // константа движения птицы
    private static final int GRAVITY = -15; // константа силы тяжести
    private Vector3 position; // переменная для хранения позиции
    private Vector3 velosity; // переменная для хранения скорости
    private Rectangle bounds; // объявление прямоугольника для птицы
    private Animation birdAnimation; // объявление анимации для птицы
    private Sound flap; // объявление звука крыльев
    private Texture texture; // объявление текстур

    public Bird(int x, int y){ // конструктор для птицы
        position = new Vector3(x, y, 0); // задание направления движения
        velosity = new Vector3(0, 0, 0); // задание скорости движения
        texture = new Texture("birdanimation.png"); // создание текстуры птицы
        birdAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);
        // при создании анимации передаём текстуру с кадром анимации 3 и длительностью пол секунды
        bounds = new Rectangle(x, y, texture.getWidth() / 3 , texture.getHeight()); // создание прямоугольника птицы
        flap = Gdx.audio.newSound(Gdx.files.internal("wing.ogg")); // инициализация звука крыльев
    }

    public TextureRegion getBird() { // возвращение текущего кадра анимации
        return birdAnimation.getFrame();
    }

    public Vector3 getPosition() { // геттер для позиции птицы
        return position;
    }

    public void update(float dt){ // создание силы тяжести для полёта для каждого момента времени
        birdAnimation.update(dt); // вызов метода анимации
        if (position.y > 0) // условие для движения птицы
            velosity.add(0, GRAVITY, 0); // метод add добавляет значение к константе GRAVITY переменной y
        velosity.scl(dt); // умножаем вектор скорости на промежуток времени
        position.add(MOVEMENT * dt, velosity.y, 0); // добавление новой координаты y положения птицы на экране
        if (position.y < 80) // условие, чтобы птица не вылетала за пределы земли
            position.y = 80;

        velosity.scl(1 / dt); // повторяем метод scale для velosity, тем самым скорость птицы будет изменяться со временем
        bounds.setPosition(position.x, position.y); // задание позиции
    }
    public void jump(){ // метод, с помощью которого птица будет совершать движение
        velosity.y = 250; // присваиваем коорденате y вектора скорости значение 250
        flap.play(); // воспроизведение звука крыльев при каждом нажатии
    }

    public Rectangle getBounds(){ // доступ к прямоугольнику из других классов
        return bounds;
    }

    public void dispose() {
        texture.dispose();
        flap.dispose();
    }
}

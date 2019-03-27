package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube {

    public static final int TUBE_WIDTH = 52; // константа ширины трубы
    public static final int FLUCTUATION = 130; // константа диапозона создания труб
    public static final int TUBE_GAP = 100; // константа значения высота просвета между трубами
    public static final int LOWEST_OPENING = 120; // константа хранения нижней границы просвета
    private Texture toptube, bottomtube; // объявление текстур верхний и нижней трубы
    private Vector2 posTopTube, posBotTube; // объект класса Vector2, т.к. трубы будут движущимеся
    private Random rand; // объект класса рандом для создания труб на разной высоте
    private Rectangle boundsTop, boundsBot; // объявление прямоугольников для труб

    public Texture getToptube() { // 4 геттера создаются для того, чтобы был доступ к переменным в других классах
        return toptube;
    }

    public Texture getBottomtube() {
        return bottomtube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBotTube() {
        return posBotTube;
    }

    public Tube(float x) { // конструктор для труб
        toptube = new Texture("toptube.png"); // создание текстуры для верхней трубы
        bottomtube = new Texture("bottomtube.png"); // создание текстуры для нижней трубы
        rand = new Random(); // объявление рандома создания труб

        posTopTube = new Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING); // верхняя труба будет создаваться с помощью рандома по высоте
        posBotTube = new Vector2(x, posTopTube.y - TUBE_GAP - bottomtube.getHeight()); // нижняя труба создаётся на фиксированной высоте

        boundsTop = new Rectangle(posTopTube.x, posTopTube.y, toptube.getWidth(), toptube.getHeight()); // создание прямоугольника для верхней трубы
        boundsBot = new Rectangle(posBotTube.x, posBotTube.y, bottomtube.getWidth(), bottomtube.getHeight()); // создание прямоугольника для нижней трубы
    }

    public void reposition(float x){ // метод для движения труб
        posTopTube.set(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING ); // используются те же параметры, что и в конструкторе
        posBotTube.set(x, posTopTube.y - TUBE_GAP - bottomtube.getHeight());
        boundsTop.setPosition(posTopTube.x, posTopTube.y); // начальное расположение верхнего прямоугольника, совпадающие с расположенем трубы
        boundsBot.setPosition(posBotTube.x, posBotTube.y); // начальное расположение нижнего прямоугольника, совпадающие с расположенем трубы

    }

    public boolean collides(Rectangle player){ // определение столкновения птицы с трубами
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }

    public void dispose() {
        toptube.dispose();
        bottomtube.dispose();
    }
}

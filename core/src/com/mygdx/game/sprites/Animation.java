package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.utils.Array;

public class Animation { // класс для анимации
    private Array<TextureRegion> frames; // массив текстур, где будут хранится кадры анимации
    private float maxFrameTime; // длительность отображения одного кадра
    private float currentFlameTime; // длительность отображения текущего кадра
    private int frameCount; // кол-во кадров анимации
    private int frame; // отдельный кадр анимации

    public Animation(TextureRegion region, int frameCount, float cycleTime){ // конструктор, в котором будет задаваться region текстур,
        // кол-во кадров анимации и длительность цикла анимации
        frames = new Array<TextureRegion>(); // объявляем массив текстур
        int frameWidth = region.getRegionWidth() / frameCount; // определение ширины кадра
        for (int i = 0; i < frameCount; i++){ // перебираем картинки на текстуре по очереди используя их для кадров анимации
            frames.add(new TextureRegion(region, i * frameWidth, 0 , frameWidth, region.getRegionHeight()));
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }
    public void update(float dt){
        currentFlameTime += dt;
        if (currentFlameTime > maxFrameTime){ // если длительность отображения текущего кадра больше максимального, то увеличиваем номер кадра, пока числа кадров
            // не достигнет установленного
            frame++;
            currentFlameTime = 0;
        }
        if (frame >= frameCount)
            frame = 0;
    }
    public TextureRegion getFrame(){ // метод получения текущего кадра анимации
        return  frames.get(frame);
    }
}

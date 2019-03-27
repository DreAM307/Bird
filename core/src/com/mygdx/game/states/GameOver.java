package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Flappy;

public class GameOver extends State {

    private Texture background;
    private Texture gameover;
    private Texture start;

    public GameOver(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, Flappy.WIDTH / 2, Flappy.HEIGHT / 2);
        background = new Texture("bg.png");
        gameover = new Texture("gameover.png"); // создание текстуры окончания игры
        start = new Texture("start.png"); // создание текстуры ч-б птицы
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm)); // возврат к экрану PlayState при нажатии
        }

    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(start, camera.position.x - start.getWidth() / 2, camera.position.y - 75); // отрисовываем ч-б птицу
        sb.draw(gameover, camera.position.x - gameover.getWidth() / 2, camera.position.y + 50); // отрисовываем текстуру оповещения конца игры
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        gameover.dispose();

    }
}

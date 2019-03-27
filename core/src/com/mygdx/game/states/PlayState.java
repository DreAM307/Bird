package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Flappy;
import com.mygdx.game.sprites.Bird;
import com.mygdx.game.sprites.Tube;

public class PlayState extends State { // класс будет унаследовать из класса State

    private static final int TUBE_SPACING = 125; // константа расстояния между появляющимися трубами
    private static final int TUBE_COUNT = 4; // константа кол-ва труб, которые будут появляться последовательно через TUBE_SPACING
    private static final int GROUND_Y_OFFSET = -30; // константа для указания координаты Y
    private Bird bird; // объявление птицы
    private Texture bg; // объявление текстуры фона
    private Texture ground; // объявление текстуры земли
    private Vector2 groundPos1, groundPos2; // объявление двух переменных для движения земли
    private Array<Tube> tubes; // объявление массива для труб, т.к. их больше 1

    public PlayState(GameStateManager gsm) { //создание конструктора
        super(gsm);
        bird = new Bird(50, 200); // создаем экземпляр птицы в точке
        camera.setToOrtho(false, Flappy.WIDTH / 2, Flappy.HEIGHT / 2); // задаем область обзора для ортографической камеры
        // а именно отцентрируем по разрешению экрана
        bg = new Texture("bg.png"); // создание текстуры фона
        ground = new Texture("ground.png"); // создание текстуры земли
        groundPos1 = new Vector2(camera.position.x - camera.viewportWidth / 2, GROUND_Y_OFFSET);  // создание первой земли
        groundPos2 = new Vector2((camera.position.x - camera.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET); // создание второй земли, которая будет идти впритык с первой

        tubes = new Array<Tube>(); // присваивание переменной

        for (int i = 0; i < TUBE_COUNT; i++){ // цикл, который создаёт трубы до того момента, как кол-во труб не достигнет TUBE_COUNT
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override // Имплементированный метод из State
    protected void handleInput() { // вызов метода jump по нажатию на экран
        if (Gdx.input.justTouched())
            bird.jump();

    }

    @Override
    public void update(float dt) {
        handleInput(); // вызов метода handleInput
        updateGround(); // вызов метода updateGround
        bird.update(dt); // вызов метода класса bird
        camera.position.x = bird.getPosition().x + 80; // привязка камеры к позиции птицы

        for (int i = 0; i < tubes.size; i++){

            Tube tube = tubes.get (i);

            if (camera.position.x - (camera.viewportWidth / 2) > tube.getPosTopTube().x + tube.getToptube().getWidth()){ // условие движения камеры
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT)); // пока есть условие, выполняется метод reposition
            }

            if (tube.collides(bird.getBounds())) // при обнаружении столкновения происходит переход к окну GameOver
                gsm.set(new GameOver(gsm));
        }
        camera.update(); // вызов обновления камеры

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined); // матрица проекции для камеры
        sb.begin();
        sb.draw(bg, camera.position.x - (camera.viewportWidth / 2), 0); // отрисовываем фон по центру области обзора камеры
        sb.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y); // передаём для отрисовки птицы её текстуру и позицию из класса bird
        for (Tube tube : tubes){ // цикл прорисовки труб
            sb.draw(tube.getToptube(), tube.getPosBotTube().x, tube.getPosTopTube().y); // отрисовываем верхние трубы
            sb.draw(tube.getBottomtube(), tube.getPosBotTube().x, tube.getPosBotTube().y); // отрисовываем нижние трубы
        }
        sb.draw(ground, groundPos1.x, groundPos1.y); // отрисовываем первую землю
        sb.draw(ground, groundPos2.x, groundPos2.y); // отрисовываем вторую землю
        sb.end();

    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        ground.dispose();
        for (Tube tube : tubes)
            tube.dispose();
    }

    private void updateGround(){ // метод для создания непрерывности земли
        if (camera.position.x - (camera.viewportWidth / 2) > groundPos1.x + ground.getWidth()) // когда первая текстура уходит с экрана она добавляется после второй текстуры
            groundPos1.add(ground.getWidth() * 2, 0);
        if (camera.position.x - (camera.viewportWidth / 2) > groundPos2.x + ground.getWidth()) // когда вторая текстура уходит с экрана она добавляется после первой текстуры
            groundPos2.add(ground.getWidth() * 2, 0);
    }
}

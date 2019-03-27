package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {

    private Stack<State> states; // объявление массива Stack состояний states, где Stack
    // подкласс класса Vector3, реализующий состояние последний вошёл - первый вышел

    public GameStateManager(){
        states = new Stack<State>(); // конструктор, создающий пустой Stack
    }

    public void push(State state){ // метод для помещения элемента в вершину стека состояний экрана
        states.push(state);
    }

    public void pop(){ // метод для извлечения элемента стека и его удаление
        states.pop().dispose();
    }

    public void set(State state){ // метод для удаления из стека верхний экран и очищения его ресурсов и помещения следующего экрана в вершину стека
        states.pop().dispose();
        states.push(state);
    }

    public void update(float dt){ // метод, который через промежуток времени будет выполнять метод peek
        states.peek().update(dt); // peek - возвращает верхний элемент стека, при этом не удаляя его из стека
    }

    public void render(SpriteBatch sb){ // метод, который принимает и отрисовывает верхний элемент стека
        states.peek().render(sb);
    }
}

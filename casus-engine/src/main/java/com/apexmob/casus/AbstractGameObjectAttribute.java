package com.apexmob.casus;

public abstract class AbstractGameObjectAttribute<T extends GameObject> implements GameObjectAttribute<T> {

    private T gameObject;

    @Override
    public T getGameObject() {
        return gameObject;
    }

    @Override
    public void setGameObject(T gameObject) {
        this.gameObject = gameObject;
    }
}

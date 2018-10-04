package com.apexmob.casus;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * AbstractGameObject is an abstract implementation of the {@link GameObject} interface that provides basic functionality
 * that most implementations will need.
 *
 * @author Chris Kirk
 * @since 1.0
 */
public class AbstractGameObject implements GameObject {
    private String name;
    private String description;
    private final GameObjectAttributeManager attributeManager;
    private List<Behavior<GameObject>> behaviors = new ArrayList<>();
    private GameEngine gameEngine;

    /**
     * Construct a new instance.
     */
    protected AbstractGameObject() {
        attributeManager = new GameObjectAttributeManager(this);
    }

    /**
     * Construct a new instance.
     * @param name The name of the object.
     * @param description A short description for the object.
     */
    protected AbstractGameObject(String name, String description) {
        this();
        this.name = name;
        this.description = description;
    }

    /**
     * Construct a new instance.
     * @param name The name of the object.
     * @param description A short description for the object.
     * @param attributeMap A Map containing the the attribute class as a key and the attribute instance as the Map value.
     */
    protected AbstractGameObject(String name, String description, Map<Class<? extends GameObjectAttribute>, GameObjectAttribute> attributeMap) {
        this(name, description);
        putAllAttributes(attributeMap);
    }

    /**
     * Construct a new instance.
     * @param name The name of the object.
     * @param description A short description for the object.
     * @param attributeMap A Map containing the the attribute class as a key and the attribute instance as the Map value.
     * @param behaviors The object's behaviors.
     */
    protected AbstractGameObject(String name, String description, Map<Class<? extends GameObjectAttribute>, GameObjectAttribute> attributeMap, List<Behavior<GameObject>> behaviors) {
        this(name, description, attributeMap);
        setBehaviors(behaviors);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supportsAttribute(Class<? extends GameObjectAttribute> attributeClass) {
        return attributeManager.containsAttribute(attributeClass);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObjectAttribute getAttribute(Class<? extends GameObjectAttribute> attributeClass) {
        return attributeManager.getAttribute(attributeClass);
    }

    /**
     * Set the attribute instance for a given attribute that the object supports.
     * @param attributeClass The class of the GameObjectAttribute to retrieve.
     * @param attribute The attribute instance of the given class.
     */
    protected void putAttribute(Class<? extends GameObjectAttribute> attributeClass, GameObjectAttribute attribute) {
        attributeManager.putAttribute(attributeClass, attribute);
    }

    /**
     * Remove the attribute instance for the provided attribute class.
     * @param attributeClass The class of the GameObjectAttribute to remove.
     * @return The Attribute instance if present and removed, otherwise null.
     */
    protected GameObjectAttribute removeAttribute(Class<? extends GameObjectAttribute> attributeClass) {
        return attributeManager.removeAttribute(attributeClass);
    }

    /**
     * Set multiple attributes for the object to support.
     * @param attributeMap A Map containing the the attribute class as a key and the attribute instance as the Map value.
     */
    protected void putAllAttributes(Map<Class<? extends GameObjectAttribute>, GameObjectAttribute> attributeMap) {
        attributeManager.putAllAttributes(attributeMap);
    }

    /**
     * Retrieve the behaviors for the object.
     * @return The behaviors.
     */
    protected List<Behavior<GameObject>> getBehaviors() {
        return behaviors;
    }

    /**
     * Set a list of behaviors for the object.
     * @param behaviors The behaviors.
     */
    protected void setBehaviors(List<Behavior<GameObject>> behaviors) {
        this.behaviors = behaviors;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(GameEngine game) {
        this.gameEngine = game;
        if (behaviors != null) {
            for (Behavior behavior : behaviors) {
                behavior.start(game, this);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop(GameEngine game) {
        if (behaviors != null) {
            for (Behavior behavior : behaviors) {
                behavior.stop(game, this);
            }
        }
        this.gameEngine = null;
    }

    /**
     * Determine if the game object is running (started but not stopped).
     * @return True if the object is running, otherwise false.
     */
    protected boolean isRunning() {
        return gameEngine != null;
    }

    /**
     * {@inheritDoc}
     */
    @JsonIgnore
    @Override
    public GameEngine getGameEngine() {
        return gameEngine;
    }
}

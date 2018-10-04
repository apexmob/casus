package com.apexmob.casus;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * GameObject represents the basic interface for all objects managed within the game engine.
 *
 * @author Chris Kirk
 * @since 1.0
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
public interface GameObject {

    /**
     * Retrieve the name of the object.
     * @return The name of the object.
     */
    String getName();

    /**
     * Set the name of the object.
     * @param name The name of the object.
     */
    void setName(String name);

    /**
     * Retrieve the description for the object.
     * @return The description for the object.
     */
    String getDescription();

    /**
     * Set the object's description.
     * @param description The description for the object.
     */
    void setDescription(String description);

    /**
     * Retrieve whether the object supports a given attribute.  Supporting an attribute indicates that a call to
     * 'getAttribute' with the same class name will return a non-null response.
     * @param attributeClass The GameObjectAttribute class for which to check support.
     * @return True, if the attribute is supported, otherwise false.
     */
    boolean supportsAttribute(Class<? extends GameObjectAttribute> attributeClass);

    /**
     * Retrieve the attribute instance for a given attribute that the object supports.
     * @param attributeClass The class of the GameObjectAttribute to retrieve.
     * @return The attribute instance if supported, otherwise null.
     */
    GameObjectAttribute getAttribute(Class<? extends GameObjectAttribute> attributeClass);

    /**
     * Initialize any aspects of the object that require access to the game engine.
     * @param gameEngine The game engine in which the object is running.
     */
    void start(GameEngine gameEngine);

    /**
     * Clean up and object resources with references to capabilities provided by the game engine.
     * @param gameEngine The game engine in which the object is running.
     */
    void stop(GameEngine gameEngine);

    /**
     * Retrieve the game engined in which the object is running.
     * @return The game engine in which the object is running.
     */
    GameEngine getGameEngine();

}

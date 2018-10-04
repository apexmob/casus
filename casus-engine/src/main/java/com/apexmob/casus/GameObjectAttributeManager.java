package com.apexmob.casus;

import java.util.Hashtable;
import java.util.Map;

/**
 * GameObjectAttributeManager is a utility class used to store and manange GameObjectAttribute instances.
 *
 * @author Chris Kirk
 * @since 1.0
 */
public class GameObjectAttributeManager {

    private final GameObject gameObject;
    private final Map<Class<? extends GameObjectAttribute>, GameObjectAttribute> gameAttributes = new Hashtable<>();

    /**
     * Construct a new instance for the provided object.
     * @param gameObject The object to which the managed attributes apply.
     */
    public GameObjectAttributeManager(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    /**
     * Set an attribute.
     * @param attributeClass The class of the attribute (typically an interface).
     * @param attribute The attribute instance.
     */
    public void putAttribute(Class<? extends GameObjectAttribute> attributeClass, GameObjectAttribute attribute) {
        attribute.setGameObject(gameObject);
        gameAttributes.put(attributeClass, attribute);
    }

    /**
     * Get an attribute instance for the specified attribute class.
     * @param attributeClass The class of the attribute.
     * @return The attribute instance.
     */
    public GameObjectAttribute getAttribute(Class<? extends GameObjectAttribute> attributeClass) {
        return gameAttributes.get(attributeClass);
    }

    /**
     * Determine if an attribute is present for the provided attribute class.
     * @param attributeClass The class of the attribute.
     * @return True if an attribute instance exists, otherwise false.
     */
    public boolean containsAttribute(Class<? extends GameObjectAttribute> attributeClass) {
        return gameAttributes.containsKey(attributeClass);
    }

    /**
     * Remove the attribute instance for the provided attribute class.
     * @param attributeClass The class of the attribute.
     * @return The attribute instance that was present and removed, otherwise null.
     */
    public GameObjectAttribute removeAttribute(Class<? extends GameObjectAttribute> attributeClass) {
        return gameAttributes.remove(attributeClass);
    }

    /**
     * Set multiple attribute instances.
     * @param attributeMap A map of attribute instances keyed by the corresponding attribute class (typically an interface).
     */
    public void putAllAttributes(Map<Class<? extends GameObjectAttribute>, GameObjectAttribute> attributeMap) {
        for(Map.Entry<Class<? extends GameObjectAttribute>, GameObjectAttribute> entry : attributeMap.entrySet()) {
            putAttribute(entry.getKey(), entry.getValue());
        }
    }
}

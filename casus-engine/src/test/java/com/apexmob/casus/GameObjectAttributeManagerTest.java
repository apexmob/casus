package com.apexmob.casus;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Hashtable;
import java.util.Map;

import static junit.framework.TestCase.*;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.same;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public class GameObjectAttributeManagerTest {

    private GameObject gameObject;
    private GameObjectAttributeManager manager;
    private GameObjectAttribute attribute;

    @Before
    public void before() {
        gameObject = Mockito.mock(GameObject.class);
        attribute = Mockito.mock(GameObjectAttribute.class);

        manager = new GameObjectAttributeManager(gameObject);
    }

    @Test
    public void testPutAttribute_whenAttributeProvided_thenGameObjectSetOnAttribute() {
        manager.putAttribute(GameObjectAttribute.class, attribute);

        Mockito.verify(attribute, Mockito.times(1)).setGameObject(same(gameObject));
    }

    @Test
    public void testGetAttribute_whenAttributeSet_thenReturnAttribute() {
        manager.putAttribute(GameObjectAttribute.class, attribute);
        assertSame(attribute, manager.getAttribute(GameObjectAttribute.class));
    }

    @Test
    public void testGetAttribute_whenAttributeRemoved_thenReturnNull() {
        assertNull(manager.getAttribute(GameObjectAttribute.class));
    }

    @Test
    public void testRemoveAttribute_whenAttributeSet_thenReturnAttribute() {
        manager.putAttribute(GameObjectAttribute.class, attribute);
        assertSame(attribute, manager.removeAttribute(GameObjectAttribute.class));
    }

    @Test
    public void testRemoveAttribute_whenAttributeNotSet_thenReturnNull() {
        assertNull(manager.removeAttribute(GameObjectAttribute.class));
    }

    @Test
    public void testRemoveAttribute_whenAttributeRemoved_thenReturnNull() {
        manager.putAttribute(GameObjectAttribute.class, attribute);
        manager.removeAttribute(GameObjectAttribute.class);

        assertNull(manager.removeAttribute(GameObjectAttribute.class));
    }

    @Test
    public void testContainsAttribute_whenAttributeSet_thenReturnTrue() {
        manager.putAttribute(GameObjectAttribute.class, attribute);

        assertTrue(manager.containsAttribute(GameObjectAttribute.class));
    }

    @Test
    public void testContainsAttribute_whenAttributeNotSet_thenReturnFalse() {
        assertFalse(manager.containsAttribute(GameObjectAttribute.class));
    }

    @Test
    public void testContainsAttribute_whenAttributeRemoved_thenReturnFalse() {
        manager.putAttribute(GameObjectAttribute.class, attribute);
        manager.removeAttribute(GameObjectAttribute.class);

        assertFalse(manager.containsAttribute(GameObjectAttribute.class));
    }

    @Test
    public void testContainsAttribute_whenAttributesProvided_thenReturnTrueForAll() {
        Map map = new Hashtable();
        map.put(GameObjectAttribute.class, attribute);

        manager.putAllAttributes(map);

        assertTrue(manager.containsAttribute(GameObjectAttribute.class));
    }

    @Test
    public void testPutAllAttributes_whenAttributesProvided_thenGameObjectSetOnAttributes() {
        Map map = new Hashtable();
        map.put(GameObjectAttribute.class, attribute);

        manager.putAllAttributes(map);

        Mockito.verify(attribute, Mockito.times(1)).setGameObject(same(gameObject));
    }
}

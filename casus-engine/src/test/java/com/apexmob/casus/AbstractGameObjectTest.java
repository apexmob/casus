package com.apexmob.casus;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.same;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public class AbstractGameObjectTest {

    private MockGameObject object;
    private Behavior<GameObject> behavior;
    private GameObjectAttribute attribute;
    private GameEngine engine;

    @Before
    public void before() {
        object = new MockGameObject();
        behavior = Mockito.mock(Behavior.class);
        attribute = Mockito.mock(GameObjectAttribute.class);
        engine = Mockito.mock(GameEngine.class);
    }

    @Test
    public void testConstructor_whenCalled_thenPopulateNothing() {
        assertNull(object.getName());
        assertNull(object.getDescription());
        assertEquals(0, object.getBehaviors().size());
        assertNull(object.getGameEngine());
    }

    @Test
    public void testNameDescConstructor_whenNameDescProvided_thenSetNameDesc() {
        MockGameObject obj = new MockGameObject("name", "description");

        assertEquals("name", obj.getName());
        assertEquals("description", obj.getDescription());
        assertEquals(0, obj.getBehaviors().size());
        assertNull(obj.getGameEngine());
    }

    @Test
    public void testNameDescAttributesConstructor_whenAllProvided_thenSetAll() {
        Map<Class<? extends GameObjectAttribute>, GameObjectAttribute> attributeMap = new Hashtable<Class<? extends GameObjectAttribute>, GameObjectAttribute>();
        attributeMap.put(GameObjectAttribute.class, attribute);
        MockGameObject obj = new MockGameObject("name", "description", attributeMap);

        assertEquals("name", obj.getName());
        assertEquals("description", obj.getDescription());
        assertEquals(0, obj.getBehaviors().size());
        assertNull(obj.getGameEngine());
        assertTrue(obj.supportsAttribute(GameObjectAttribute.class));
    }

    @Test
    public void testNameDescAttributesBehaviorsConstructor_whenAllProvided_thenSetAll() {
        Map<Class<? extends GameObjectAttribute>, GameObjectAttribute> attributeMap = new Hashtable<Class<? extends GameObjectAttribute>, GameObjectAttribute>();
        attributeMap.put(GameObjectAttribute.class, attribute);

        List<Behavior<GameObject>> behaviors = new ArrayList<>();
        behaviors.add(behavior);

        MockGameObject obj = new MockGameObject("name", "description", attributeMap, behaviors);

        assertEquals("name", obj.getName());
        assertEquals("description", obj.getDescription());
        assertEquals(1, obj.getBehaviors().size());
        assertNull(obj.getGameEngine());
        assertTrue(obj.supportsAttribute(GameObjectAttribute.class));
    }

    @Test
    public void testSetName_whenCalled_thenSetName() {
        object.setName("test");
        assertEquals("test",  object.getName());
    }

    @Test
    public void testSetDescription_whenCalled_thenSetDescription() {
        object.setDescription("test");
        assertEquals("test", object.getDescription());
    }

    @Test
    public void testSupportsAttribute_whenAttributeNotPresent_thenReturnFalse() {
        assertFalse(object.supportsAttribute(GameObjectAttribute.class));
    }

    @Test
    public void testGetAttribute_whenAttributePresent_thenReturnAttribute() {
        Map<Class<? extends GameObjectAttribute>, GameObjectAttribute> attributeMap = new Hashtable<Class<? extends GameObjectAttribute>, GameObjectAttribute>();
        attributeMap.put(GameObjectAttribute.class, attribute);
        MockGameObject obj = new MockGameObject("name", "description", attributeMap);

        assertSame(attribute, obj.getAttribute(GameObjectAttribute.class));
    }

    @Test
    public void testGetAttribute_whenAttributeNotPresent_thenReturnNull() {
        assertNull(object.getAttribute(GameObjectAttribute.class));
    }

    @Test
    public void testPutAttribute_whenAttributeSet_thenAttributeReadable() {
        object.putAttribute(GameObjectAttribute.class, attribute);
        assertSame(attribute, object.getAttribute(GameObjectAttribute.class));
    }

    @Test
    public void testPutAttributes_whenAttributesSet_thenAttributesReadable() {
        Map<Class<? extends GameObjectAttribute>, GameObjectAttribute> attributeMap = new Hashtable<Class<? extends GameObjectAttribute>, GameObjectAttribute>();
        attributeMap.put(GameObjectAttribute.class, attribute);

        object.putAllAttributes(attributeMap);

        assertSame(attribute, object.getAttribute(GameObjectAttribute.class));
    }

    @Test
    public void testRemoteAttribute_whenPresent_thenReturnAttribute() {
        object.putAttribute(GameObjectAttribute.class, attribute);
        assertSame(attribute, object.removeAttribute(GameObjectAttribute.class));
    }

    @Test
    public void testRemoteAttribute_whenNotPresent_thenReturnNull() {
        assertNull(object.removeAttribute(GameObjectAttribute.class));
    }
    @Test
    public void testRemoteAttribute_whenPresent_thenAttributeNotReadable() {
        object.putAttribute(GameObjectAttribute.class, attribute);
        object.removeAttribute(GameObjectAttribute.class);

        assertNull(object.removeAttribute(GameObjectAttribute.class));
    }

    @Test
    public void testSetBehaviors_whenBehaviorsSet_thenBehaviorsReadable() {
        List<Behavior<GameObject>> behaviors = new ArrayList<>();
        behaviors.add(behavior);

        object.setBehaviors(behaviors);

        assertSame(behaviors, object.getBehaviors());
    }

    @Test
    public void testStart_whenStarted_thenGameObjectPopulated() {
        object.start(engine);

        assertSame(engine, object.getGameEngine());
    }

    @Test
    public void testStart_whenStarted_thenBehaviorsStarted() {
        List<Behavior<GameObject>> behaviors = new ArrayList<>();
        behaviors.add(behavior);

        object.setBehaviors(behaviors);

        object.start(engine);

        Mockito.verify(behavior, Mockito.times(1)).start(same(engine), same(object));
    }

    @Test
    public void testStop_whenStop_thenBehaviorsStopped() {
        List<Behavior<GameObject>> behaviors = new ArrayList<>();
        behaviors.add(behavior);

        object.setBehaviors(behaviors);

        object.start(engine);
        object.stop(engine);

        Mockito.verify(behavior, Mockito.times(1)).stop(same(engine), same(object));
    }

    @Test
    public void testIsRunning_whenNotStarted_thenReturnFalse() {
        assertFalse(object.isRunning());
    }

    @Test
    public void testIsRunning_whenStarted_thenReturnTrue() {
        object.start(engine);
        assertTrue(object.isRunning());
    }

    @Test
    public void testIsRunning_whenStartedAndStopped_thenReturnFalse() {
        object.start(engine);
        object.stop(engine);
        assertFalse(object.isRunning());
    }

    private class MockGameObject extends AbstractGameObject {
        public MockGameObject() {
        }

        public MockGameObject(String name, String description) {
            super(name, description);
        }

        public MockGameObject(String name, String description, Map<Class<? extends GameObjectAttribute>, GameObjectAttribute> attributeMap) {
            super(name, description, attributeMap);
        }

        public MockGameObject(String name, String description, Map<Class<? extends GameObjectAttribute>, GameObjectAttribute> attributeMap, List<Behavior<GameObject>> behaviors) {
            super(name, description, attributeMap, behaviors);
        }

        @Override
        public void putAttribute(Class<? extends GameObjectAttribute> attributeClass, GameObjectAttribute attribute) {
            super.putAttribute(attributeClass, attribute);
        }

        @Override
        public GameObjectAttribute removeAttribute(Class<? extends GameObjectAttribute> attributeClass) {
            return super.removeAttribute(attributeClass);
        }

        @Override
        public void putAllAttributes(Map<Class<? extends GameObjectAttribute>, GameObjectAttribute> attributeMap) {
            super.putAllAttributes(attributeMap);
        }
    }
}

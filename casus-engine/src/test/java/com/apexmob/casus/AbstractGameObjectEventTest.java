package com.apexmob.casus;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertSame;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public class AbstractGameObjectEventTest {

    private GameObject gameObject;
    private MockGameObjectEvent event;

    @Before
    public void before() {
        gameObject = Mockito.mock(GameObject.class);
        event = new MockGameObjectEvent(gameObject);
    }

    @Test
    public void testConstuctor_whenConstructed_thenGameObjectReadable() {
        assertSame(gameObject, event.getGameObject());
    }

    private class MockGameObjectEvent extends AbstractGameObjectEvent {
        public MockGameObjectEvent(GameObject gameObject) {
            super(gameObject);
        }
    }
}

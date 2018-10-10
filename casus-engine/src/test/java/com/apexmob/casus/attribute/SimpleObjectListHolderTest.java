package com.apexmob.casus.attribute;

import com.apexmob.casus.attribute.event.ObjectListChangedEvent;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public class SimpleObjectListHolderTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private SimpleObjectListHolder<String> holder;
    private MockObjectListChangeListener listener;

    @Before
    public void before() {
        holder = new SimpleObjectListHolder();
        listener = new MockObjectListChangeListener();
    }

    @Test
    public void testGetObjects_whenCreated_thenReturnEmptyList() {
        assertEquals(0, holder.getObjects().size());
    }

    @Test
    public void testGetObject_whenCalled_thenReturnListIsImmutable() {
        thrown.expect(UnsupportedOperationException.class);

        holder.getObjects().add("test");
    }

    @Test
    public void testAddObject_whenObjectAdded_thenListenerCalled() {
        holder.addObjectListChangeListener(listener);

        holder.addObject("test");

        assertEquals(ObjectListChangedEvent.ChangeType.ADDITION, listener.getEvent().getChangeType());
        assertEquals("test", listener.getEvent().getObject());
    }

    @Test
    public void testAddObject_whenObjectAdded_thenGetObjectsReturnsObject() {
        holder.addObject("test");

        List<String> objects = holder.getObjects();
        assertEquals(1, objects.size());
        assertEquals("test", objects.get(0));
    }

    @Test
    public void testRemoveObject_whenObjectRemoved_thenListenerCalled() {
        holder.addObject("test");

        holder.addObjectListChangeListener(listener);

        holder.removeObject("test");

        assertEquals(ObjectListChangedEvent.ChangeType.REMOVAL, listener.getEvent().getChangeType());
        assertEquals("test", listener.getEvent().getObject());
    }

    @Test
    public void testRemoveObject_whenObjectRemoved_thenGetObjectDoesNotReturnObject() {
        holder.addObject("test");
        holder.removeObject("test");

        List<String> objects = holder.getObjects();
        assertEquals(0, objects.size());
    }

    @Test
    public void testRemoveObjectListChangeListener_whenAdded_thenListenerNotNotifiedOnChange() {
        holder.addObjectListChangeListener(listener);
        holder.removeObjectListChangeListener(listener);

        holder.addObject("test");

        assertNull(listener.getEvent());
    }

    static class MockObjectListChangeListener implements ObjectListChangeListener<String> {

        private ObjectListChangedEvent<String> event;

        @Override
        public void onChange(ObjectListChangedEvent<String> event) {
            this.event = event;
        }

        public ObjectListChangedEvent<String> getEvent() {
            return event;
        }
    }
}

package com.apexmob.casus.location;

import com.apexmob.casus.*;
import com.apexmob.casus.Character;
import com.apexmob.casus.action.ActionModule;
import com.apexmob.casus.attribute.Locatable;
import com.apexmob.casus.character.CasusCharacter;
import com.apexmob.casus.character.location.CharacterLocationModule;
import com.apexmob.casus.character.location.CharacterLocationChangedEvent;
import com.apexmob.casus.command.CommandModule;
import com.apexmob.casus.command.CommandService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.util.*;

import static junit.framework.TestCase.assertSame;
import static org.junit.Assert.assertEquals;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public class LocationIntegrationTest {

    private GameEngine gameEngine;
    private Character character;

    @Before
    public void before() {
        character = new CasusCharacter("test");

        gameEngine = new SimpleGameEngine();
        gameEngine.add(new CommandModule());
        gameEngine.add(new LocationModule());
        gameEngine.add(new CharacterLocationModule());
        gameEngine.add(new ActionModule());

        gameEngine.start();
    }

    @After
    public void after() {
        gameEngine.stop();
    }

    @Test
    public void test() {
        CommandService commandService = (CommandService) gameEngine.getService(CommandService.class.getName());
        LocationService locationService = (LocationService) gameEngine.getService(LocationService.class.getName());
        GameEventDispatcher<CharacterLocationChangedEvent> dispatcher = gameEngine.getGameEventDispatcher(CharacterLocationChangedEvent.class);

        TestLocationProvider locationProvider = new TestLocationProvider();
        locationProvider.add("memory://files.system/zones/1/layers/2/locations/old", buildLocation("old", "new", "memory://files.system/zones/1/layers/2/locations/new"));
        locationProvider.add("memory://files.system/zones/1/layers/2/locations/new", buildLocation("new", "old", "memory://files.system/zones/1/layers/2/locations/old"));
        locationService.addObjectProvider(locationProvider);

        Locatable locatable = (Locatable)character.getAttribute(Locatable.class);
        locatable.setLocation(locationProvider.getObject(URI.create("memory://files.system/zones/1/layers/2/locations/old")));

        TestCharacterLocationChangedEventListener listener = new TestCharacterLocationChangedEventListener();
        dispatcher.addListener(listener);

        commandService.sendCommand(character, "move new");

        assertEquals(1, listener.getEvents().size());
        assertEquals("new", listener.getEvents().get(0).getNewLocation().getName());
        assertEquals("old", listener.getEvents().get(0).getOldLocation().getName());
        assertSame(character, listener.getEvents().get(0).getGameObject());

        assertSame(locationProvider.getObject(URI.create("memory://files.system/zones/1/layers/2/locations/new")), locatable.getLocation());
    }

    private Location buildLocation(String name, String exitName, String exitUri) {
        CasusLocation retVal = new CasusLocation();
        retVal.setName(name);

        Set<Exit> exits = new LinkedHashSet<>();
        exits.add(new SimpleExit(exitName, URI.create(exitUri)));
        retVal.setExits(exits);

        return retVal;
    }

    private class TestLocationProvider implements ObjectProvider<Location> {
        private Map<String,Location> locationMap = new Hashtable<>();

        @Override
        public Location getObject(URI uri) {
            return locationMap.get(uri.toString());
        }

        public void add(String uri, Location location) {
            locationMap.put(uri, location);
        }
    }

    private class TestCharacterLocationChangedEventListener implements GameEventListener<CharacterLocationChangedEvent> {

        private List<CharacterLocationChangedEvent> events = new ArrayList<>();

        @Override
        public boolean onEvent(CharacterLocationChangedEvent event) {
            events.add(event);
            return true;
        }

        public List<CharacterLocationChangedEvent> getEvents() {
            return events;
        }
    }

}

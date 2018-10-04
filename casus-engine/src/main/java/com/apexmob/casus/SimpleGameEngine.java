package com.apexmob.casus;

import java.util.*;

/**
 * SimpleGameEngine provides a simple implementation of {@link GameEngine}.
 *
 * @author Chris Kirk
 * @since 1.0
 */
public class SimpleGameEngine implements GameEngine {

    private final Map<String,Service> services = new Hashtable<>();
    private final Map<Class,GameEventDispatcher> eventDispatchers = new Hashtable<>();
    private final Set<Module> modules = Collections.synchronizedSet(new LinkedHashSet<Module>());
    private boolean running = false;

    /**
     * {@inheritDoc}
     */
    @Override
    public void putService(String name, Service service) {
        services.put(name, service);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Service removeService(String name) {
        return services.remove(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Service getService(String name) {
        return services.get(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(Module module) {
        return modules.add(module);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(Module module) {
        return modules.remove(module);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        startModules();
        setRunning(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop() {
        setRunning(false);
        stopModules();
    }

    /**
     * Start all modules in the order in which they were added.
     */
    protected void startModules() {
        for (Module module : modules) {
            module.start(this);
        }
    }

    /**
     * Stop all modules in reverse order of how they were running/added.
     */
    protected void stopModules() {
        List<Module> mods = new ArrayList<>(modules);
        Collections.reverse(mods);
        for (Module module : mods) {
            module.stop(this);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void putGameEventDispatcher(Class eventClass, GameEventDispatcher eventDispatcher) {
        eventDispatchers.put(eventClass, eventDispatcher);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameEventDispatcher removeGameEventDispatcher(Class eventClass) {
        return eventDispatchers.remove(eventClass);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameEventDispatcher getGameEventDispatcher(Class eventClass) {
        return eventDispatchers.get(eventClass);
    }

    /**
     * Retrieve whether the game engine is running.
     * @return True if it is running, otherwise false.
     */
    protected boolean isRunning() {
        return running;
    }

    /**
     * Set whether the game engine is running.
     * @param running Indicator as to whether to consider the game engine running.
     */
    protected void setRunning(boolean running) {
        this.running = running;
    }
}

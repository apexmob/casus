package com.apexmob.casus;

/**
 * BaseModule is an abstract implementation of the {@link Module} interface that serves as a forward compatibility
 * mechanism.  It is recommended that all concrete {@link Module} implementations extend this class.  Future requirements
 * of {@link Module} implementations will be fulfilled through default implementations added to this class.
 *
 * @author Chris Kirk
 * @since 1.0
 */
public abstract class BaseModule implements Module {
    /**
     * {@inheritDoc}
     */
    @Override
    public void start(GameEngine game) {
        //do nothing
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop(GameEngine game) {
        //do nothing
    }
}

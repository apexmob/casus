package com.apexmob.casus.server;

import com.apexmob.casus.GameEvent;
import com.apexmob.casus.Service;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public interface WebSocketUserEventMessagingService extends Service {

    void sendEvent(String user, String Destination, GameEvent event);
}

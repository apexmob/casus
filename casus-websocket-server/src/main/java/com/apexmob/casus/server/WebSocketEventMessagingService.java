package com.apexmob.casus.server;

import com.apexmob.casus.BaseService;
import com.apexmob.casus.GameEvent;
import com.apexmob.casus.Service;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public interface WebSocketEventMessagingService extends Service {

    void sendEvent(String Destination, GameEvent event);
}

package io.ggammu.basicserver;

import java.io.InputStream;

public interface EventHandler {

    String getHandler();

    void handleEvent(InputStream inputStream);

}

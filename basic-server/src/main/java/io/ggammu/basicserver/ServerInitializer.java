package io.ggammu.basicserver;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.util.List;

public class ServerInitializer {

    public static void main(String[] args) {
        int port = 35000;
        System.out.println("Server ON : " + port);
        Reactor reactor = new Reactor(port);

        try{
            Serializer serializer = new Persister();
            File source = new File("HandlerList.xml");
            ServerListData serverListData = serializer.read(ServerListData.class, source);
            for (HandlerListData handlerListData: serverListData.getServer()) {
                if ("server1".equals(handlerListData.getName())) {
                    List<HandlerData> handlerList = handlerListData.getHandler();
                    for (HandlerData handler: handlerList) {
                        try {
                            reactor.registerHandler(handler.getHeader(), (EventHandler) Class.forName(ServerInitializer.class.getPackageName()+ "." + handler.getHandler()).newInstance());
                        } catch(InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        reactor.startServer();
    }

}

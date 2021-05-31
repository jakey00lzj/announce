package com.demo.announce;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.demo.announce.AnnounceConstants.DEFAULT_SERVER_PORT;
import static com.demo.announce.AnnounceConstants.SERVER_PORT_SYSENV_KEY;

public class AnnounceServer implements IServer {

    private final AtomicBoolean shouldStart = new AtomicBoolean(false);

    private IServer server;

    public AnnounceServer() {
        int port = getPort();
        server = new EmbedAnnounceServer(port);
    }

    private int getPort() {
        int port;
        String portStr = System.getProperty(SERVER_PORT_SYSENV_KEY,DEFAULT_SERVER_PORT);
        try {
            port = Integer.parseInt(portStr);
        } catch (Exception e) {
            port = Integer.parseInt(DEFAULT_SERVER_PORT);
        }
        return port;
    }

    public void start() throws Exception {
        if (shouldStart.compareAndSet(false, true)) {
            if (server != null) {
                server.start();

                //todo other...
            }
        }
    }

    @Override
    public void stop() throws Exception {
        if (shouldStart.compareAndSet(true,false )) {
            if (server != null) {
                server.stop();
            }
        }
    }


}

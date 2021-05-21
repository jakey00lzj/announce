package com.demo.announce;

import io.netty.util.internal.StringUtil;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.demo.announce.AnnounceConstants.DEFAULT_SERVER_PORT;
import static com.demo.announce.AnnounceConstants.SERVER_PORT_SYSENV_KEY;

public class AnnounceServer implements IServer {

    private final AtomicBoolean status = new AtomicBoolean(false);

    private IServer server;

    public AnnounceServer() {
        String portStr = System.getProperty(SERVER_PORT_SYSENV_KEY,DEFAULT_SERVER_PORT);
        int port;
        try {
            port = Integer.parseInt(portStr);
        } catch (Exception e) {

        }

//        server = new EmbedAnnounceServer(port);

    }

    public void start() {

        if (status.compareAndSet(false, true)) {

        }

    }

    @Override
    public void stop() throws Exception {

    }


}

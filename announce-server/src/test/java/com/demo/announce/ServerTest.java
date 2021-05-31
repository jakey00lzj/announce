package com.demo.announce;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ServerTest {

    @Test
    public void serverTest() throws Exception {

        AnnounceServer announceServer = new AnnounceServer();
        announceServer.start();

    }
}

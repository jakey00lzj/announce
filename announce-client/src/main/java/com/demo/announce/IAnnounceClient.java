package com.demo.announce;


/**
 * support command:  start(id) , stop(id) , startall() ,status()
 */
public interface IAnnounceClient {

    boolean start(String id);

    boolean stop(String id);

    boolean status(String id);

}

package com.demo.announce;

@AnnouceAction
public class TestAction implements IAction{

    @Override
    public boolean doStart() {
        System.out.println("do start....");
        return true;
    }

    @Override
    public boolean doStop() {
        System.out.println("do stop....");
        return true;
    }

}

package edu.detectortroyano.com.adroidchat.lib;

/**
 * Created by detectortroyano on 27/06/2016.
 */
public class GreenRobotEventBus implements EventBus {
    //Refactor gradle y eventbus
    org.greenrobot.eventbus.EventBus eventBus;

    private static class SingletonHolder {
        private static final GreenRobotEventBus INSTANCE = new GreenRobotEventBus();
    }

    public static GreenRobotEventBus getInstance() {
        return SingletonHolder.INSTANCE;
    }
    //Refactor gradle y eventbus
    public GreenRobotEventBus(){
        eventBus = org.greenrobot.eventbus.EventBus.getDefault();
    }

    public void register(Object subscriber){
        eventBus.register(subscriber);
    }

    public void unregister(Object subscriber){
        eventBus.unregister(subscriber);
    }

    public void post(Object event){
        eventBus.post(event);
    }
}
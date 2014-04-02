/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.akka.junit.sample;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class ClassSingleton extends UntypedActor {

LoggingAdapter log = Logging.getLogger(getContext().system(), this);

public ClassSingleton() {
    System.out.println("Constructor is done");
}

public static Props props() {
    return Props.create(ClassSingleton.class);
}

@Override
public void preStart() throws Exception {
    ActorRef selection = getSelf();
    System.out.println("ClassSingleton ActorRef...  " + selection);
}   

@Override
public void onReceive(Object message) {
}

@Override
public void postStop() throws Exception {
    System.out.println("postStop ...  ");
}   


}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.akka.junit.sample;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.actor.Props;
import akka.contrib.pattern.ClusterSingletonManager;
import akka.contrib.pattern.ClusterSingletonProxy;
import akka.testkit.JavaTestKit;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class WorkerTest {

static ActorSystem system;

@BeforeClass
public static void setup() {
    
    system = ActorSystem.create("ClusterSystem");
}

@AfterClass
public static void teardown() {
    JavaTestKit.shutdownActorSystem(system);
    system = null;
}

@Test
public void testWorkers() throws Exception {
    new JavaTestKit(system) {{
        system.actorOf(ClusterSingletonManager.defaultProps(
                Props.create(ClassSingleton.class), "consumer",
                PoisonPill.getInstance(),"backend"), "singleton");
        ActorRef selection = system.actorOf(ClusterSingletonProxy.defaultProps("user/singleton/consumer", "backend"), "consumerProxy");
        System.out.println(selection);
        selection.tell("Some thing",null);
        Thread.sleep(5000);
    }};
}
}

/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstarts.ejb.remote.client.test;

import org.jboss.as.quickstarts.ejb.remote.stateful.RemoteCounter;
import org.jboss.as.quickstarts.ejb.remote.stateless.RemoteCalculator;
import org.junit.Before;
import org.junit.Test;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

/**
 * Unit test based on Sample of Jaikiran Pai.
 *
 * @author Paulo Castro <a href="mailto:paulof@gmail.com"/>.
 */
public class RemoteEJBClientStateFullTest {
    RemoteCounter statefullRemoteCounter = null;

    @Before
    public void setUp() throws NamingException {
        // Obtained a remote stateful counter for invocation
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        final Context context = new InitialContext(jndiProperties);

        statefullRemoteCounter = (RemoteCounter) context.lookup("ejb:/jboss-ejb-remote-server-side/CounterBean!"
                + RemoteCounter.class.getName() + "?stateful");
    }


    /**
     * Looks up a stateless bean and invokes on it
     *
     * @throws javax.naming.NamingException
     */
    @Test
    public void testInvokeStateFullBean() throws NamingException {
        // Let's lookup the remote stateful counter


        // invoke on the remote counter bean
        final int NUM_TIMES = 5;
        System.out.println("Counter will now be incremented " + NUM_TIMES + " times");
        for (int i = 0; i < NUM_TIMES; i++) {
            System.out.println("Incrementing counter");
            statefullRemoteCounter.increment();
            System.out.println("Count after increment is " + statefullRemoteCounter.getCount());
        }
        // now decrementing
        System.out.println("Counter will now be decremented " + NUM_TIMES + " times");
        for (int i = NUM_TIMES; i > 0; i--) {
            System.out.println("Decrementing counter");
            statefullRemoteCounter.decrement();
            System.out.println("Count after decrement is " + statefullRemoteCounter.getCount());
        }
    }
}

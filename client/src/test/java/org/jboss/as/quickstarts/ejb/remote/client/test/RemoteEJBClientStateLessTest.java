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
public class RemoteEJBClientStateLessTest {
    RemoteCalculator statelessRemoteCalculator = null;

    @Before
    public void setUp() throws NamingException {
        // Obtained a remote stateless calculator for invocation

        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        final Context context = new InitialContext(jndiProperties);

        statelessRemoteCalculator = (RemoteCalculator) context.lookup("ejb:/jboss-ejb-remote-server-side/CalculatorBean!"
                + RemoteCalculator.class.getName());
    }


    /**
     * Looks up a stateless bean and invokes on it
     *
     * @throws javax.naming.NamingException
     */
    @Test
    public void testInvokeStatelessBean() throws NamingException {

        // invoke on the remote calculator
        int a = 204;
        int b = 340;
        System.out.println("Adding " + a + " and " + b + " via the remote stateless calculator deployed on the server");

        int sum = statelessRemoteCalculator.add(a, b);
        System.out.println("Remote calculator returned sum = " + sum);

        if (sum != a + b) {
            throw new RuntimeException("Remote stateless calculator returned an incorrect sum " + sum + " ,expected sum was "
                    + (a + b));
        }

        // try one more invocation, this time for subtraction
        int num1 = 3434;
        int num2 = 2332;
        System.out.println("Subtracting " + num2 + " from " + num1
                + " via the remote stateless calculator deployed on the server");

        int difference = statelessRemoteCalculator.subtract(num1, num2);
        System.out.println("Remote calculator returned difference = " + difference);

        if (difference != num1 - num2) {
            throw new RuntimeException("Remote stateless calculator returned an incorrect difference " + difference
                    + " ,expected difference was " + (num1 - num2));
        }
    }
}

Fork for:ejb-remote: Remote EJB Client Example

Add tests.
Check above for more information.
Author: Paulo Castro


=====================================
Author: Jaikiran Pai, Mike Musgrove  
Level: Intermediate  
Technologies: EJB  
Summary: Shows how to access an EJB from a remote Java client program using JNDI  
Target Product: EAP  
Product Versions: EAP 6.1, EAP 6.2  
Source: <https://github.com/jboss-developer/jboss-eap-quickstarts/>  

What is it?
-----------

This example shows how to access an EJB from a remote Java client application. It demonstrates the use of *EJB 3.1* and *JNDI* in Red Hat JBoss Enterprise Application Platform.

There are two components to this example: 

1. A server side component:

    The server component is comprised of a stateful EJB and a stateless EJB. It provides both an EJB JAR that is deployed to the server and a JAR file containing the remote business interfaces required by the remote client application.
2. A remote client application that accesses the server component. 

    The remote client application depends on the remote business interfaces from the server component. This application looks up the stateless and stateful beans via JNDI and invokes a number of methods on them.

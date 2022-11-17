package server;

import corba.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.PortableServer.*;

/**
 *
 * @author alex santacruz & tommy rivera
 */
public class ServerAOM {

    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);
            POA rootpos = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpos.the_POAManager().activate();
            ServerImpl echoImpl = new ServerImpl();
            echoImpl.setORB(orb);
            org.omg.CORBA.Object ref = rootpos.servant_to_reference(echoImpl);
            EchoService href = EchoServiceHelper.narrow(ref);
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            String name = "Echo";
            NameComponent path[] = ncRef.to_name(name);
            ncRef.rebind(path, href);
            System.out.println("Servidor Echo listo y esperando...");
            orb.run();
        } catch (Exception e) {
            System.out.println("Error: " + e);
            e.printStackTrace(System.out);
        }
        System.out.println("Adios, cerrando servidor!!!");
    }
}

package client;

import corba.*;
import java.util.Scanner;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;

/**
 *
 * @author alex santacruz & tommy rivera
 */
public class ClientImpl {

    static EchoService echoImpl;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            ORB orb = ORB.init(args, null);
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            String name = "Echo";
            echoImpl = EchoServiceHelper.narrow(ncRef.resolve_str(name));
            String input = "", fin = "fin";
            while (!input.equals(fin)) {
                System.out.println("Ingrese su mensaje:");
                input = sc.nextLine();
                System.out.println(echoImpl.echo(input));
            }

            echoImpl.shutdown();

        } catch (Exception e) {
            System.out.println("Error: " + e);
            e.printStackTrace(System.out);
        }
    }
}

package server;

import corba.EchoServicePOA;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.omg.CORBA.ORB;

/**
 *
 * @author alex santacruz & tommy rivera
 */
public class ServerImpl extends EchoServicePOA {

    private ORB orb;

    public void setORB(ORB orb_val) {
        orb = orb_val;
    }

    @Override
    public String echo(String x) {
        String respuesta;
        try {
            respuesta = InetAddress.getLocalHost().getHostName() + " ===> " + x;
            return respuesta;
        } catch (UnknownHostException ex) {
            respuesta = "localhost ===>" + x;
            return respuesta;
        }
    }

    @Override
    public void shutdown() {
        orb.shutdown(false);
    }
}

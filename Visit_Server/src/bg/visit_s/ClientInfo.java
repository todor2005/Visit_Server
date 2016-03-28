package bg.visit_s;

/**
 *
 * ClientInfo class contains information about a client, connected to the
 * server.
 */
import bg.visit.VTUser;
import bg.visit.VTUser;
import java.net.Socket;

public class ClientInfo {

    public Socket mSocket = null;
    public ClientListener mClientListener = null;
    public ClientSender mClientSender = null;
    public VTUser user = new VTUser();
}

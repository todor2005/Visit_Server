package bg.visit_s;

/**
 * ClientListener class is purposed to listen for client messages and to forward
 * them to ServerDispatcher.
 */
import java.io.*;
import java.net.*;
import net.sf.json.JSONObject;

public class ClientListener extends Thread {

    private ServerDispatcher mServerDispatcher;
    private ClientInfo mClientInfo;
    private BufferedReader mIn;
    private String message;
    private String decoded = null;
    private Server parent;

    public ClientListener(ClientInfo aClientInfo,
            ServerDispatcher aServerDispatcher, Server parent) throws IOException {
        this.parent = parent;
        mClientInfo = aClientInfo;
        mServerDispatcher = aServerDispatcher;
        Socket socket = aClientInfo.mSocket;
        mIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    /**
     * Until interrupted, reads messages from the client socket, forwards them
     * to the server dispatcher and notifies the server dispatcher.
     */
    @Override
    public void run() {
        message = "";
        while (!isInterrupted()) {
            try {
                message = mIn.readLine();
                if (message == null) {
                    break;
                }
                try {
                    decoded = URLDecoder.decode(message, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                
                parent.receiveMessage(decoded,mClientInfo);
                
        
//                parent.sendMessaga(decoded);
//                mServerDispatcher.sendMessage(decoded);

            } catch (IOException e) {
                break;
            }

        }

        parent.disconnectUser(mClientInfo);
        parent.apendText("User disconnected: " + mClientInfo.mSocket.getInetAddress().getHostAddress());
        // Communication is broken. Interrupt both listener and sender threads
        mClientInfo.mClientSender.interrupt();
        mServerDispatcher.deleteClient(mClientInfo);
    }
}
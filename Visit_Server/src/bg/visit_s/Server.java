package bg.visit_s;

import bg.visit.VTUser;
import bg.visit.VTUser;
import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class Server {

    public static final int LISTENING_PORT = 12345;
    private ServerSocket serverSocket;
    private ClientSender clientSender;
    private ServerWindow parent;
    ServerDispatcher serverDispatcher;

    public void startServer( ServerWindow parent ){
        this.parent = parent;
        try {
            serverSocket = new ServerSocket( LISTENING_PORT );
        } catch( IOException se ) {
            System.err.println( "Can not start listening on port " + LISTENING_PORT );
            se.printStackTrace();
            System.exit( -1 );
        }

        // Start ServerDispatcher thread
        serverDispatcher = new ServerDispatcher();

        // Accept and handle client connections
        while( true ){
            try {
                parent.receiveMessage( "waiting for connection", new ClientInfo() );
                Socket socket = serverSocket.accept();
                ClientInfo clientInfo = new ClientInfo();
                clientInfo.mSocket = socket;
                receiveMessage( socket.getInetAddress().getHostName(), new ClientInfo() );

                ClientListener clientListener
                        = new ClientListener( clientInfo, serverDispatcher, this );
                clientSender
                        = new ClientSender( clientInfo, serverDispatcher, this );
                clientInfo.mClientListener = clientListener;
                clientInfo.mClientSender = clientSender;
                clientListener.start();
                clientSender.start();
                serverDispatcher.addClient( clientInfo );
                
            } catch( IOException ioe ) {
                ioe.printStackTrace();
            }
        }
    }

    public void sendMessageToUser( ArrayList<VTUser> users, String message ){
        serverDispatcher.sendMesageToClients( users, message );
    }

    public void sendMessaga( String message ){
        clientSender.sendMessage( message );
    }

    public void receiveMessage( String message, ClientInfo info ){
        parent.receiveMessage( message, info );
    }

    public void disconnectUser( ClientInfo info ){
        parent.userDisconect( info );
    }

    public void apendText( String text ){
        parent.appendText( text );
    }

//    public String getIP(String userName){
//        return serverDispatcher.getIP(firstName);
//    }
}

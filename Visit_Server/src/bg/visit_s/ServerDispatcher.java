package bg.visit_s;

import bg.visit.VTUser;
import bg.visit.VTUser;
import java.util.*;

public class ServerDispatcher {

//    private Vector mMessageQueue = new Vector();
    public Vector<ClientInfo> mClients = new Vector<>();

    public synchronized void addClient( ClientInfo aClientInfo ){
        mClients.add( aClientInfo );
    }

//    public String getIP(String userName){
//        for (int i = 0; i < mClients.size(); i++) {
//            ClientInfo tmp = mClients.get(i);
//            if(tmp.user.getFirstName().equals(firstName)){
//                return tmp.mSocket.getInetAddress().getHostAddress();
//            }
//        }
//        return "";
//    }
    public synchronized void deleteClient( ClientInfo aClientInfo ){
        int clientIndex = mClients.indexOf( aClientInfo );
        if( clientIndex != -1 ){
            mClients.removeElementAt( clientIndex );
        }
    }

    public synchronized void sendMesageToClients( ArrayList<VTUser> users, String message ){
        for( int k = 0; k < users.size(); k++ ){
            VTUser user = users.get( k );
            for( int i = 0; i < mClients.size(); i++ ){
                ClientInfo info = (ClientInfo) mClients.get( i );
                if( info.user.getFirstName().equals( user.getFirstName() ) ){
                    info.mClientSender.sendMessage( message );
                }
            }
        }
    }

    public synchronized void sendMessageToAllClients( String aMessage ){
        for( int i = 0; i < mClients.size(); i++ ){
            ClientInfo infy = (ClientInfo) mClients.get( i );
            infy.mClientSender.sendMessage( aMessage );
        }
    }

    public void sendMessage( ClientInfo aClientInfo, String aMessage ){
        aClientInfo.mClientSender.sendMessage( aMessage );
    }
}

package bg.visit_s;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ClientSender extends Thread {

    private Vector mMessageQueue = new Vector();
    private ServerDispatcher mServerDispatcher;
    private ClientInfo mClientInfo;
    private PrintWriter mOut;
    private Server parent;
    
    public ClientSender(ClientInfo aClientInfo, ServerDispatcher aServerDispatcher, Server parent)
            throws IOException {
        this.parent = parent;
        mClientInfo = aClientInfo;
        mServerDispatcher = aServerDispatcher;
        Socket socket = aClientInfo.mSocket;
        mOut = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));
    }

    /**
     * Adds given message to the message queue and notifies this thread
     * (actually getNextMessageFromQueue method) that a message is arrived.
     * sendMessage is called by other threads (ServeDispatcher).
     */
    public synchronized void sendMessage(String aMessage) {
        mMessageQueue.add(aMessage);
        notify();
    }

    /**
     * @return and deletes the next message from the message queue. If the queue
     * is empty, falls in sleep until notified for message arrival by
     * sendMessage method.
     */
    private synchronized String getNextMessageFromQueue() throws InterruptedException {
        while (mMessageQueue.size() == 0) {
            wait();
        }
        String message = (String) mMessageQueue.get(0);
        mMessageQueue.removeElementAt(0);
//        parent.receiveMessage("Сървър до "+ mClientInfo.user.getFirstName() +" :" + message,mClientInfo);
        parent.receiveMessage( message,mClientInfo);
        return message;
    }

    /**
     * Sends given message to the client's socket.
     */
    private void sendMessageToClient(String aMessage) {
        
        String encoded;
        try {
            encoded = URLEncoder.encode(aMessage, "UTF-8");
            mOut.println(encoded);
            mOut.flush();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        
    }

    /**
     * Until interrupted, reads messages from the message queue and sends them
     * to the client's socket.
     */
    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                String message = getNextMessageFromQueue();
                
                
                sendMessageToClient(message);
            }
        } catch (Exception e) {
            // Commuication problem
            return;
        }

        // Communication is broken. Interrupt both listener and sender threads
        mClientInfo.mClientListener.interrupt();
        mServerDispatcher.deleteClient(mClientInfo);
    }
}

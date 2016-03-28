/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.visit_s;

/**
 *
 * @author Todor
 */
public class ServerWindow extends javax.swing.JFrame {

    private Server server;

    /**
     * Creates new form ServerWindow
     */
    public ServerWindow(){
        initComponents();

        jButton1ActionPerformed( null );
        jButton3ActionPerformed( null );
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ieText = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Start");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, -1, -1));

        ieText.setEditable(false);
        ieText.setColumns(20);
        ieText.setRows(5);
        jScrollPane1.setViewportView(ieText);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 680, 460));

        jButton3.setText("Connect to DB");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 480, -1, -1));

        jButton2.setText("Clear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 480, -1, -1));

        jButton4.setText("Send all");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 480, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        server = new Server();
        final ServerWindow window = this;
        Thread a;
        a = new Thread( new Runnable() {
            @Override
            public void run(){
                server.startServer( window );
            }
        } );
        a.start();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        final Connection conDB = new Connection();

        Thread a = new Thread( new Runnable() {
            @Override
            public void run(){
                conDB.connect();
            }
        } );

        a.start();


    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        ieText.setText( "" );
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:

        for( ClientInfo tmp : server.serverDispatcher.mClients ){
            tmp.mClientSender.sendMessage( "ppppppppp" );
        }


    }//GEN-LAST:event_jButton4ActionPerformed

    public void receiveMessage( String message, ClientInfo info ){
        ieText.append( "\n" + message );
        readJSON( message, info );
    }

    public void appendText( String text ){
        ieText.append( "\n" + text );
    }

    private void readJSON( String text, ClientInfo info ){
        ServerHelper.readJSON( text, info );
    }

    public void userDisconect( ClientInfo info ){
    }

    /**
     * @param args the command line arguments
     */
    public static void main( String args[] ){
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for( javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels() ){
                if( "Nimbus".equals( info.getName() ) ){
                    javax.swing.UIManager.setLookAndFeel( info.getClassName() );
                    break;
                }
            }
        } catch( ClassNotFoundException ex ) {
            java.util.logging.Logger.getLogger( ServerWindow.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        } catch( InstantiationException ex ) {
            java.util.logging.Logger.getLogger( ServerWindow.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        } catch( IllegalAccessException ex ) {
            java.util.logging.Logger.getLogger( ServerWindow.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        } catch( javax.swing.UnsupportedLookAndFeelException ex ) {
            java.util.logging.Logger.getLogger( ServerWindow.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run(){
                new ServerWindow().setVisible( true );
            }
        } );
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea ieText;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}

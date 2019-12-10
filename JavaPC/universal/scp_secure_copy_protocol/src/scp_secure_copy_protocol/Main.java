package scp_secure_copy_protocol;

import java.io.IOException;
import javax.websocket;

class Main{
	public static void main(String[] arg) throws IOException, JSchException {
        String remoteA = "/tmp/scp/remote-a/";
        String remoteB = "/tmp/scp/remote-b/";
        String local = "/tmp/scp/local/";
        String fileName = "abc.txt";
        
        String user = "chanaka";
        String host = "localhost";
        int port = 22;

        String keyFilePath = "/home/chanaka/.ssh/id_rsa";
        String keyPassword = null;

        Session session = createSession(user, host, port, keyFilePath, keyPassword);

        copyLocalToRemote(session, remoteA, local, fileName);
        copyLocalToRemote(session, local, remoteB, fileName);
    }

    private static Session createSession(String user, String host, int port, String keyFilePath, String keyPassword) {
        try {
            JSch jsch = new JSch();

            if (keyFilePath != null) {
                if (keyPassword != null) {
                    jsch.addIdentity(keyFilePath, keyPassword);
                } else {
                    jsch.addIdentity(keyFilePath);
                }
            }

            Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");

            Session session = jsch.getSession(user, host, port);
            session.setConfig(config);
            session.connect();

            return session;
        } catch (JSchException e) {
            System.out.println(e);
            return null;
        }
    }   
}
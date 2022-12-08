package socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.lang.model.element.Element;

import operation.*;

public class Serveur implements Runnable {

    private static ArrayList<ConnectionHandler> connections;       // lister les connexions des clients
    private static ServerSocket server;
    private static boolean done;
    private static ExecutorService pool;

    public Serveur(){
        done = false;
        connections = new ArrayList<ConnectionHandler>();
    }

    @Override
    public void run(){
        try {
            server = new ServerSocket(9999);
            pool = Executors.newCachedThreadPool();
            while(!done){
                 
                Socket client = server.accept();
                ConnectionHandler handler = new ConnectionHandler(client);
                connections.add(handler);
                pool.execute(handler);
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    
    public void broadcast(String message){
        for (ConnectionHandler ch : connections) {
            if(ch!=null){
                ch.sendMessage(message);
            }
        }
    }
    public static void shutdown(){
        try {
            
            done = true; 
            pool.shutdown();
            if(!server.isClosed()){
                server.close();
            }
            for (ConnectionHandler connectionHandler : connections) {
                connectionHandler.shutdown();
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    class ConnectionHandler implements Runnable{        //ato mi accepte anle connexion
        private Socket client;
        private BufferedReader in;              //texte venant du client
        private PrintWriter out;                //écrire au client
        private String nickname;
        Traitement traitement = new Traitement();
        Selectspecificcolumn selectspecificcolumn = new Selectspecificcolumn();
        
        
        public ConnectionHandler(Socket client){
            this.client = client;
        }
        
        @Override
        public void run(){
            try {
                out = new PrintWriter(client.getOutputStream(),true);
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
               
                out.println("Ecrivez votre requete");
                String message;
                while((message = in.readLine()) !=null){
                    Vector<String> getTraitement = new Vector<String>();
                    getTraitement = traitement.getTraitement(message);
                    out.print("\n");
                    out.print("----------------------------------------------------------");
                    out.print("\n");
                    out.println(traitement.verification(message));
                    for (int i = 0; i < getTraitement.size(); i++) {
                        out.print("|");
                        out.println("  "+getTraitement.get(i)+" ");
                        //out.print("----------------------------------------------------------");
                    }
                    if(message.startsWith("/quit")){
                        out.println(message);
                        //socket.Serveur.shutdown();
                        shutdown();
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                out.println(e);
                //TODO: handle exception
            }
        }
        public void sendMessage(String message){
            out.println(message);
        }

        public void shutdown(){
            try {
                in.close();
                out.close();
                if(!client.isClosed()){
                    client.close();
                }
            } catch (Exception e) {
                //TODO: handle exception
            }
        }
    }
    public static void main(String[] args) {
        Serveur serveur = new Serveur();
        serveur.run();
    }
    
    

}

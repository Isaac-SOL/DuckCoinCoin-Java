package dcc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

public class BCJsonUtils {

    public static Blockchain BCJsonReader(String filename) {

        Gson gson = new Gson();

        try (Reader reader = new FileReader(filename)) {

        	// Convert JSON to Java Object
        	Blockchain bc = gson.fromJson(reader, Blockchain.class);
            //System.out.println(bc);
            return bc;

			// Convert JSON to JsonElement, and later to String
            /*JsonElement json = gson.fromJson(reader, JsonElement.class);
            String jsonInString = gson.toJson(json);
            System.out.println(jsonInString);*/

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static void BCJsonWriter(Blockchain BlockC, String filename){
        // JSON Parser
        //1. Convert object to JSON string
        Gson gson = new Gson();
        String json = gson.toJson(BlockC);
        System.out.println(json);

        //2. Convert object to JSON string and save into a file directly
        try (FileWriter writer = new FileWriter(filename)) {

            gson.toJson(BlockC, writer);

        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * Fonction pour écrire un fichier JSON plus joli
     * Partagé par Calvin Dogus
     * N'affecte pas le projet Blockchain
     * @param BlockC Blockchain à écrire dansun fichier
     * @param filename nom du fichier dans lequel écrire
     */
    public static void BCJsonWriterFormatted(Blockchain BlockC, String filename) {
        // JSON Parser
        //1. Convert object to JSON string
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        String json = gson.toJson(BlockC);
        JsonElement je = jp.parse(json);
        json = gson.toJson(je);
        System.out.println(json);
        
        //2. Convert object to JSON string and save into a file directly
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(BlockC, writer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

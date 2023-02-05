package org.example;


import kong.unirest.GetRequest;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import java.util.Scanner;


/**
 * This is an extremely simple program, so very little documentation is needed.
 */

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("------------------------------------------");
        System.out.println("This program uses the 'Free Dictionary\n" +
                "API' provided by MeetDeveloper to produce\n" +
                "phonetic transcriptions (british pronounciation)\n" +
                "of English words.\n");
        System.out.println("Please enter a word, or 'exit!' to complete.");
        System.out.println("------------------------------------------");
        System.out.print("Input: ");
        String input = scan.nextLine();
        String http = "https://api.dictionaryapi.dev/api/v2/entries/en/";

        while (!input.equals("exit!")) {
            try {
                HttpResponse<JsonNode> response = Unirest.get(http.concat(input)).asJson();
                int stat = response.getStatus();
                if (stat == 200) {
                    JSONObject word;
                    JsonNode node = response.getBody();
                    word = node.getArray().getJSONObject(0);

                    System.out.println(word.get("phonetic"));
                } else if (stat == 404) {
                    System.out.println("The word you entered does exist.");
                } else {
                    System.out.println("A server-side error has occured");
                }

            } catch (Exception ex) {
                System.out.println("An unexpected error has occured.");
                System.out.println(ex.toString());
                break;
            }

            System.out.print("Input: ");
            input = scan.nextLine();
        }
        System.out.println("\nGoodbye!");


    }
}
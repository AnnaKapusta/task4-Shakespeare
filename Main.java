package com.company.task4;

import javax.security.sasl.SaslClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class Main {

    public static String getText(String url) throws Exception {
        URL website = new URL(url);
        URLConnection connection = website.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            response.append(inputLine);

        in.close();

        return response.toString();
    }

    public static void main(String[] args) throws Exception {
        HashMap<String, Integer> map = new HashMap<>();
        String[] shakespeare = {
                "http://shakespeare.mit.edu/Poetry/sonnet.I.html",
                "http://shakespeare.mit.edu/Poetry/sonnet.II.html",
                "http://shakespeare.mit.edu/Poetry/sonnet.III.html",
                "http://shakespeare.mit.edu/Poetry/sonnet.IV.html",
                "http://shakespeare.mit.edu/Poetry/sonnet.V.html",
                "http://shakespeare.mit.edu/Poetry/sonnet.VI.html",
                "http://shakespeare.mit.edu/Poetry/sonnet.VII.html",
                "http://shakespeare.mit.edu/Poetry/sonnet.VIII.html",
                "http://shakespeare.mit.edu/Poetry/sonnet.IX.html",
                "http://shakespeare.mit.edu/Poetry/sonnet.X.html",
                "http://shakespeare.mit.edu/Poetry/sonnet.XI.html",
                "http://shakespeare.mit.edu/Poetry/sonnet.XII.html",
                "http://shakespeare.mit.edu/Poetry/sonnet.XIII.html",
                "http://shakespeare.mit.edu/Poetry/sonnet.XIV.html",
                "http://shakespeare.mit.edu/Poetry/sonnet.XV.html",
                "http://shakespeare.mit.edu/Poetry/sonnet.XVI.html",
                "http://shakespeare.mit.edu/Poetry/sonnet.XVII.html",
                "http://shakespeare.mit.edu/Poetry/sonnet.XVIII.html",
                "http://shakespeare.mit.edu/Poetry/sonnet.XIX.html",
                "http://shakespeare.mit.edu/Poetry/sonnet.XX.html"
        };
        System.out.println("Enter word");
        Scanner scanner = new Scanner(System.in);
        String searchWord = scanner.nextLine().trim();

        for (String a : shakespeare) {
            map.put(a, 0);
            String[] words = getText(a).split(" ");
            for (String w : words) {
                if(w.equalsIgnoreCase(searchWord)) {
                    Integer freq = map.get(a);
                    map.put(a, (freq == 0) ? 1 : freq + 1);
                }
            }
        }

        Map<String, Integer> result = map
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.naturalOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue)-> oldValue, LinkedHashMap::new));

        for (Map.Entry<String, Integer> element : result.entrySet()) {
            System.out.println(element.getKey() + ", "+ element.getValue());
        }

    }
}

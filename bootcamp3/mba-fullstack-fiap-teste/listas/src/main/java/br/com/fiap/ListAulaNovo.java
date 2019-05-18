package br.com.fiap;

import java.util.*;

public class ListAulaNovo {
    public static void main(String[] args) {
        sortedSet();
    }

    static void hashSet(){
        Set<String> nomes = new HashSet<String>();
        nomes.add("danilo");
        nomes.add("diego");
        System.out.println(nomes);
        System.out.println(nomes.contains("diego"));

    }

    static void list(){
        List<String> nomes = new ArrayList<String>();
        nomes.add("Diego");
        nomes.add("Danilo");
        System.out.println(nomes);
        System.out.println(nomes.get(1));
    }

    static void sortedSet(){
        SortedSet<String> sorted = new TreeSet<String>();
        sorted.add("Maria");
        sorted.add("Pedro");
        Iterator<String> iterarNomes = sorted.iterator();
        while (iterarNomes.hasNext()){

            System.out.println(iterarNomes.next());
        }
    }
}

package com.codesse.wordgeek;

import java.util.*;
import java.util.stream.Collectors;

public class LetterFrequencyGraph {

    private final Map<String, Integer> letterFrequencies  = new HashMap<>();

    public LetterFrequencyGraph(String data) {
        if (Objects.isNull(data)){
            return;
        }

        data.chars().mapToObj(c -> (char)c).forEach (character -> {
            String key = String.valueOf(character);
            Integer count = letterFrequencies.get(key);

            if (Objects.isNull(count)) {
                count = 0;
            }

            letterFrequencies.put(key, count+1);
        });
    }

    public int howManyOf(char character){
        Integer count = letterFrequencies.get(String.valueOf(character));
        return Optional.ofNullable(count)
                .orElse(0);
    }

    public Map<String, Integer> letterFrequencies(){
        return Collections.unmodifiableMap(letterFrequencies);
    }


    public boolean containsAll(LetterFrequencyGraph letterFrequencyGraph){
        Map<String, Integer> expectedMap = letterFrequencyGraph.letterFrequencies();
        Map<String, Integer> containedMap = expectedMap.entrySet().stream()
                .filter(entry -> {
                    String key = entry.getKey();
                    Integer actualCount = Objects.nonNull(letterFrequencies.get(key)) ? letterFrequencies.get(key) : 0 ;
                    return entry.getValue() <= actualCount;
                }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return containedMap.size() == expectedMap.size();
    }
}



import java.util.*;

public class TextAnalyzer
{
    private String text;

    public TextAnalyzer(String text)
    {
        this.text = text;
    }

    /**
     * Splits the text into words
     * @return
     */
    public ArrayList<String> getWords()
    {
        String fragments[] = splitTextIntoFragments();
        ArrayList<String> words = new ArrayList<>();
        for(String fragment : fragments)
        {
            if (fragment.matches(".*[a-zA-Z].*")) {
                words.add(fragment);
            }
        }
        return words;
    }

    public String getMostFrequentWord()
    {
        ArrayList<String> words = getWords();
        TreeMap<String, Integer> wordCounts = new TreeMap<>();
        for(String word : words)
        {
            Integer count = wordCounts.get(word);
            wordCounts.put(word, count == null ? 1 : count + 1);
        }
        Collections.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return wordCounts.get(o2) - wordCounts.get(o1);
            }
        });
        return words.get(0);

    }

    //========================================================

    private String[] splitTextIntoFragments()
    {
        return text.toLowerCase().split("[^a-z0-9\\-']");
    }

}
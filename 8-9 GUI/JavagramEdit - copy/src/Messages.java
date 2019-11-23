import java.util.ArrayList;

public class Messages {

    ArrayList<String> messages = new ArrayList<>();
    {
        messages.add("hello");
        messages.add("hi");
        messages.add("abcd efg");
        messages.add("abcde fg");
        messages.add("hello");
        messages.add("hi");
        messages.add("abcd efg");
        messages.add("abcde fg");

    }
    ArrayList getMessgeList () {
        return messages;
    }


    public void addMessage(String message) {
        messages.add(message);
    }




}


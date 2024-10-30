package DataStructures;

import java.util.*;

public class Trie2 {
    private Node root;

    public Trie() {
        root = new Node();
    }

    public void insert(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.containsKey(ch)) {
                node.put(ch, new Node());
            }
            node = node.get(ch);
            node.increasePrefix();
        }
        node.increaseEnd();
    }

    public int countWordsEqualTo(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.containsKey(ch)) {
                return 0;
            }
            node = node.get(ch);
        }
        return node.getEnd();
    }

    public int countWordsStartingWith(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.containsKey(ch)) {
                return 0;
            }
            node = node.get(ch);
        }
        return node.getPrefix();
    }

    public void erase(String word) {
        if (countWordsEqualTo(word) > 0) {
            Node node = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                node = node.get(ch);
                node.reducePrefix();
            }
            node.deleteEnd();
        }
    }
}

class Node {
    Node[] links = new Node[26];
    int cntEndWith = 0;
    int cntPrefix = 0;

    public boolean containsKey(char ch) {
        return links[ch - 'a'] != null;
    }

    public Node get(char ch) {
        return links[ch - 'a'];
    }

    public void put(char ch, Node node) {
        links[ch - 'a'] = node;
    }

    public void increaseEnd() {
        cntEndWith++;
    }

    public void increasePrefix() {
        cntPrefix++;
    }

    public void deleteEnd() {
        cntEndWith--;
    }

    public void reducePrefix() {
        cntPrefix--;
    }

    public int getEnd() {
        return cntEndWith;
    }

    public int getPrefix() {
        return cntPrefix;
    }
}

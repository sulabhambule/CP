
class Trie {

  private Node root;

  public Trie() {
      root = new Node();
  }
  
  // Inserts a word into the Trie
  public void insert(String word) {
      Node node = root;
      for(int i = 0; i < word.length(); i++) {
          char currChar = word.charAt(i);
          if(!node.containsKey(currChar)) {
              node.put(currChar, new Node());
          }
          node = node.get(currChar);
      }
      node.setEnd();
  }
  
  // Searches if the word exists in the Trie
  public boolean search(String word) {
      Node node = root;
      for(int i = 0; i < word.length(); i++) {
          char currChar = word.charAt(i);
          if(node.containsKey(currChar)) {
              node = node.get(currChar);
          } else {
              return false;
          }
      }
      return node.isEnd();
  }
  
  // Checks if there is any word in the Trie that starts with the given prefix
  public boolean startsWith(String prefix) {
      Node node = root;
      for(int i = 0; i < prefix.length(); i++) {
          char currChar = prefix.charAt(i);
          if(node.containsKey(currChar)) {
              node = node.get(currChar);
          } else {
              return false;
          }
      }
      return true;
  }
}

class Node {
  Node[] links = new Node[26];
  private boolean isEnd;

  // if the current node contains the given character..
  public boolean containsKey(char ch) {
      return links[ch - 'a'] != null;
  }

  // Get the node correspoding to given character
  public Node get(char ch) {
      return links[ch - 'a'];
  }

  // put a new node currespond to the character...
  public void put(char ch, Node node) {
      links[ch - 'a'] = node;
  }
  
  // Mark this node as the end of word
  public void setEnd() {
      isEnd = true;
  }

  // Checks if this node is the end of a word
  public boolean isEnd() {
      return isEnd;
  }
}
public class Card {

  // Class variables
  // static means the variable is shared
  // final means the variable is constant
  public static final String[] RANKS = {
    null, "Ace", "2", "3", "4", "5", "6", "7",
    "8", "9", "10", "Jack", "Queen", "King"};

  public static final String[] SUITS = {
    "Clubs", "Diamonds", "Hearts", "Spades"};

  // Instance private variables
  private final int rank;
  private final int suit;

  public Card(int rank, int suit) {
    this.rank = rank;
    this.suit = suit;
  }

  public String toString() {
    return RANKS[this.rank] + " of " + SUITS[this.suit];
  }

  public boolean equals(Card that) {
    return this.rank == that.rank
        && this.suit == that.suit;
    }

  public int compareTo(Card that) {
    // System.out.println("first card suit " + this.suit + " rank " + this.rank);
    // System.out.println("second card suit " + that.suit + " rank " + that.rank);

    if (this.suit < that.suit) {
      return -1;
    }
    if (this.suit > that.suit) {
      return 1;
    }
    // if suits are same, then compare ranks
    if (this.rank < that.rank) {
      return -1;
    }
    if (this.rank > that.rank) {
      return 1;
    }
    // if ranks are also the same, return 0
    return 0;
  }

  public int getRank() {
    return this.rank;
  }

  public int getSuit() {
    return this.suit;
  }

  public static int search(Card[] cards, Card target) {
      for (int i = 0; i < cards.length; i++) {
          if (cards[i].equals(target)) {
              return i;
          }
      }
      return -1;
  }

  public static int binarySearch(Card[] cards, Card target) {
      int low = 0;
      int high = cards.length - 1;
      while (low <= high) {
          System.out.println(low + ", " + high);

          int mid = (low + high) / 2;
          int comp = cards[mid].compareTo(target);

          if (comp == 0) {
              return mid;
          }
          else if (comp < 0) {
              low = mid + 1;
          }
          else {
              high = mid - 1;
          }
      }
      return -1;
  }

  public static int binarySearchRecursive(Card[] cards, Card target, int low, int high) {
      if (high < low) {
          return -1;
      }
      int mid = (low + high) / 2;
      int comp = cards[mid].compareTo(target);

      if (comp == 0) {
          return mid;
      }
      else if (comp < 0) {
          return binarySearchRecursive(cards, target, mid + 1, high);
      }
      else {
          return binarySearchRecursive(cards, target, low, mid - 1);
      }
  }

  public static void printDeck(Card[] cards) {
      for (int i = 0; i < cards.length; i++) {
          System.out.println(cards[i]);
      }
  }

  public static Card[] makeDeck() {
      Card[] deck = new Card[52];

      int index = 0;
      for (int suit = 0; suit <= 3; suit++) {
          for (int rank = 1; rank <= 13; rank++) {
              deck[index] = new Card(rank, suit);
              index++;
          }
      }
      return deck;
  }

  public static void main(String[] args) {

    Card[] deck = Card.makeDeck();
    Card.printDeck(deck);
    // Card card = new Card(15,0);
    // System.out.println(binarySearch(cards,card));
  }
}

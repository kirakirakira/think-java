import java.util.Arrays;
import java.util.Random;

public class Deck {
    private Card[] cards;

    public Deck(int n) {
        this.cards = new Card[n];
    }

    public Deck() {
        this.cards = new Card[52];
        int index = 0;
        for (int suit = 0; suit <= 3; suit++) {
            for (int rank = 1; rank <= 13; rank++) {
                this.cards[index] = new Card(rank, suit);
                index++;
            }
        }
    }

    public Card[] getCards() {
        return this.cards;
    }

    public String toString() {
        return Arrays.toString(this.cards);
    }

    public void print() {
        for (int i = 0; i < this.cards.length; i++) {
            System.out.println(this.cards[i]);
        }
    }

    Random randomno = new Random();

    public int randomInt(int low, int high) {
        // returns a random number between low and high (inclusive)
        // use nextInt, but should not create a Random object
        // each time that randomInt is invoked
        // nextInt returns a pseudorandom, uniformly distributed
        // int value between 0 (inclusive) and n (exclusive)
        return randomno.nextInt((high - low) + 1) + low;
    }

    public void swapCards(int i, int j) {
        // takes two indexes and swaps the cards at the given locations
        Card temp = this.cards[i];
        this.cards[i] = this.cards[j];
        this.cards[j] = temp;
    }

    public void shuffle() {
        //for each index i {
            // choose a random number between i and length - 1
            // swap the ith card and the randomly-chosen card
        //}
        for (int i = 0; i < this.cards.length; i++) {
            int rand = randomInt(i, this.cards.length - 1);
            swapCards(i, rand);
        }
    }

    public int indexLowest(int low, int high) {
        int lowestIndex = low;

        for (int i = low + 1; i < high + 1; i++) {
            //System.out.println("Comparing " + this.cards[lowestIndex] + " and " + this.cards[i]);
            //System.out.println(" and the result is " + this.cards[lowestIndex].compareTo(this.cards[i]));
            if (this.cards[lowestIndex].compareTo(this.cards[i]) > 0) {
                lowestIndex = i;
            }
        }
        return lowestIndex;
    }

    public void selectionSort() {
        //for each index i {
            // find the lowest card at or to the right of i
            // swap the ith card and the lowest card found
        //}
        for (int i = 0; i < this.cards.length; i++) {
            //System.out.println("i = " + i);
            int lowestCard = indexLowest(i, this.cards.length - 1);
            //System.out.println("lowest card " + lowestCard);
            swapCards(i, lowestCard);
        }
    }

    public Deck subdeck(int low, int high) {
        Deck sub = new Deck(high - low + 1);
        for (int i = 0; i < sub.cards.length; i++) {
            sub.cards[i] = this.cards[low+i];
        }
        return sub;
    }

    public static Deck merge(Deck d1, Deck d2) {
        // create a new deck big enough for all the cards
        Deck newDeck = new Deck(d1.cards.length+d2.cards.length);
        // System.out.println(d1.cards.length);
        // System.out.println(d2.cards.length);
        // use the index i to keep track of where we are at in
        // the first deck, and the index j for the second deck
        int i = 0;
        int j = 0;

        // the index k traverses the result deck
        for (int k = 0; k < newDeck.cards.length; k++) {
            // System.out.println("k is " + k);
            // System.out.println("i is " + i);
            // System.out.println("j is " + j);

            // if d1 is empty, d2 wins
            if (i == d1.cards.length) {
                newDeck.cards[k] = d2.cards[j];
                j++;
            }
            // if d2 is empty, d1 wins
            else if (j == d2.cards.length) {
                newDeck.cards[k] = d1.cards[i];
                i++;
            }

            // otherwise, compare the two cards
            // add the winner to the new deck at position k
            // increment either i or j
            else if (d1.cards[i].compareTo(d2.cards[j]) == 1){
                newDeck.cards[k] = d2.cards[j];
                j++;
            }
            else {
                newDeck.cards[k] = d1.cards[i];
                i++;
            }

        }
        // return the  new deck
        return newDeck;
    }

    public static Deck almostMergeSort(Deck deck) {
        // divide the deck into two subdecks
        // sort the subdecks using selectionSort
        // merge the two halves and return the result
        // not recursive

        // use subdeck and form two small subdecks
        //System.out.println(deck.cards.length/2);
        Deck deck_1 = deck.subdeck(0, deck.cards.length/2);
        Deck deck_2 = deck.subdeck(deck.cards.length/2 + 1, deck.cards.length - 1);
        // use selection sort to sort each deck
        deck_1.selectionSort();
        deck_2.selectionSort();

        // merge the two subdecks together
        Deck deckMerged = merge(deck_1, deck_2);

        return deckMerged;
    }

    public static Deck mergeSort(Deck deck) {
        // recursive implementation
        // if the deck is 0 or 1 cards, return it

        if (deck.cards.length < 2) {
            return deck;
        }

        // divide the deck into two subdecks
        int mid = (deck.cards.length - 1)/2;
        int max = deck.cards.length;
        Deck deck_1 = deck.subdeck(0, mid);
        Deck deck_2 = deck.subdeck(mid + 1, max - 1);

        // sort the subdecks using mergeSort
        Deck deck_1_sorted = mergeSort(deck_1);
        Deck deck_2_sorted = mergeSort(deck_2);

        // merge the two halves and return the result
        return merge(deck_1_sorted, deck_2_sorted);

    }

    public void insertionSort() {
        // https://en.wikipedia.org/wiki/Insertion_sort
        // for i = 1 to length(A) - 1
        for (int i = 1; i < this.cards.length; i++) {
            int j = i;
            while ((j > 0) && (this.cards[j-1].compareTo(this.cards[j]) > 0)) {
                swapCards(j, j-1);
                j--;
            }
        }
    }

    public static void main(String[] args) {
        // Deck deck = new Deck();
        // deck.print();
        // //System.out.println(deck.randomInt(0, 5));
        // System.out.println("------------");
        // //deck.swapCards(1, 10);
        // //deck.print();
        // deck.shuffle();
        // //System.out.println(deck.indexLowest(1,5));
        // deck.print();
        // System.out.println("------------");
        // deck.selectionSort();
        // deck.print();

        // // test merge:
        // // build a deck
        // Deck deck = new Deck();
        // // shuffle the deck
        // deck.shuffle();
        // // use subdeck and form two small subdecks
        // Deck deck_1 = deck.subdeck(0,26);
        // Deck deck_2 = deck.subdeck(27,51);
        // // use selection sort to sort each deck
        // deck_1.selectionSort();
        // System.out.println("printing deck 1 after it was sorted --");
        // deck_1.print();
        //
        // deck_2.selectionSort();
        // System.out.println("printing deck 2 after it was sorted --");
        // deck_2.print();
        // // merge the two subdecks together
        //
        // Deck deckMerged = merge(deck_1, deck_2);
        // System.out.println("printing the merged deck --");
        // deckMerged.print();

        // test almostMergeSort:
        // build a deck
        Deck deck = new Deck();
        // shuffle the deck
        deck.shuffle();
        // Deck deckMerged = mergeSort(deck);
        // deckMerged.print();
        deck.insertionSort();
        deck.print();


    }
}

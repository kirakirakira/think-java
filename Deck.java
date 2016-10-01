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

    //     int lowestIndex = -1;
    //
    //     if (low == high) {
    //         lowestIndex = low;
    //     }
    //
    //     for (int i = low; i < high; i++) {
    //         System.out.println("Comparing " + this.cards[low] + " and " + this.cards[i+1]);
    //         System.out.println(" and the result is " + this.cards[low].compareTo(this.cards[i+1]));
    //         if (this.cards[low].compareTo(this.cards[i+1]) > 0) {
    //             lowestIndex = i + 1;
    //         }
    //         else {
    //             lowestIndex = low;
    //         }
    //     }
    //     return lowestIndex;
    // }

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

        // use the index i to keep track of where we are at in
        // the first deck, and the index j for the second deck
        int i = 0;
        int j = 0;

        // the index k traverses the result deck
        //for (int k = 0; k < result.cards.length; k++) {

            // if d1 is empty, d2 wins
            // if d2 is empty, d1 wins
            // otherwise, compare the two cards

            // add the winner to the new deck at position k
            // increment either i or j
        //}
        // return the  new deck
        return null;
    }

    public Deck almostMergeSort() {
        // divide the deck into two subdecks
        // sort the subdecks using selectionSort
        // merge the two halves and return the result
        // not recursive?
        return null;
    }

    public Deck mergeSort() {
        // if the deck is 0 or 1 cards, return it
        // divide the deck into two subdecks
        // sort the subdecks using mergeSort
        // merge the two halves and return the result
        // should be recursive
        return null;
    }

    public void insertionSort() {

    }

    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.print();
        //System.out.println(deck.randomInt(0, 5));
        System.out.println("------------");
        //deck.swapCards(1, 10);
        //deck.print();
        deck.shuffle();
        //System.out.println(deck.indexLowest(1,5));
        deck.print();
        System.out.println("------------");
        deck.selectionSort();
        deck.print();
    }
}

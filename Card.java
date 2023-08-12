public class Card {
    private Suit suit; // масть карты;
    private Rank rank; // достоинство карты;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Rank getRank() {
        return rank;
    }

    public String toString() {
        return suit.toString() + rank.toString();
    }
}

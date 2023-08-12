import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardDesk {
    private List<Card> cardList = new ArrayList<>(Arrays.asList(new Card(Suit.SPADES, Rank.SIX), new Card(Suit.SPADES, Rank.SEVEN),
            new Card(Suit.SPADES, Rank.EIGHT), new Card(Suit.SPADES, Rank.NINE), new Card(Suit.SPADES, Rank.TEN), new Card(Suit.SPADES, Rank.JACK),
            new Card(Suit.SPADES, Rank.QUEEN), new Card(Suit.SPADES, Rank.KING), new Card(Suit.SPADES, Rank.ACE), new Card(Suit.HEARTS, Rank.SIX),
            new Card(Suit.HEARTS, Rank.SEVEN), new Card(Suit.HEARTS, Rank.EIGHT), new Card(Suit.HEARTS, Rank.NINE), new Card(Suit.HEARTS, Rank.TEN),
            new Card(Suit.HEARTS, Rank.JACK), new Card(Suit.HEARTS, Rank.QUEEN), new Card(Suit.HEARTS, Rank.KING), new Card(Suit.HEARTS, Rank.ACE),
            new Card(Suit.CLUBS, Rank.SIX), new Card(Suit.CLUBS, Rank.SEVEN), new Card(Suit.CLUBS, Rank.EIGHT), new Card(Suit.CLUBS, Rank.NINE),
            new Card(Suit.CLUBS, Rank.TEN), new Card(Suit.CLUBS, Rank.JACK), new Card(Suit.CLUBS, Rank.QUEEN), new Card(Suit.CLUBS, Rank.KING),
            new Card(Suit.CLUBS, Rank.ACE), new Card(Suit.DIAMONDS, Rank.SIX), new Card(Suit.DIAMONDS, Rank.SEVEN), new Card(Suit.DIAMONDS, Rank.EIGHT),
            new Card(Suit.DIAMONDS, Rank.NINE), new Card(Suit.DIAMONDS, Rank.TEN), new Card(Suit.DIAMONDS, Rank.JACK), new Card(Suit.DIAMONDS, Rank.QUEEN),
            new Card(Suit.DIAMONDS, Rank.KING), new Card(Suit.DIAMONDS, Rank.ACE)));

    public List<Card> getCardList() {
        return cardList;
    }

    // демонстрация колоды карт;
    public void show() {
        System.out.print("Состав колоды: [");
        for (int i = 0; i < cardList.size(); i++) {
            if (i == cardList.size() - 1) {
                System.out.print(cardList.get(i));
            } else {
                System.out.print(cardList.get(i) + " ");
            }
        }
        System.out.println("]");
    }

    // перемешивание колоды карт;
    public void shuffle() {
        System.out.print("\nКолода перемешивается. ");
        Collections.shuffle(cardList);
        show();
    }

    public boolean checkingForTheEmptiness() {
        if (cardList.isEmpty()) {
            System.out.println("Колода пуста!");
            return true;
        } else return false;
    }
}

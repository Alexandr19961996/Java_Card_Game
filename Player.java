import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class Player {
    private int id; // уникальный порядковый идентификатор пользователя
    private String username; // имя / никнейм пользователя;
    private List<Card> cardArrayList = new ArrayList<>(); // набор карт пользователя;

    public Player(String username) {
        this.username = username;
    }

    public List<Card> getCardArrayList() {
        return cardArrayList;
    }

    // демонстрация карт пользователя;
    public void showCardArrayList() {
        for (int i = 0; i < cardArrayList.size(); i++) {
            if (i == cardArrayList.size() - 1) {
                System.out.print(cardArrayList.get(i));
            } else {
                System.out.print(cardArrayList.get(i) + " ");
            }
        }
    }

    public int makeMove(Deque<Player> players) {
        System.out.print("\nИгрок " + players.element().getId() + ", " + players.element() + ", ваша очередность хода. Ваши карты: [");
        players.element().showCardArrayList();
        System.out.print("Какую карту вы хотите бросить? Введите ее номер: ");
        Scanner line = new Scanner(System.in);
        int numberCard = line.nextInt();
        System.out.println("Игрок " + players.element().getId() + ", " + players.element() + ", бросил карту: [" +
                players.element().getCardArrayList().get(numberCard - 1) + "]");
        return numberCard;





        System.out.print("]. Вы хотите бросить карту? Введите \"1\", если \"Да\", и \"2\", если \"Нет\": ");
        Scanner sc = new Scanner(System.in);
        int answer = sc.nextInt();
        if (answer == 1) {
            System.out.print("Какую карту вы хотите бросить? Введите ее номер от 1 до 6: ");
            Scanner line = new Scanner(System.in);
            int numberCard = line.nextInt();
            System.out.println("Игрок " + players.element().getId() + ", " + players.element() + ", бросил карту: [" +
                    players.element().getCardArrayList().get(numberCard - 1) + "]");
            return numberCard;
        } else {
            System.out.println("\"nИгрок " + players.element().getId() + ", " + players.element() + ", Вы взяли карты.\nВаши карты:");
            players.element().showCardArrayList();
        }
        return 0;
    }

    // взять карту из колоды;
    public void takeCardFromTheDeck(List<Card> list) {
        cardArrayList.add(list.get(0));
    }

    // взять карту со стола;
    public void takeACardFromTheTable(Card card) {
        cardArrayList.add(card);
    }

    // подбросить карту на стол;


    public Card throwACardOnTheTable(int numberCard) {
        return cardArrayList.get(numberCard);
    }

    // перевести ход;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return username;
    }
}

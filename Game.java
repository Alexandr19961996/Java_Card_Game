import java.util.*;

public class Game {
    private Deque<Player> players; // очередь игроков;
    private String gameMode = "1"; // режим игры: однопользовательский - 1 / многопользовательский - 2;
    private String gameOption = "1"; // функциональность игры: стандарт - 1 / подкидной - 2 / переводной - 3;
    private List<Card> listOfCardsFromAttackPlayers = new ArrayList<>(6); // динимический список, в котором будут отображены карты нападающих игроков;
    private List<Card> listOfCardsFromDefensePlayers = new ArrayList<>(6); // динимический список, в котором будут отображены карты защищающегося игрока;

    // общая информация об игре: название, функционал (стандарт / подкидной / переводной) - возможность выбора, кол-во игроков;
    public void info() {
        System.out.println("Добро пожаловать! Вас приветствует карточная игра \"Дурак\" ...");
    }

    // инициализация игровой сессии (информация об игре,
    public void initialization() {
        info();

        do {
            if (!(gameMode.equals("1") || gameMode.equals("2"))) {
                System.out.println("Некорректный ввод!");
            }
            System.out.print("Выберете один из представленных режимов игры:\n     одиночная игра - 1\n     многопользовательская игра - 2.\nВаш выбор: ");
            Scanner sc = new Scanner(System.in);
            gameMode = sc.nextLine();
        }
        while (!(gameMode.equals("1") || gameMode.equals("2")));
        if (gameMode.equals("1")) {
            System.out.println("Вы выбрали режим одиночной игры.");
        } else {
            System.out.println("Вы выбрали режим многопользовательской игры.");
        }

        // получение от пользователей информации о кол-ве игроков
        // (unchecked - проверка на некорректный ввод);
        // (unchecked - корректный вывод слова игроков/игрока/игрок);
        System.out.print("Введите количество игроков: ");
        Scanner sc = new Scanner(System.in);
        int numbersOfPlayers = sc.nextInt();
        System.out.println("Отлично! Участвует " + numbersOfPlayers + " игроков.\n");

        // заполнение массива игроков (unchecked - проверка на некорректный ввод)
        players = new ArrayDeque<>(numbersOfPlayers);

        for (int i = 0; i < numbersOfPlayers; i++) {
            System.out.print("Игрок " + (i + 1) + ", введите Ваше имя: ");
            Scanner sc1 = new Scanner(System.in);
            String name = sc1.next();
            players.add(new Player(name));
        }

        System.out.print("Игра между ");
        Iterator<Player> iterator = players.iterator();
        do {
            System.out.print(iterator.next());
            if (iterator.hasNext()) {
                System.out.print(", ");
            }
        }
        while (iterator.hasNext());
        System.out.println(" началась!");
    }

    public void gameProcess() {
        CardDesk cardDesk = new CardDesk();

        // демонстрация колоды карт;
        System.out.print("\nПеред Вами колода карт. ");
        cardDesk.show();

        // демонстрация перемешанной колоды карт;
        cardDesk.shuffle();

        // раздача карт игрокам;
        for (int i = 0; i < 6; i++) {
            for (Player player : players) {
                player.getCardArrayList().add(cardDesk.getCardList().get(0));
                cardDesk.getCardList().remove(0);
            }
        }

        // демонстрация карт каждого игрока;
        System.out.println("\nПроисходит раздача карт. После раздачи игроки имеют следующие наборы карт: ");
        int identification = 1;
        for (Player player : players) {
            player.setId(identification);
            System.out.print("Игрок " + player.getId() + ", " + player + ": [");
            player.showCardArrayList();
            System.out.println("]");
            identification++;
        }
        cardDesk.show();

        // определение козырной карты;
        cardDesk.identifyTheTrumpCard();

        // определение очередности хода;
        //d
        int playerIndex = 0; // индекс игрока в очереди, отвечающий за очередность хода;
        Card youngestTrumpCard = new Card(trump.getSuit(), Rank.ACE); // по умолчанию вес самой младшей козырной карты - "А" (туз);
        for (Player player : players) {
            for (int j = 0; j < 6; j++) {
                if (player.getCardArrayList().get(j).getSuit().ordinal() == trump.getSuit().ordinal() &&
                        player.getCardArrayList().get(j).getRank().ordinal() >= youngestTrumpCard.getRank().ordinal()) {
                    youngestTrumpCard.setRank(player.getCardArrayList().get(j).getRank());
                    playerIndex = player.getId();
                }
            }
        }
        for (int i = 0; i < playerIndex - 1; i++) {
            players.add(players.removeFirst());
        }
        System.out.println("\nИгрок " + (playerIndex) + ", " + players.element() + ", начинает игру первым. " +
                "Наименьший козырь: " + youngestTrumpCard);

        // игровая сессия;

        // запрос информации, какой картой будет ходить нападающий игрок;
        // обновление игрового поля / вывод информации о картах, находящихся в данный момент на столе;
        do {
            listOfCardsFromAttackPlayers.add(players.element().throwACardOnTheTable(nextPlayersMove(players) - 1));
            showCardsOnTheTable();
        } while (nextPlayersMove(players) != 0);

        listOfCardsFromAttackPlayers.add(players.element().throwACardOnTheTable(nextPlayersMove(players) - 1));
        showCardsOnTheTable();

        // отбивание следующего по ходу игрока;
        players.add(players.element());
        players.remove();

        // запрос информации, какой картой будет ходить защищающийся игрок;
        // обновление игрового поля / вывод информации о картах, находящихся в данный момент на столе;
        listOfCardsFromDefensePlayers.add(players.element().throwACardOnTheTable(nextPlayersMove(players) - 1));
        showCardsOnTheTable();

        players.addFirst(players.pollLast());

        //players.element().nextPlayersMove();
    }


    public int nextPlayersMove(Deque<Player> players) {
        System.out.print("\nИгрок " + players.element().getId() + ", " + players.element() + ", ваша очередность хода. Ваши карты: [");
        players.element().showCardArrayList();
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
            players.element().getCardArrayList().add((Card) listOfCardsFromAttackPlayers);
            players.element().showCardArrayList();
        }
        return 0;
    }

    public boolean CheckingMove() {

        return false;
    }

    public void showCardsOnTheTable() {
        System.out.println("\nНа игровом поле сейчас: карты игрока, осуществляющего ход: " + listOfCardsFromAttackPlayers + ", карты отбивыющегося игрока: " + listOfCardsFromDefensePlayers);
    }
}

public enum Suit {
    SPADES("\u2660"), HEARTS("\u2665"), CLUBS("\u2663"), DIAMONDS("\u2662");

    private final String presentation;

    Suit(String presentation) {
        this.presentation = presentation;
    }

    public String toString() {
        return presentation;
    }
}

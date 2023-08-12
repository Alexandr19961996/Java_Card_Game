public enum Rank {
    ACE("A"), KING("K"), QUEEN("Q"),
    JACK("J"), TEN("10"), NINE("9"),
    EIGHT("8"), SEVEN("7"), SIX("6");

    private final String presentation;

    Rank(String presentation) {
        this.presentation = presentation;
    }

    public String toString() {
        return presentation;
    }
}

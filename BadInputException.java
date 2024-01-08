package burger;

public class BadInputException extends Exception {
    public BadInputException(int limit) {
        super("잘못된 입력입니다! 1에서 "+limit+" 사이의 수를 입력해주세요.");
    }
}

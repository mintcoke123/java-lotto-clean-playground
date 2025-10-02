package domain;

public record ManualCount(int value) {
    public ManualCount {
        if (value < 0) throw new IllegalArgumentException("수동 개수는 음수일 수 없습니다.");
    }
    public void validateAgainstTotal(int total) {
        if (value > total) throw new IllegalArgumentException("수동 개수가 전체를 초과했습니다.");
    }
}

package cleancode.studycafe.tobe.model;

public abstract class StudyCafePass {

    protected final StudyCafePassType passType;
    protected final int duration;
    protected final int price;

    protected StudyCafePass(StudyCafePassType passType, int duration, int price) {
        this.passType = passType;
        this.duration = duration;
        this.price = price;
    }

    public StudyCafePassType getPassType() {
        return passType;
    }

    public int getDuration() {
        return duration;
    }

    public int getPrice() {
        return price;
    }
}

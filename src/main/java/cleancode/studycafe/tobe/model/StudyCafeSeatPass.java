package cleancode.studycafe.tobe.model;

public class StudyCafeSeatPass extends StudyCafePass {
    private final double discountRate;

    private StudyCafeSeatPass(StudyCafePassType passType, int duration, int price, double discountRate) {
        super(passType, duration, price);
        this.discountRate = discountRate;
    }

    public static StudyCafeSeatPass of(StudyCafePassType passType, int duration, int price, double discountRate) {
        return new StudyCafeSeatPass(passType, duration, price, discountRate);
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public boolean canNotUseLocker() {
        return !getPassType().isFixed();
    }
}

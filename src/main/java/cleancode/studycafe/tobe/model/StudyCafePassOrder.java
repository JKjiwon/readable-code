package cleancode.studycafe.tobe.model;

import java.util.Optional;

public class StudyCafePassOrder {

    private final StudyCafeSeatPass studyCafeSeatPass;
    private final StudyCafeLockerPass studyCafeLockerPass;

    private StudyCafePassOrder(StudyCafeSeatPass studyCafeSeatPass, StudyCafeLockerPass studyCafeLockerPass) {
        this.studyCafeSeatPass = studyCafeSeatPass;
        this.studyCafeLockerPass = studyCafeLockerPass;
    }

    public static StudyCafePassOrder of(StudyCafeSeatPass studyCafeSeatPass, StudyCafeLockerPass studyCafeLockerPass) {
        return new StudyCafePassOrder(studyCafeSeatPass, studyCafeLockerPass);
    }

    public StudyCafeSeatPass getStudyCafeSeatPass() {
        return studyCafeSeatPass;
    }

    public Optional<StudyCafeLockerPass> getStudyCafeLockerPass() {
        return Optional.ofNullable(studyCafeLockerPass);
    }

    public int getDiscountPrice() {
        double discountRate = studyCafeSeatPass.getDiscountRate();
        return (int) (studyCafeSeatPass.getPrice() * discountRate);
    }

    public int getTotalPrice() {
        return studyCafeSeatPass.getPrice() - getDiscountPrice() + (studyCafeLockerPass != null ? studyCafeLockerPass.getPrice() : 0);
    }
}

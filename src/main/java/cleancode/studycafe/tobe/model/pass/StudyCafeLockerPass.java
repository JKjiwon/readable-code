package cleancode.studycafe.tobe.model.pass;

public class StudyCafeLockerPass extends StudyCafePass {
    private StudyCafeLockerPass(StudyCafePassType passType, int duration, int price) {
        super(passType, duration, price);
    }

    public static StudyCafeLockerPass of(StudyCafePassType passType, int duration, int price) {
        return new StudyCafeLockerPass(passType, duration, price);
    }
}

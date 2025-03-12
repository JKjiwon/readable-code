package cleancode.studycafe.tobe.repository;

import cleancode.studycafe.tobe.model.pass.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.pass.StudyCafePassType;

import java.util.Optional;

public interface StudyCafeLockerPassRepository {
    Optional<StudyCafeLockerPass> findByStudyCafePassTypeAndDuration(StudyCafePassType studyCafePassType, int duration);
}

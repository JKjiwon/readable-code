package cleancode.studycafe.tobe.repository;

import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.Optional;

public interface StudyCafeLockerPassRepository {
    Optional<StudyCafeLockerPass> findByStudyCafePassTypeAndDuration(StudyCafePassType studyCafePassType, int duration);
}

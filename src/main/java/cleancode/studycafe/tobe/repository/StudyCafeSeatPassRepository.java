package cleancode.studycafe.tobe.repository;

import cleancode.studycafe.tobe.model.StudyCafePassType;
import cleancode.studycafe.tobe.model.StudyCafeSeatPass;

import java.util.List;

public interface StudyCafeSeatPassRepository {
    List<StudyCafeSeatPass> findAllByStudyCafePassType(StudyCafePassType type);
}

package cleancode.studycafe.tobe.repository;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;

import java.util.List;

public interface StudyCafeSeatPassRepository {
    List<StudyCafeSeatPass> findAllByStudyCafePassType(StudyCafePassType type);
}

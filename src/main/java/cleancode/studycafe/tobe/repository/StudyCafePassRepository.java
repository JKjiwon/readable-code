package cleancode.studycafe.tobe.repository;

import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;

public interface StudyCafePassRepository {
    List<StudyCafePass> findAllByStudyCafePasType(StudyCafePassType type);
}

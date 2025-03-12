package cleancode.studycafe.tobe.repository;

import cleancode.studycafe.tobe.io.StudyCafeFileHandler;
import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;

import java.util.List;

public class FileBasedStudyCafeSeatRepository implements StudyCafeSeatPassRepository {

    private final StudyCafeFileHandler fileHandler;

    public FileBasedStudyCafeSeatRepository(StudyCafeFileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    @Override
    public List<StudyCafeSeatPass> findAllByStudyCafePassType(StudyCafePassType type) {
        List<StudyCafeSeatPass> allStudyCafeSeatPasses = findAll();
        return allStudyCafeSeatPasses.stream()
                .filter(studyCafePass -> studyCafePass.getPassType() == type)
                .toList();
    }

    private List<StudyCafeSeatPass> findAll() {
        return fileHandler.readStudyCafePasses();
    }
}

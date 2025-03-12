package cleancode.studycafe.tobe.repository;

import cleancode.studycafe.tobe.io.StudyCafeFileHandler;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;

public class FileBasedStudyCafeRepository implements StudyCafePassRepository {

    private final StudyCafeFileHandler fileHandler;

    public FileBasedStudyCafeRepository(StudyCafeFileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    @Override
    public List<StudyCafePass> findAllByStudyCafePasType(StudyCafePassType type) {
        List<StudyCafePass> allStudyCafePasses = findAll();
        return allStudyCafePasses.stream()
                .filter(studyCafePass -> studyCafePass.getPassType() == type)
                .toList();
    }

    private List<StudyCafePass> findAll() {
        return fileHandler.readStudyCafePasses();
    }
}

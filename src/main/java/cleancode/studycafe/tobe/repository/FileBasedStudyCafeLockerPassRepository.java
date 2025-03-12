package cleancode.studycafe.tobe.repository;

import cleancode.studycafe.tobe.io.StudyCafeFileHandler;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;
import java.util.Optional;

public class FileBasedStudyCafeLockerPassRepository implements StudyCafeLockerPassRepository {

    private final StudyCafeFileHandler fileHandler;

    public FileBasedStudyCafeLockerPassRepository(StudyCafeFileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    @Override
    public Optional<StudyCafeLockerPass> findByStudyCafePassTypeAndDuration(StudyCafePassType studyCafePassType, int duration) {
        List<StudyCafeLockerPass> allStudyCafeLockerPasses = findAll();
        return allStudyCafeLockerPasses.stream()
                .filter(pass -> pass.getPassType() == studyCafePassType)
                .filter(pass -> pass.getDuration() == duration)
                .findFirst();
    }

    private List<StudyCafeLockerPass> findAll() {
        return fileHandler.readLockerPasses();
    }
}

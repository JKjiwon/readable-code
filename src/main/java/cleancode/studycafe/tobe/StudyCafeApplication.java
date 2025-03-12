package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.io.StudyCafeFileHandler;
import cleancode.studycafe.tobe.repository.FileBasedStudyCafeLockerPassRepository;
import cleancode.studycafe.tobe.repository.FileBasedStudyCafeSeatRepository;
import cleancode.studycafe.tobe.repository.StudyCafeLockerPassRepository;
import cleancode.studycafe.tobe.repository.StudyCafeSeatPassRepository;

public class StudyCafeApplication {

    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        OutputHandler outputHandler = new OutputHandler();
        StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();
        StudyCafeSeatPassRepository studyCafeSeatPassRepository = new FileBasedStudyCafeSeatRepository(studyCafeFileHandler);
        StudyCafeLockerPassRepository studyCafeLockerPassRepository = new FileBasedStudyCafeLockerPassRepository(studyCafeFileHandler);

        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(inputHandler, outputHandler, studyCafeSeatPassRepository, studyCafeLockerPassRepository);

        studyCafePassMachine.run();
    }

}

package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.io.StudyCafeFileHandler;
import cleancode.studycafe.tobe.repository.FileBasedStudyCafeLockerPassRepository;
import cleancode.studycafe.tobe.repository.FileBasedStudyCafeRepository;
import cleancode.studycafe.tobe.repository.StudyCafeLockerPassRepository;
import cleancode.studycafe.tobe.repository.StudyCafePassRepository;

public class StudyCafeApplication {

    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        OutputHandler outputHandler = new OutputHandler();
        StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();
        StudyCafePassRepository studyCafePassRepository = new FileBasedStudyCafeRepository(studyCafeFileHandler);
        StudyCafeLockerPassRepository studyCafeLockerPassRepository = new FileBasedStudyCafeLockerPassRepository(studyCafeFileHandler);

        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(inputHandler, outputHandler, studyCafePassRepository, studyCafeLockerPassRepository);


        studyCafePassMachine.run();
    }

}

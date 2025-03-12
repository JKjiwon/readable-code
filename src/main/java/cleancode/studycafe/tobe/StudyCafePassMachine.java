package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.model.pass.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.order.StudyCafePassOrder;
import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.repository.StudyCafeLockerPassRepository;
import cleancode.studycafe.tobe.repository.StudyCafeSeatPassRepository;

import java.util.List;
import java.util.Optional;

public class StudyCafePassMachine {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final StudyCafeSeatPassRepository studyCafeSeatPassRepository;
    private final StudyCafeLockerPassRepository studyCafeLockerPassRepository;

    public StudyCafePassMachine(InputHandler inputHandler, OutputHandler outputHandler,
                                StudyCafeSeatPassRepository studyCafeSeatPassRepository,
                                StudyCafeLockerPassRepository studyCafeLockerPassRepository) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        this.studyCafeSeatPassRepository = studyCafeSeatPassRepository;
        this.studyCafeLockerPassRepository = studyCafeLockerPassRepository;
    }

    public void run() {
        try {
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();

            StudyCafeSeatPass selectedSeatPass = selectSeatPass();
            Optional<StudyCafeLockerPass> selectedLockerPass = selectLockerPass(selectedSeatPass);
            StudyCafePassOrder order = StudyCafePassOrder.of(selectedSeatPass, selectedLockerPass.orElse(null));
            outputHandler.showPassOrderSummary(order);
        } catch (AppException e) {
            outputHandler.showExceptionMessage(e);
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private StudyCafeSeatPass selectSeatPass() {
        outputHandler.askPassTypeSelection();
        StudyCafePassType studyCafePassType = inputHandler.getPassTypeSelectingUserAction();
        List<StudyCafeSeatPass> seatPassCandidate = studyCafeSeatPassRepository.findAllByStudyCafePassType(studyCafePassType);
        outputHandler.showPassListForSelection(seatPassCandidate);
        return inputHandler.getSelectPass(seatPassCandidate);
    }

    private Optional<StudyCafeLockerPass> selectLockerPass(StudyCafeSeatPass selectedSeatPass) {
        if (selectedSeatPass.canNotUseLocker()) {
            return Optional.empty();
        }
        StudyCafeLockerPass lockerPass = getStudyCafeLockerPass(selectedSeatPass.getPassType(), selectedSeatPass.getDuration());
        outputHandler.askLockerPass(lockerPass);
        boolean lockerSelected = inputHandler.getLockerSelection();
        if (lockerSelected) {
            return Optional.of(lockerPass);
        }
        return Optional.empty();
    }

    private StudyCafeLockerPass getStudyCafeLockerPass(StudyCafePassType passType, int duration) {
        return studyCafeLockerPassRepository
                .findByStudyCafePassTypeAndDuration(passType, duration)
                .orElseThrow(() -> new AppException("락커 이용권을 찾을 수 없습니다."));
    }
}

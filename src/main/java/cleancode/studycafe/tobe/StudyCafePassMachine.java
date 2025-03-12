package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;
import cleancode.studycafe.tobe.repository.StudyCafeLockerPassRepository;
import cleancode.studycafe.tobe.repository.StudyCafePassRepository;

import java.util.List;

public class StudyCafePassMachine {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final StudyCafePassRepository studyCafePassRepository;
    private final StudyCafeLockerPassRepository studyCafeLockerPassRepository;

    public StudyCafePassMachine(InputHandler inputHandler, OutputHandler outputHandler,
                                StudyCafePassRepository studyCafePassRepository,
                                StudyCafeLockerPassRepository studyCafeLockerPassRepository) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        this.studyCafePassRepository = studyCafePassRepository;
        this.studyCafeLockerPassRepository = studyCafeLockerPassRepository;

    }

    public void run() {
        try {
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();

            outputHandler.askPassTypeSelection();
            StudyCafePassType studyCafePassType = inputHandler.getPassTypeSelectingUserAction();

            if (studyCafePassType.isHourly()) {
                List<StudyCafePass> hourlyPasses = getHourlyPasses();
                outputHandler.showPassListForSelection(hourlyPasses);
                StudyCafePass selectedPass = inputHandler.getSelectPass(hourlyPasses);
                outputHandler.showPassOrderSummary(selectedPass, null);
            } else if (studyCafePassType.isWeekly()) {
                List<StudyCafePass> weeklyPasses = getWeeklyPasses();
                outputHandler.showPassListForSelection(weeklyPasses);
                StudyCafePass selectedPass = inputHandler.getSelectPass(weeklyPasses);
                outputHandler.showPassOrderSummary(selectedPass, null);
            } else if (studyCafePassType.isFixed()) {
                List<StudyCafePass> fixedPasses = getFixedPasses();
                outputHandler.showPassListForSelection(fixedPasses);
                StudyCafePass selectedPass = inputHandler.getSelectPass(fixedPasses);
                StudyCafeLockerPass lockerPass = getStudyCafeLockerPass(selectedPass);
                boolean lockerSelection = false;
                if (lockerPass != null) {
                    outputHandler.askLockerPass(lockerPass);
                    lockerSelection = inputHandler.getLockerSelection();
                }

                if (lockerSelection) {
                    outputHandler.showPassOrderSummary(selectedPass, lockerPass);
                } else {
                    outputHandler.showPassOrderSummary(selectedPass, null);
                }
            }
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private StudyCafeLockerPass getStudyCafeLockerPass(StudyCafePass selectedPass) {
        return studyCafeLockerPassRepository
                .findByStudyCafePassTypeAndDuration(selectedPass.getPassType(), selectedPass.getDuration())
                .orElseThrow(() -> new AppException("StudyCafeLockerPass(type=" + selectedPass.getPassType()
                        + ", duration=" + selectedPass.getDuration() + ")를 찾을 수 없습니다."));
    }

    private List<StudyCafePass> getFixedPasses() {
        return studyCafePassRepository.findAllByStudyCafePasType(StudyCafePassType.FIXED);
    }

    private List<StudyCafePass> getWeeklyPasses() {
        return studyCafePassRepository.findAllByStudyCafePasType(StudyCafePassType.WEEKLY);
    }

    private List<StudyCafePass> getHourlyPasses() {
        return studyCafePassRepository.findAllByStudyCafePasType(StudyCafePassType.HOURLY);
    }
}

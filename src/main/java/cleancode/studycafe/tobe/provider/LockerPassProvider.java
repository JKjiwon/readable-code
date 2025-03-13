package cleancode.studycafe.tobe.provider;

import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPasses;

// 어떤 데이터를 가져올 것인가에 초점을 맞춰 추상화!!!
public interface LockerPassProvider {
    StudyCafeLockerPasses getLockerPasses();
}

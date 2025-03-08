package cleancode.order;

public class OrderService {

    Log log = new Log();

    public boolean validateOrder(Order order) {
        if (order.getItems().size() == 0) {
            log.info("주문 항목이 없습니다.");
            return false;
        } else {
            if (order.getTotalPrice() > 0) {
                if (!order.hasNotCustomerInfo()) {
                    log.info("사용자 정보가 없습니다.");
                    return false;
                } else {
                    return true;
                }
            } else if (!(order.getTotalPrice() > 0)) {
                log.info("올바르지 않은 총 가격입니다.");
                return false;
            }
        }
        return true;
    }


    // 반환 형태를 바꿔도 되나?...
    public void validateOrderV2(Order order) {
        if (order.hasNoItems()) {
            throw new OrderException("주문 항목이 없습니다.");
        }

        if (order.isInvalidTotalPrice()) {
            throw new OrderException("올바르지 않은 총 가격입니다.");
        }

        if (order.hasNotCustomerInfo()) {
            throw new OrderException("사용자 정보가 없습니다.");
        }
    }
}

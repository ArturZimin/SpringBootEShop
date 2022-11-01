package by.step.zimin.eshop.model;

public enum OrderStatus {
    ADD("The product was added to bucket."),
    PAID("The product was added to bucket and payed."),
    CANCELED("The order was canceled."),
    CLOSED("The order was added to bucket and payed and user got an order.  ");


    private String orderStatus;

    private OrderStatus(String orderStatus){
        this.orderStatus=orderStatus;
    }

    public String getOrderStatus(){
        return orderStatus;
    }
}

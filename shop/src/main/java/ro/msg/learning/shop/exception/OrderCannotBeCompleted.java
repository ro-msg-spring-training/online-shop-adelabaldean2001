package ro.msg.learning.shop.exception;

public class OrderCannotBeCompleted extends RuntimeException{
    public OrderCannotBeCompleted(){
        super("Order cannot be completed. Products are not in stock");
    }
}

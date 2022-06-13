package com.suprun.store.entity;

import java.util.Objects;

/**
 * {@code Order} class represents a order entity.
 *
 * @author Philip Suprun
 * @see AbstractEntity
 */
public class Order extends AbstractEntity {

    // FIXME: 03/06/2022

    /**
     * Enum containing order status of the order.
     */
    public enum OrderStatus {
        ORDERED,
        IN_PROGRESS,
        COMPLETED
    }

    private OrderStatus orderStatus;

    /**
     * Foreign key to {@link User} entity.
     */
    private long userId;


    private Order() {

    }

    public static OrderBuilder builder() {
        return new Order().new OrderBuilder();
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public long getUserId() {
        return userId;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Order order = (Order) object;
        return super.equals(userId == order.userId)
                && Objects.equals(order.orderStatus, orderStatus);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = prime + super.hashCode();
        result = prime * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        result = prime * result + Long.hashCode(userId);

        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Order ");
        builder.append(super.toString()).append(": (");
        builder.append("orderStatus = ").append(orderStatus).append(", ");
        builder.append("userId = ").append(userId).append(", ");

        return builder.toString();
    }

    /**
     * {@code OrderBuilder} is a subclass of {@link AbstractBuilder} class and used for building the order entity.
     *
     * @author Philip Suprun
     */

    public class OrderBuilder extends AbstractBuilder {
        private OrderBuilder() {

        }

        public OrderBuilder setUserId(long userId) {
            Order.this.userId = userId;
            return this;
        }

        public OrderBuilder setOrderStatus(OrderStatus orderStatus) {
            Order.this.orderStatus = orderStatus;
            return this;
        }


        public OrderBuilder of(Order order) {
            super.of(order);
            Order.this.userId = order.userId;
            Order.this.orderStatus = order.orderStatus;

            return this;
        }

        @Override
        public Order build() {
            return Order.this;
        }
    }
}

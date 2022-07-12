package com.suprun.store.entity;

import java.util.List;

/**
 * {@code Card} class represents a Card entity.
 *
 * @author Philip Suprun
 */

public class Card {

    private Order order;

    private List<DeviceHasOrder> deviceHasOrderList;

    private Card() {

    }

    public static CardBuilder builder() {
        return new Card().new CardBuilder();
    }


    public Order getOrder() {
        return order;
    }


    public List<DeviceHasOrder> getDeviceHasOrder() {
        return deviceHasOrderList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (order != null ? !order.equals(card.order) : card.order != null) return false;
        return deviceHasOrderList != null ? deviceHasOrderList.equals(card.deviceHasOrderList) : card.deviceHasOrderList == null;
    }

    @Override
    public int hashCode() {
        int result = order != null ? order.hashCode() : 0;
        result = 31 * result + (deviceHasOrderList != null ? deviceHasOrderList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Card{");
        sb.append("order=").append(order);
        sb.append(", deviceHasOrderList=").append(deviceHasOrderList);
        sb.append('}');
        return sb.toString();
    }

    public class CardBuilder {
        private CardBuilder() {

        }

        public CardBuilder setOrder(Order order) {
            Card.this.order = order;
            return this;
        }

        public CardBuilder setDeviceHasOrderList(List<DeviceHasOrder> deviceHasOrderList) {
            Card.this.deviceHasOrderList = deviceHasOrderList;
            return this;
        }

        public CardBuilder of(Card card) {
            Card.this.order = card.order;
            Card.this.deviceHasOrderList = card.deviceHasOrderList;
            return this;
        }


        public Card build() {
            return Card.this;
        }
    }
}

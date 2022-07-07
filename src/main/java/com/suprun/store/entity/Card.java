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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<DeviceHasOrder> getDeviceHasOrder() {
        return deviceHasOrderList;
    }

    public void setDeviceHasOrder(List<DeviceHasOrder> deviceHasOrder) {
        this.deviceHasOrderList = deviceHasOrder;
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
}

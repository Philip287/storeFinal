package com.suprun.store.entity;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * {@code Guitar} class represents a computer entity.
 *
 * @author Philip Suprun
 * @see AbstractEntity
 */
public class Computer extends AbstractEntity {

    /**
     * Enum containing order status of the computer.
     */
    public enum OrderStatus {
        ORDERED,
        IN_PROGRESS,
        COMPLETED
    }

    private String name;
    private BigDecimal totalCoast;
    private OrderStatus orderStatus;
    /**
     * Foreign key to {@link User} entity.
     */
    private long userId;
    /**
     * Foreign key to {@link Processor} entity.
     */
    private long processorId;
    /**
     * Foreign key to {@link VideoCard} entity.
     */
    private long videoCardId;
    /**
     * Foreign key to {@link MotherBoard} entity.
     */
    private long motherboardId;
    /**
     * Foreign key to {@link Ram} entity.
     */
    private long ramId;
    /**
     * Foreign key to {@link PowerSupply} entity.
     */
    private long powerSupplyId;
    /**
     * Foreign key to {@link Keyboard} entity.
     */
    private long keyboardId;
    /**
     * Foreign key to {@link Monitor} entity.
     */
    private long monitorId;
    /**
     * Foreign key to {@link Mouse} entity.
     */
    private long mouseId;
    /**
     * Foreign key to {@link Hull} entity.
     */
    private long hullId;
    /**
     * Foreign key to {@link Hdd} entity.
     */
    private long hddId;
    /**
     * Foreign key to {@link Speaker} entity.
     */
    private long speakerId;

    private Computer() {

    }

    public static ComputerBuilder builder() {
        return new Computer().new ComputerBuilder();
    }

    public String getName() {
        return name;
    }

    public BigDecimal getTotalCoast() {
        return totalCoast;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public long getUserId() {
        return userId;
    }

    public long getProcessorId() {
        return processorId;
    }

    public long getVideoCardId() {
        return videoCardId;
    }

    public long getMotherboardId() {
        return motherboardId;
    }

    public long getRamId() {
        return ramId;
    }

    public long getPowerSupplyId() {
        return powerSupplyId;
    }

    public long getKeyboardId() {
        return keyboardId;
    }

    public long getMonitorId() {
        return monitorId;
    }

    public long getMouseId() {
        return mouseId;
    }

    public long getHullId() {
        return hullId;
    }

    public long getHddId() {
        return hddId;
    }

    public long getSpeakerId() {
        return speakerId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Computer computer = (Computer) object;

        if (userId != computer.userId) {
            return false;
        }
        if (processorId != computer.processorId) {
            return false;
        }
        if (videoCardId != computer.videoCardId) {
            return false;
        }

        if (motherboardId != computer.motherboardId) {
            return false;
        }
        if (ramId != computer.ramId) {
            return false;
        }
        if (powerSupplyId != computer.powerSupplyId) {
            return false;
        }
        if (keyboardId != computer.keyboardId) {
            return false;
        }
        if (monitorId != computer.monitorId) {
            return false;
        }
        if (mouseId != computer.mouseId) {
            return false;
        }
        if (hullId != computer.hullId) {
            return false;
        }
        if (hddId != computer.hddId) {
            return false;
        }
        if (speakerId != computer.speakerId) {
            return false;
        }
        if (!Objects.equals(name, computer.name)) {
            return false;
        }
        if (!Objects.equals(totalCoast, computer.totalCoast)) {
            return false;
        }
        return orderStatus == computer.orderStatus;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = prime + super.hashCode();
        result = name != null ? name.hashCode() : 0;
        result = prime * result + (totalCoast != null ? totalCoast.hashCode() : 0);
        result = prime * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        result = prime * result + Long.hashCode(userId);
        result = prime * result + Long.hashCode(processorId);
        result = prime * result + Long.hashCode(videoCardId);
        result = prime * result + Long.hashCode(motherboardId);
        result = prime * result + Long.hashCode(ramId);
        result = prime * result + Long.hashCode(powerSupplyId);
        result = prime * result + Long.hashCode(keyboardId);
        result = prime * result + Long.hashCode(monitorId);
        result = prime * result + Long.hashCode(mouseId);
        result = prime * result + Long.hashCode(hullId);
        result = prime * result + Long.hashCode(hddId);
        result = prime * result + Long.hashCode(speakerId);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Computer ");
        builder.append(super.toString()).append(": (");
        builder.append("name = ").append(name).append(", ");
        builder.append("totalCoast = ").append(totalCoast).append(", ");
        builder.append("orderStatus = ").append(orderStatus).append(", ");
        builder.append("userId = ").append(userId).append(", ");
        builder.append("processorId = ").append(processorId).append(", ");
        builder.append("videoCardId = ").append(videoCardId).append(", ");
        builder.append("motherboardId = ").append(motherboardId).append(", ");
        builder.append("ramId = ").append(ramId).append(", ");
        builder.append("powerSupplyId = ").append(powerSupplyId).append(", ");
        builder.append("keyboardId = ").append(keyboardId).append(", ");
        builder.append("monitorId = ").append(monitorId).append(", ");
        builder.append("mouseId = ").append(mouseId).append(", ");
        builder.append("hullId = ").append(hullId).append(", ");
        builder.append("hddId = ").append(hddId).append(", ");
        builder.append("speakerId = ").append(speakerId).append(")");

        return builder.toString();
    }

    /**
     * {@code ComputerBuilder} is a subclass of {@link AbstractBuilder} class and used for building the computer body entity.
     *
     * @author Philip Suprun
     */

    public class ComputerBuilder extends AbstractBuilder {
        private ComputerBuilder() {

        }
        public ComputerBuilder setUserId(long userId) {
            Computer.this.userId = userId;
            return this;
        }

        public ComputerBuilder setName(String name) {
            Computer.this.name = name;
            return this;
        }

        public ComputerBuilder setProcessorId(long processorId) {
            Computer.this.processorId = processorId;
            return this;
        }

        public ComputerBuilder setVideoCardId(long videoCardId) {
            Computer.this.videoCardId = videoCardId;
            return this;
        }

        public ComputerBuilder setMotherboardId(long motherboardId) {
            Computer.this.motherboardId = motherboardId;
            return this;
        }

        public ComputerBuilder setRamId(long ramId) {
            Computer.this.ramId = ramId;
            return this;
        }

        public ComputerBuilder setPowerSupplyId(long powerSupplyId) {
            Computer.this.powerSupplyId = powerSupplyId;
            return this;
        }

        public ComputerBuilder setKeyboardId(long keyboardId) {
            Computer.this.keyboardId = keyboardId;
            return this;
        }

        public ComputerBuilder setMonitorId(long monitorId) {
            Computer.this.monitorId = monitorId;
            return this;
        }

        public ComputerBuilder setMouseId(long mouseId) {
            Computer.this.mouseId = mouseId;
            return this;
        }

        public ComputerBuilder setHullId(long hullId) {
            Computer.this.hullId = hullId;
            return this;
        }

        public ComputerBuilder setHddId(long hddId) {
            Computer.this.hddId = hddId;
            return this;
        }

        public ComputerBuilder setSpeakerId(long speakerId) {
            Computer.this.speakerId = speakerId;
            return this;
        }

        public ComputerBuilder setTotalCoast(BigDecimal totalCoast) {
            Computer.this.totalCoast = totalCoast;
            return this;
        }

        public ComputerBuilder setOrderStatus(OrderStatus orderStatus) {
            Computer.this.orderStatus = orderStatus;
            return this;
        }

        public ComputerBuilder of(Computer computer) {
            super.of(computer);
            Computer.this.name = computer.name;
            Computer.this.userId = computer.userId;
            Computer.this.processorId = computer.processorId;
            Computer.this.videoCardId = computer.videoCardId;
            Computer.this.motherboardId = computer.motherboardId;
            Computer.this.ramId = computer.ramId;
            Computer.this.powerSupplyId = computer.powerSupplyId;
            Computer.this.keyboardId = computer.powerSupplyId;
            Computer.this.monitorId = computer.monitorId;
            Computer.this.mouseId = computer.mouseId;
            Computer.this.hullId = computer.hullId;
            Computer.this.hddId = computer.hddId;
            Computer.this.speakerId = computer.speakerId;
            Computer.this.totalCoast = computer.totalCoast;
            Computer.this.orderStatus = computer.orderStatus;

            return this;
        }

        @Override
        public Computer build() {
            return Computer.this;
        }
    }
}

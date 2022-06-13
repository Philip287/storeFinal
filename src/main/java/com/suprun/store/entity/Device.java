package com.suprun.store.entity;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * {@code Device} class represents a device entity.
 *
 * @author Philip Suprun
 * @see AbstractEntity
 */
public class Device extends AbstractEntity {

    /**
     * Enum containing type of the device.
     */

    public enum Type {
        HARD_DISK,
        HULL,
        KEYBOARD,
        MONITOR,
        MOTHERBOARD,
        MOUSE,
        POWER_SUPPLY,
        PROCESSOR,
        RAM,
        SPEAKER,
        VIDEO_CARD,
        COOLER,
        VENTILATOR
    }

    private Type type;
    private String name;
    private BigDecimal price;
    private String description;
    private String picturePath;

    private Device() {

    }

    public static DeviceBuilder builder() {
        return new Device().new DeviceBuilder();
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getPicturePath() {
        return picturePath;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Device device = (Device) object;

        return super.equals(Objects.equals(name, device.name) && device.price == price
                && Objects.equals(description, device.description) && Objects.equals(picturePath, device.picturePath)
                && Objects.equals(type, device.type));
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = super.hashCode();
        result = prime * result + (name != null ? name.hashCode() : 0);
        result = prime * result + (price != null ? price.hashCode() : 0);
        result = prime * result + (description != null ? description.hashCode() : 0);
        result = prime * result + (picturePath != null ? picturePath.hashCode() : 0);
        result = prime * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Device ");
        builder.append(super.toString()).append("(");
        builder.append("type = ").append(type).append(", ");
        builder.append("name = ").append(name).append(", ");
        builder.append("price = ").append(price).append(", ");
        builder.append("description = ").append(description).append(", ");
        builder.append("picturePath = ").append(picturePath).append(")");

        return builder.toString();
    }

    /**
     * {@code DeviceBuilder} is a subclass of {@link AbstractBuilder} class and used for building the device entity.
     *
     * @author Philip Suprun
     */
    public class DeviceBuilder extends AbstractBuilder {

        private DeviceBuilder() {

        }

        public DeviceBuilder setName(String name) {
            Device.this.name = name;
            return this;
        }

        public DeviceBuilder setPrice(BigDecimal price) {
            Device.this.price = price;
            return this;
        }

        public DeviceBuilder setDescription(String description) {
            Device.this.description = description;
            return this;
        }

        public DeviceBuilder setPicturePath(String picturePath) {
            Device.this.picturePath = picturePath;
            return this;
        }

        public DeviceBuilder setType(Type type) {
            Device.this.type = type;
            return this;
        }

        public DeviceBuilder of(Device device) {
            super.of(device);
            Device.this.name = device.name;
            Device.this.picturePath = device.picturePath;
            Device.this.description = device.description;
            Device.this.price = device.price;
            Device.this.type = device.type;
            return this;
        }

        @Override
        public Device build() {
            return Device.this;
        }
    }
}

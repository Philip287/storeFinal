package com.suprun.store.entity;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * {@code Monitor} class represents a guitar monitor entity.
 *
 * @author Philip Suprun
 * @see AbstractEntity
 */
public class Monitor extends AbstractEntity {

    private String name;
    private BigDecimal price;
    private String description;
    private String picturePath;

    private Monitor() {

    }

    public static MonitorBuilder builder() {
        return new Monitor().new MonitorBuilder();
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
        if (!super.equals(object)) {
            return false;
        }

        Monitor monitor = (Monitor) object;

        if (!Objects.equals(name, monitor.name)) {
            return false;
        }
        if (!Objects.equals(price, monitor.price)) {
            return false;
        }
        if (!Objects.equals(description, monitor.description)) {
            return false;
        }

        return Objects.equals(picturePath, monitor.picturePath);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = super.hashCode();
        result = prime * result + (name != null ? name.hashCode() : 0);
        result = prime * result + (price != null ? price.hashCode() : 0);
        result = prime * result + (description != null ? description.hashCode() : 0);
        result = prime * result + (picturePath != null ? picturePath.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Monitor ");
        builder.append(super.toString()).append(": (");
        builder.append("name = ").append(name).append(", ");
        builder.append("price = ").append(price).append(", ");
        builder.append("description = ").append(description).append(", ");
        builder.append("picturePath = ").append(picturePath).append(")");

        return builder.toString();
    }

    /**
     * {@code MonitorBuilder} is a subclass of {@link AbstractBuilder} class and used for building the computer pickup entity.
     *
     * @author Philip Suprun
     */
    public class MonitorBuilder extends AbstractBuilder {

        private MonitorBuilder() {

        }

        public MonitorBuilder setName(String nme) {
            Monitor.this.name = name;
            return this;
        }

        public MonitorBuilder setPrice(BigDecimal price) {
            Monitor.this.price = price;
            return this;
        }

        public MonitorBuilder setDescription(String description) {
            Monitor.this.description = description;
            return this;
        }

        public MonitorBuilder setPicturePath(String picturePath) {
            Monitor.this.picturePath = picturePath;
            return this;
        }

        public MonitorBuilder of(Monitor monitor) {
            super.of(monitor);
            Monitor.this.name = monitor.name;
            Monitor.this.description = monitor.description;
            Monitor.this.picturePath = monitor.picturePath;
            Monitor.this.price = monitor.price;

            return this;
        }

        @Override
        public AbstractEntity build() {
            return null;
        }
    }
}

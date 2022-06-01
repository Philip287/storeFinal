package com.suprun.store.entity;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * {@code PowerSupply} class represents a computer powerSupply entity.
 *
 * @author Philip Suprun
 * @see AbstractEntity
 */
public class PowerSupply extends AbstractEntity {

    private String name;
    private BigDecimal price;
    private String description;
    private String picturePath;

    private PowerSupply() {

    }

    public static PowerSupplyBuilder builder() {
        return new PowerSupply().new PowerSupplyBuilder();
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

        PowerSupply that = (PowerSupply) object;

        if (!Objects.equals(name, that.name)) {
            return false;
        }
        if (!Objects.equals(price, that.price)) {
            return false;
        }
        if (!Objects.equals(description, that.description)) {
            return false;
        }
        return Objects.equals(picturePath, that.picturePath);
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
        StringBuilder builder = new StringBuilder("PowerSupply");
        builder.append(super.toString()).append(": (");
        builder.append("name = ").append(name).append(", ");
        builder.append("price = ").append(price).append(", ");
        builder.append("description = ").append(description).append(", ");
        builder.append("picturePath = ").append(picturePath).append(", ");
        return builder.toString();
    }

    /**
     * {@code PowerSupplyBuilder} is a subclass of {@link AbstractBuilder} class and used for building the powerSupply entity.
     *
     * @author Philip Suprun
     */
    public class PowerSupplyBuilder extends AbstractBuilder {

        private PowerSupplyBuilder() {

        }

        public PowerSupplyBuilder setName(String name) {
            PowerSupply.this.name = name;
            return this;
        }

        public PowerSupplyBuilder setPrice(BigDecimal price) {
            PowerSupply.this.price = price;
            return this;
        }

        public PowerSupplyBuilder setDescription(String description) {
            PowerSupply.this.description = description;
            return this;
        }

        public PowerSupplyBuilder setPicturePath(String picturePath) {
            PowerSupply.this.picturePath = picturePath;
            return this;
        }

        public PowerSupplyBuilder of(PowerSupply powerSupply) {
            super.of(powerSupply);
            PowerSupply.this.name = powerSupply.name;
            PowerSupply.this.description = powerSupply.description;
            PowerSupply.this.picturePath = powerSupply.picturePath;
            PowerSupply.this.price = powerSupply.price;
            return this;
        }

        @Override
        public PowerSupply build() {
            return PowerSupply.this;
        }
    }
}

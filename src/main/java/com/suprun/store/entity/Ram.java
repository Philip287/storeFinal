package com.suprun.store.entity;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * {@code Ram} class represents a computer ram entity.
 *
 * @author Philip Suprun
 * @see AbstractEntity
 */
public class Ram extends AbstractEntity {

    private String name;
    private BigDecimal price;
    private String description;
    private String picturePath;

    private Ram() {

    }

    public RamBuilder build() {
        return new Ram().new RamBuilder();
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

        Ram ram = (Ram) object;

        if (!Objects.equals(name, ram.name)) {
            return false;
        }
        if (!Objects.equals(price, ram.price)) {
            return false;
        }
        if (!Objects.equals(description, ram.description)) {
            return false;
        }
        return Objects.equals(picturePath, ram.picturePath);
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
        StringBuilder builder = new StringBuilder("Ram ");
        builder.append(super.toString()).append("(");
        builder.append("name = ").append(name).append(", ");
        builder.append("price = ").append(price).append(", ");
        builder.append("description = ").append(description).append(", ");
        builder.append("picturePath = ").append(picturePath).append(")");

        return builder.toString();
    }

    /**
     * {@code RamBuilder} is a subclass of {@link AbstractBuilder} class and used for building the computer ram entity.
     *
     * @author Philip Suprun
     */
    public class RamBuilder extends AbstractBuilder {
        private RamBuilder() {

        }

        public RamBuilder setName(String name) {
            Ram.this.name = name;
            return this;
        }

        public RamBuilder setPrice(BigDecimal price) {
            Ram.this.price = price;
            return this;
        }

        public RamBuilder setDescription(String description) {
            Ram.this.description = description;
            return this;
        }

        public RamBuilder setPicturePath(String picturePath) {
            Ram.this.picturePath = picturePath;
            return this;
        }

        public RamBuilder of(Ram ram) {
            super.of(ram);
            Ram.this.name = ram.name;
            Ram.this.picturePath = ram.picturePath;
            Ram.this.description = ram.description;
            Ram.this.price = ram.price;
            return this;
        }

        @Override
        public AbstractEntity build() {
            return Ram.this;
        }
    }


}

package com.suprun.store.entity;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * {@code Pickup} class represents a computer motherboard entity.
 *
 * @author Philip Suprun
 * @see AbstractEntity
 */
public class MotherBoard extends AbstractEntity {

    private String name;
    private BigDecimal price;
    private String description;
    private String picturePath;

    private MotherBoard() {

    }

    public static MotherBoardBuilder builder() {
        return new MotherBoard().new MotherBoardBuilder();
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

        MotherBoard that = (MotherBoard) object;

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
        final StringBuilder builder = new StringBuilder("MotherBoard ");
        builder.append(super.toString()).append(": (");
        builder.append("name = ").append(name).append(", ");
        builder.append("price = ").append(price).append(", ");
        builder.append("description = ").append(description).append(", ");
        builder.append("picturePath = ").append(picturePath).append(") ");

        return builder.toString();
    }

    /**
     * {@code MotherBoardBuilder} is a subclass of {@link AbstractBuilder} class and used for building the guitar motherboard entity.
     *
     * @author Philip Suprun
     */

    public class MotherBoardBuilder extends AbstractBuilder {
        private MotherBoardBuilder() {

        }

        public MotherBoardBuilder setName(String name) {
            MotherBoard.this.name = name;
            return this;
        }

        public MotherBoardBuilder setPrice(BigDecimal price) {
            MotherBoard.this.price = price;
            return this;
        }

        public MotherBoardBuilder setDescription(String description) {
            MotherBoard.this.description = description;
            return this;
        }

        public MotherBoardBuilder setPicturePath(String picturePath) {
            MotherBoard.this.picturePath = picturePath;
            return this;
        }

        public MotherBoardBuilder of(MotherBoard motherboard) {
            super.of(motherboard);
            MotherBoard.this.name = motherboard.name;
            MotherBoard.this.picturePath = motherboard.picturePath;
            MotherBoard.this.description = motherboard.description;
            MotherBoard.this.price = motherboard.price;
            return this;
        }

        @Override
        public MotherBoard build() {
            return MotherBoard.this;
        }
    }
}

package com.suprun.store.entity;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * {@code Hdd} class represents a computer hdd entity.
 *
 * @author Philip Suprun
 * @see AbstractEntity
 */
public class Hdd extends AbstractEntity {

    private String name;
    private BigDecimal price;
    private String description;
    private String picturePath;

    private Hdd() {

    }

    public static HddBuilder builder() {
        return new Hdd().new HddBuilder();
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

        Hdd hdd = (Hdd) object;

        if (!Objects.equals(name, hdd.name)) {
            return false;
        }
        if (!Objects.equals(price, hdd.price)) {
            return false;
        }
        if (!Objects.equals(description, hdd.description)) {
            return false;
        }
        return Objects.equals(picturePath, hdd.picturePath);
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
        StringBuilder builder = new StringBuilder("Hdd ");
        builder.append(super.toString()).append(", ");
        builder.append("name = ").append(name).append(", ");
        builder.append("price = ").append(price).append(", ");
        builder.append("description = ").append(description).append(", ");
        builder.append("picturePath = ").append(picturePath).append(")");
        return builder.toString();
    }

    /**
     * {@code HddBuilder} is a subclass of {@link AbstractEntity.AbstractBuilder} class and used for building the computer body entity.
     *
     * @author Philip Suprun
     */

    public class HddBuilder extends AbstractBuilder {

        private HddBuilder() {

        }

        public HddBuilder setName(String name) {
            Hdd.this.name = name;
            return this;
        }

        public HddBuilder setPrice(BigDecimal price) {
            Hdd.this.price = price;
            return this;
        }

        public HddBuilder setDescription(String descriptions) {
            Hdd.this.description = descriptions;
            return this;
        }

        public HddBuilder setPicturePath(String picturePath) {
            Hdd.this.picturePath = picturePath;
            return this;
        }

        public HddBuilder of(Hdd hdd) {
            super.of(hdd);
            Hdd.this.name = hdd.name;
            Hdd.this.price = hdd.price;
            Hdd.this.description = hdd.description;
            Hdd.this.picturePath = hdd.picturePath;
            return this;
        }

        @Override
        public AbstractEntity build() {
            return Hdd.this;
        }
    }
}

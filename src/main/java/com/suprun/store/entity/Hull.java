package com.suprun.store.entity;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * {@code Hull} class represents a computer hull entity.
 *
 * @author Philip Suprun
 * @see AbstractEntity
 */
public class Hull extends AbstractEntity {

    private String name;
    private BigDecimal price;
    private String description;
    private String picturePath;

    private Hull() {

    }

    public HullBuilder builder() {
        return new Hull().new HullBuilder();
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

        Hull hull = (Hull) object;

        if (!Objects.equals(name, hull.name)) {
            return false;
        }
        if (!Objects.equals(price, hull.price)) {
            return false;
        }
        if (!Objects.equals(description, hull.description)) {
            return false;
        }

        return Objects.equals(picturePath, hull.picturePath);
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
        StringBuilder builder = new StringBuilder("Hull ");
        builder.append(super.toString()).append(": (");
        builder.append("name = ").append(name).append(", ");
        builder.append("price = ").append(price).append(", ");
        builder.append("description = ").append(description).append(", ");
        builder.append("picturePath = ").append(picturePath).append(")");

        return builder.toString();
    }

    /**
     * {@code HullBuilder} is a subclass of {@link AbstractBuilder} class and used for building the computer body entity.
     *
     * @author Philip Suprun
     */

    public class HullBuilder extends AbstractBuilder {

        private HullBuilder() {

        }

        public HullBuilder setName(String name) {
            Hull.this.name = name;
            return this;
        }

        public HullBuilder setPrice(BigDecimal price) {
            Hull.this.price = price;
            return this;
        }

        public HullBuilder setDescription(String descriptions) {
            Hull.this.description = descriptions;
            return this;
        }

        public HullBuilder setPicturePath(String picturePath) {
            Hull.this.picturePath = picturePath;
            return this;
        }

        public HullBuilder of(Hull hull){
            super.of(hull);
            Hull.this.name = hull.name;
            Hull.this.price = hull.price;
            Hull.this.description = hull.description;
            Hull.this.picturePath = hull.picturePath;
            return this;
        }


        @Override
        public AbstractEntity build() {
            return Hull.this;
        }
    }
}

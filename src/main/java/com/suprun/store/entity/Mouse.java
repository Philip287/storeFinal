package com.suprun.store.entity;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * {@code Mouse} class represents a computer mouse entity.
 *
 * @author Philip Suprun
 * @see AbstractEntity
 */
public class Mouse extends AbstractEntity {

    private String name;
    private BigDecimal price;
    private String description;
    private String picturePath;

    private Mouse() {

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

    public static MouseBuilder builder() {
        return new Mouse().new MouseBuilder();
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

        Mouse mouse = (Mouse) object;

        if (!Objects.equals(name, mouse.name)) {
            return false;
        }
        if (!Objects.equals(price, mouse.price)) {
            return false;
        }
        if (!Objects.equals(description, mouse.description)) {
            return false;
        }
        return Objects.equals(picturePath, mouse.picturePath);
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
        StringBuilder builder = new StringBuilder("Mouse ");
        builder.append(super.toString()).append(": (");
        builder.append("name = ").append(name).append(", ");
        builder.append("price = ").append(price).append(", ");
        builder.append("description = ").append(description).append(", ");
        builder.append("picturePath = ").append(picturePath).append(", ");
        return builder.toString();
    }

    public class MouseBuilder extends AbstractBuilder {
        private MouseBuilder() {

        }

        public MouseBuilder setName(String name) {
            Mouse.this.name = name;
            return this;
        }

        public MouseBuilder setPrice(BigDecimal price) {
            Mouse.this.price = price;
            return this;
        }

        public MouseBuilder setDescription(String description) {
            Mouse.this.description = description;
            return this;
        }

        public MouseBuilder setPicturePath(String picturePath) {
            Mouse.this.picturePath = picturePath;
            return this;
        }

        public MouseBuilder of(Mouse mouse) {
            super.of(mouse);
            Mouse.this.name = mouse.name;
            Mouse.this.description = mouse.description;
            Mouse.this.picturePath = mouse.picturePath;
            Mouse.this.price = mouse.price;
            return this;
        }


        @Override
        public Mouse build() {
            return Mouse.this;
        }
    }
}

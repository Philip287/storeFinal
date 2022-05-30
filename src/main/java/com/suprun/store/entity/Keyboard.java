package com.suprun.store.entity;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * {@code Keyboard} class represents a computer keyboard entity.
 *
 * @author Philip Suprun
 * @see AbstractEntity
 */
public class Keyboard extends AbstractEntity {

    private String name;
    private BigDecimal price;
    private String description;
    private String picturePath;

    private Keyboard() {

    }

    public KeyboardBuilder builder() {
        return new Keyboard().new KeyboardBuilder();
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

        Keyboard keyboard = (Keyboard) object;

        if (!Objects.equals(name, keyboard.name)) {
            return false;
        }
        if (!Objects.equals(price, keyboard.price)) {
            return false;
        }
        if (!Objects.equals(description, keyboard.description)) {
            return false;
        }
        return Objects.equals(picturePath, keyboard.picturePath);
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
     * {@code KeyboardBuilder} is a subclass of {@link AbstractEntity.AbstractBuilder} class and used for building the computer body entity.
     *
     * @author Philip Suprun
     */

    public class KeyboardBuilder extends AbstractEntity.AbstractBuilder {

        private KeyboardBuilder() {

        }

        public KeyboardBuilder setName(String name) {
            Keyboard.this.name = name;
            return this;
        }

        public KeyboardBuilder setPrice(BigDecimal price) {
            Keyboard.this.price = price;
            return this;
        }

        public KeyboardBuilder setDescription(String descriptions) {
            Keyboard.this.description = descriptions;
            return this;
        }

        public KeyboardBuilder setPicturePath(String picturePath) {
            Keyboard.this.picturePath = picturePath;
            return this;
        }

        public KeyboardBuilder of(Keyboard keyboard) {
            super.of(keyboard);
            Keyboard.this.name = keyboard.name;
            Keyboard.this.price = keyboard.price;
            Keyboard.this.description = keyboard.description;
            Keyboard.this.picturePath = keyboard.picturePath;
            return this;
        }

        @Override
        public AbstractEntity build() {
            return Keyboard.this;
        }
    }
}

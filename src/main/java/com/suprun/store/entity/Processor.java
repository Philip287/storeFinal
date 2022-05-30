package com.suprun.store.entity;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * {@code Pickup} class represents a computer processor entity.
 *
 * @author Philip Suprun
 * @see AbstractEntity
 */
public class Processor extends AbstractEntity {

    private String name;
    private BigDecimal price;
    private String description;
    private String picturePath;

    private Processor() {

    }

    public static ProcessorBuilder builder() {
        return new Processor().new ProcessorBuilder();
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

    public String getName() {
        return name;
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

        Processor processor = (Processor) object;

        if (!Objects.equals(name, processor.name)) {
            return false;
        }
        if (!Objects.equals(price, processor.price)) {
            return false;
        }
        if (!Objects.equals(description, processor.description)) {
            return false;
        }
        return Objects.equals(picturePath, processor.picturePath);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (picturePath != null ? picturePath.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Processor");
        builder.append(super.toString()).append(": (");
        builder.append("name = ").append(name).append(", ");
        builder.append("price = ").append(price).append(", ");
        builder.append("description = ").append(description).append(", ");
        builder.append("picturePath = ").append(picturePath).append(")");

        return builder.toString();
    }

    /**
     * {@code ProcessorBuilder} is a subclass of {@link AbstractBuilder} class and used for building the computer processor entity.
     *
     * @author Philip Suprun
     */
    public class ProcessorBuilder extends AbstractBuilder {
        private ProcessorBuilder() {

        }

        public ProcessorBuilder setName(String name) {
            Processor.this.name = name;
            return this;
        }

        public ProcessorBuilder setPrice(BigDecimal price) {
            Processor.this.price = price;
            return this;
        }

        public ProcessorBuilder setDescription(String description) {
            Processor.this.description = description;
            return this;
        }

        public ProcessorBuilder setPicturePath(String picturePath) {
            Processor.this.picturePath = picturePath;
            return this;
        }

        public ProcessorBuilder of(Processor processor) {
            super.of(processor);
            Processor.this.name = processor.name;
            Processor.this.picturePath = processor.picturePath;
            Processor.this.description = processor.description;
            Processor.this.price = processor.price;
            return this;
        }

        @Override
        public Processor build() {
            return Processor.this;
        }
    }
}

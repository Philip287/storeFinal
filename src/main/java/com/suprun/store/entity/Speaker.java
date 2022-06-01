package com.suprun.store.entity;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * {@code Speaker} class represents a computer speaker entity.
 *
 * @author Philip Suprun
 * @see AbstractEntity
 */
public class Speaker extends AbstractEntity {

    private String name;
    private BigDecimal price;
    private String description;
    private String picturePath;

    private Speaker() {

    }

    public static SpeakerBuilder builder() {
        return new Speaker().new SpeakerBuilder();
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

        Speaker speaker = (Speaker) object;

        if (!Objects.equals(name, speaker.name)) {
            return false;
        }
        if (!Objects.equals(price, speaker.price)) {
            return false;
        }
        if (!Objects.equals(description, speaker.description)) {
            return false;
        }
        return Objects.equals(picturePath, speaker.picturePath);
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
        StringBuilder builder = new StringBuilder("Speaker :");
        builder.append(super.toString()).append("(");
        builder.append("name = ").append(name).append(", ");
        builder.append("price = ").append(price).append(", ");
        builder.append("description = ").append(description).append(", ");
        builder.append("picturePath = ").append(picturePath).append(")");

        return builder.toString();
    }

    /**
     * {@code SpeakerBuilder} is a subclass of {@link AbstractBuilder} class and used for building the computer speakerBuilder entity.
     *
     * @author Philip Suprun
     */
    public class SpeakerBuilder extends AbstractBuilder {

        private SpeakerBuilder() {

        }

        public SpeakerBuilder setName(String name) {
            Speaker.this.name = name;
            return this;
        }

        public SpeakerBuilder setPrice(BigDecimal price) {
            Speaker.this.price = price;
            return this;
        }

        public SpeakerBuilder setDescription(String description) {
            Speaker.this.description = description;
            return this;
        }

        public SpeakerBuilder setPicturePath(String picturePath) {
            Speaker.this.picturePath = picturePath;
            return this;
        }

        public SpeakerBuilder of(Speaker speaker) {
            super.of(speaker);
            Speaker.this.name = speaker.name;
            Speaker.this.picturePath = speaker.picturePath;
            Speaker.this.description = description;
            Speaker.this.price = price;
            return this;
        }

        @Override
        public Speaker build() {
            return Speaker.this;
        }
    }

}

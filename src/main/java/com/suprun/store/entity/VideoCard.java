package com.suprun.store.entity;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * {@code VideoCard} class represents a computer videoCard entity.
 *
 * @author Philip Suprun
 * @see AbstractEntity
 */
public class VideoCard extends AbstractEntity {

    private String name;
    private BigDecimal price;
    private String description;
    private String picturePath;

    private VideoCard() {

    }

    public VideoCardBuilder build() {
        return new VideoCard().new VideoCardBuilder();
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

        VideoCard videoCard = (VideoCard) object;

        if (!Objects.equals(name, videoCard.name)) {
            return false;
        }
        if (!Objects.equals(price, videoCard.price)) {
            return false;
        }
        if (!Objects.equals(description, videoCard.description)) {
            return false;
        }

        return Objects.equals(picturePath, videoCard.picturePath);
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
        StringBuilder builder = new StringBuilder("VideoCard ");
        builder.append(super.toString()).append("(");
        builder.append("name = ").append(name).append(", ");
        builder.append("price = ").append(price).append(", ");
        builder.append("description = ").append(description).append(", ");
        builder.append("picturePath = ").append(picturePath).append(")");

        return builder.toString();
    }

    /**
     * {@code VideoCardBuilder} is a subclass of {@link AbstractBuilder} class and used for building the computer videoCard entity.
     *
     * @author Philip Suprun
     */
    public class VideoCardBuilder extends AbstractBuilder {

        private VideoCardBuilder() {

        }

        public VideoCardBuilder setName(String name) {
            VideoCard.this.name = name;
            return this;
        }

        public VideoCardBuilder setPrice(BigDecimal price) {
            VideoCard.this.price = price;
            return this;
        }

        public VideoCardBuilder setDescription(String description) {
            VideoCard.this.description = description;
            return this;
        }

        public VideoCardBuilder setPicturePath(String picturePath) {
            VideoCard.this.picturePath = picturePath;
            return this;
        }

        public VideoCardBuilder of(VideoCard videoCard) {
            super.of(videoCard);
            VideoCard.this.name = videoCard.name;
            VideoCard.this.picturePath = picturePath;
            VideoCard.this.description = description;
            VideoCard.this.price = price;
            return this;
        }

        @Override
        public VideoCard build() {
            return VideoCard.this;
        }
    }
}

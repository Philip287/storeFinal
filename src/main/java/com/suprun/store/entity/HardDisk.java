package com.suprun.store.entity;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * {@code HardDisk} class represents a computer hardDisk entity.
 *
 * @author Philip Suprun
 * @see AbstractEntity
 */
public class HardDisk extends AbstractEntity {

    private String name;
    private BigDecimal price;
    private String description;
    private String picturePath;

    private HardDisk() {

    }

    public static HardDiskBuilder builder() {
        return new HardDisk().new HardDiskBuilder();
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

        HardDisk hdd = (HardDisk) object;

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
        StringBuilder builder = new StringBuilder("Hard disk ");
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

    public class HardDiskBuilder extends AbstractBuilder {

        private HardDiskBuilder() {

        }

        public HardDiskBuilder setName(String name) {
            HardDisk.this.name = name;
            return this;
        }

        public HardDiskBuilder setPrice(BigDecimal price) {
            HardDisk.this.price = price;
            return this;
        }

        public HardDiskBuilder setDescription(String descriptions) {
            HardDisk.this.description = descriptions;
            return this;
        }

        public HardDiskBuilder setPicturePath(String picturePath) {
            HardDisk.this.picturePath = picturePath;
            return this;
        }

        public HardDiskBuilder of(HardDisk hdd) {
            super.of(hdd);
            HardDisk.this.name = hdd.name;
            HardDisk.this.price = hdd.price;
            HardDisk.this.description = hdd.description;
            HardDisk.this.picturePath = hdd.picturePath;
            return this;
        }

        @Override
        public AbstractEntity build() {
            return HardDisk.this;
        }
    }
}

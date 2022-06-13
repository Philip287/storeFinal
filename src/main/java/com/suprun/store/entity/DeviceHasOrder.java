package com.suprun.store.entity;


/**
 * {@code DeviceHasOrder} class represents a DeviceHaOrder entity.
 *
 * @author Philip Suprun
 * @see AbstractEntity
 */
public class DeviceHasOrder extends AbstractEntity {

    /**
     * Foreign key to {@link Device} entity.
     */

    private long deviceId;

    /**
     * Foreign key to {@link Order} entity.
     */

    private long orderId;
    private long number;

    public long getDeviceId() {
        return deviceId;
    }

    public long getOrderId() {
        return orderId;
    }

    public long getNumber() {
        return number;
    }

    private DeviceHasOrder() {

    }

    public static DeviceHasOrderBuilder builder() {
        return new DeviceHasOrder().new DeviceHasOrderBuilder();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        DeviceHasOrder deviceHasOrder = (DeviceHasOrder) object;
        return super.equals(deviceHasOrder.deviceId == deviceId && deviceHasOrder.orderId == orderId
                && deviceHasOrder.number == number);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = super.hashCode();
        result = prime * result + Long.hashCode(deviceId);
        result = prime * result + Long.hashCode(orderId);
        result = prime * result + Long.hashCode(number);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("DeviceHasOrder ");
        builder.append(super.toString()).append("(");
        builder.append("deviceId = ").append(deviceId).append(", ");
        builder.append("orderId = ").append(orderId).append(", ");
        builder.append("number = ").append(number).append(")");

        return builder.toString();
    }

    /**
     * {@code DeviceHasOrder} is a subclass of {@link AbstractBuilder} class and used for building the deviceHasOrder entity.
     *
     * @author Philip Suprun
     */

    public class DeviceHasOrderBuilder extends AbstractBuilder {
        private DeviceHasOrderBuilder() {

        }

        public DeviceHasOrderBuilder setDeviceId(long deviceId) {
            DeviceHasOrder.this.deviceId = deviceId;
            return this;
        }

        public DeviceHasOrderBuilder setOrderId(long orderId) {
            DeviceHasOrder.this.orderId = orderId;

            return this;
        }

        public DeviceHasOrderBuilder setNumber(long number) {
            DeviceHasOrder.this.number = number;

            return this;
        }

        public DeviceHasOrderBuilder of(DeviceHasOrder deviceHasOrder) {
            DeviceHasOrder.this.orderId = deviceHasOrder.orderId;
            DeviceHasOrder.this.deviceId = deviceHasOrder.deviceId;
            DeviceHasOrder.this.number = deviceHasOrder.number;
            return this;
        }

        @Override
        public DeviceHasOrder build() {
            return DeviceHasOrder.this;
        }
    }

}

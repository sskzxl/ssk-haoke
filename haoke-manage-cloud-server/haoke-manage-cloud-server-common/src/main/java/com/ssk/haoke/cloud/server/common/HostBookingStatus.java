package com.ssk.haoke.cloud.server.common;

public enum HostBookingStatus {
    UNCONFIRMED(0,"待确认","待房主确认"),
    TOINSPECTION(1,"已通过","已通过，请按时前往"),//已通过 -> 看房 ->已看完
    NOTALLOWED(2,"已拒绝","未通过，请再次联系房东进行确认"),
    CANCEL(3,"已取消","已取消，如需要请再次预约"),
    CONFIRMED(4,"已看完","已完成看房");

    private Integer status;
    private String  msg;
    private String button;

    public static HostBookingStatus getNoticeByStatus(Integer status){
        for (HostBookingStatus bookingStatus : HostBookingStatus.values()) {
            if (bookingStatus.getStatus() == status){
                return bookingStatus;
            }
        }
        return null;
    }

    public String getNotice() {
        return button;
    }

    HostBookingStatus(Integer status, String msg,String button) {
        this.status = status;
        this.msg = msg;
        this.button = button;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}

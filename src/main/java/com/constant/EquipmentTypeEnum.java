package com.constant;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * 设备枚举类
 */
public enum EquipmentTypeEnum {


    /**
     * 粮仓设备类型
     */
    LC ( 1 ),

    /**
     * 天牛设备类型
     */
    TN ( 2 );

    private int code;

    EquipmentTypeEnum ( Integer code ) {
        this.code = code;
    }

    public Integer getCode () {
        return code;
    }

    public void setCode ( Integer code ) {
        this.code = code;
    }

    public static EquipmentTypeEnum fromNumber ( Integer integer ) {
        switch ( integer ) {
            case 1:
                return LC;
            case 2:
                return TN;
            default:
                return null;
        }
    }

    public static boolean isLC(Integer integer) {
        if (null == integer) {
            return false;
        }
        return LC.getCode () == integer;
    }

    public static boolean isTN(Integer integer) {
        if (null == integer) {
            return false;
        }
        return TN.getCode () == integer;
    }

    /**
     * 该数字是否是此枚举类里的值
     *
     * @param integer
     * @return
     */
    public static boolean isInEnum ( Integer integer ) {
        if ( integer == null || integer == 0 ) {
            return false;
        }

        for ( EquipmentTypeEnum equipmentTypeEnum : values () ) {
            if ( equipmentTypeEnum.getCode () == integer ) {
                return true;
            }
        }
        return false;
    }

    /**
     * 该数字不是此枚举类里的值
     *
     * @param integer
     * @return
     */
    public static boolean isNotInEnum ( Integer integer ) {
        return ! isInEnum ( integer );
    }


}

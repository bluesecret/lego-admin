package com.lego.crm.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.lego.core.vo.VO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrmLeadModifyVO extends VO {

    private static final long serialVersionUID = 1L;

    private String code;
    private String name;
    private String mobile;
    private BigDecimal amount;
    private Date brithday;
    private String address;
    private int age;
    private BigDecimal size;
    private boolean status;
    private String email;
    private String employee;
    private String dept;
    private String source;
    private String customer;
}

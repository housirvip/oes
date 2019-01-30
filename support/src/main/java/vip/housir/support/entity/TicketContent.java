package vip.housir.support.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author housirvip
 */
@Data
public class TicketContent implements Serializable {
    private Integer id;

    private Integer tid;

    private Boolean idAdmin;

    private List<String> pics;

    private String content;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}
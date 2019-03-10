package vip.housir.support.entity;

import lombok.Data;
import vip.housir.base.constant.TicketModule;
import vip.housir.base.constant.TicketStatus;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author housirvip
 */
@Data
public class Ticket implements Serializable {
    private Integer id;

    private Integer uid;

    private Integer rate;

    private TicketStatus status;

    private TicketModule module;

    private String title;

    private Date createTime;

    private Date updateTime;

    private List<TicketContent> ticketContents;

    private static final long serialVersionUID = 1L;
}
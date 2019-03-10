package vip.housir.base.dto;

import lombok.Data;
import vip.housir.base.constant.TicketModule;
import vip.housir.base.constant.TicketStatus;

import java.io.Serializable;
import java.util.List;

/**
 * @author housirvip
 */
@Data
public class TicketDto implements Serializable {
    private Integer id;

    private Integer uid;

    private Integer rate;

    private TicketStatus status;

    private TicketModule module;

    private String title;

    private TicketContentDto ticketContent;

    private static final long serialVersionUID = 1L;

    @Data
    private class TicketContentDto implements Serializable {
        private List<String> pics;

        private String content;

        private static final long serialVersionUID = 1L;
    }
}
package vip.housir.base.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author housirvip
 */
@Data
public class TicketDto implements Serializable {
    private Integer id;

    private Integer uid;

    private String status;

    private Integer rate;

    private String module;

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
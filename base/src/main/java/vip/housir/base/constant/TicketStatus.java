package vip.housir.base.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author housirvip
 */
@Getter
@AllArgsConstructor
public enum TicketStatus {

    /**
     * 用户回复
     */
    UserReply("user_reply"),
    /**
     * 管理员回复
     */
    AdminReply("admin_reply"),
    /**
     * 管理员回复
     */
    Finished("finished");

    private String value;
}

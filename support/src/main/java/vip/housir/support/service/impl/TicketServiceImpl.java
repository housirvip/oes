package vip.housir.support.service.impl;

import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.BooleanUtils;
import org.springframework.stereotype.Service;
import vip.housir.base.constant.ErrorMessage;
import vip.housir.support.entity.Ticket;
import vip.housir.support.mapper.TicketContentMapper;
import vip.housir.support.mapper.TicketMapper;
import vip.housir.support.service.TicketService;

import java.util.List;
import java.util.Optional;

/**
 * @author housirvip
 */
@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketMapper ticketMapper;
    private final TicketContentMapper ticketContentMapper;

    @Override
    public Ticket oneById(Integer id, Integer uid) {

        Ticket ticket = ticketMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(ticket, ErrorMessage.TICKET_NOT_FOUND);
        Preconditions.checkArgument(uid == null || uid.equals(ticket.getUid()), ErrorMessage.TICKET_PERMISSION_DENY);

        ticket.setTicketContents(ticketContentMapper.selectByTid(ticket.getId()));

        return ticket;
    }
}

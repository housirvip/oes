package vip.housir.support.service.impl;

import com.github.pagehelper.Page;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vip.housir.base.constant.Constant;
import vip.housir.base.constant.ErrorMessage;
import vip.housir.base.dto.PageDto;
import vip.housir.base.dto.TicketDto;
import vip.housir.support.entity.Ticket;
import vip.housir.support.entity.TicketContent;
import vip.housir.support.mapper.TicketContentMapper;
import vip.housir.support.mapper.TicketMapper;
import vip.housir.support.service.TicketService;

import java.util.Date;

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

    @Override
    public Page<Ticket> pageByParam(PageDto pageDto) {

        return ticketMapper.listByParam(pageDto.putParam().getParamAsMap());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer create(TicketDto ticketDto) {

        Ticket ticket = new Ticket();
        ticket.setUid(ticketDto.getUid());
        ticket.setStatus(Constant.TICKET_USER);
        ticket.setModule(ticketDto.getModule());
        ticket.setTitle(ticketDto.getTitle());
        ticket.setCreateTime(new Date());

        ticketMapper.insertSelective(ticket);

        TicketContent ticketContent = new TicketContent();
        BeanUtils.copyProperties(ticketDto.getTicketContent(), ticketContent);
        ticketContent.setCreateTime(new Date());
        ticketContent.setTid(ticket.getId());

        ticketContentMapper.insertSelective(ticketContent);

        return ticket.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer update(TicketDto ticketDto) {

        Ticket ticket = ticketMapper.selectByPrimaryKey(ticketDto.getId());
        Preconditions.checkNotNull(ticket, ErrorMessage.TICKET_NOT_FOUND);
        Preconditions.checkArgument(ticketDto.getUid() == null || ticketDto.getUid().equals(ticket.getUid()), ErrorMessage.TICKET_PERMISSION_DENY);

        ticket.setStatus(ticketDto.getStatus());
        ticketMapper.updateByPrimaryKeySelective(ticket);

        if (ticketDto.getTicketContent() == null) {
            return ticket.getId();
        }

        TicketContent ticketContent = new TicketContent();
        BeanUtils.copyProperties(ticketDto.getTicketContent(), ticketContent);
        ticketContent.setCreateTime(new Date());
        ticketContent.setTid(ticket.getId());
        ticketContent.setIdAdmin(false);
        ticketContentMapper.insertSelective(ticketContent);

        return ticket.getId();
    }
}

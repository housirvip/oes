package vip.housir.support.controller;

import com.github.pagehelper.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vip.housir.base.constant.Constant;
import vip.housir.base.dto.PageDto;
import vip.housir.base.dto.TicketDto;
import vip.housir.base.response.BaseResponse;
import vip.housir.base.response.PageResponse;
import vip.housir.base.response.ResultResponse;
import vip.housir.support.entity.Ticket;
import vip.housir.support.service.TicketService;

/**
 * @author housirvip
 */
@RestController
@RequestMapping(value = "/ticket")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping(value = "/{id}")
    public BaseResponse<Ticket> ticket(@PathVariable Integer id, Authentication auth) {

        return new ResultResponse<>(ticketService.oneById(id, (Integer) auth.getPrincipal()));
    }

    @GetMapping(value = "/list")
    public BaseResponse<Page> tickets(@Validated PageDto pageDto, Authentication auth) {

        Page<Ticket> tickets = ticketService.pageByParam(pageDto.putUid((Integer) auth.getPrincipal()));

        return new PageResponse<>(tickets, tickets.getTotal());
    }

    @PostMapping(value = "")
    public BaseResponse<Integer> create(@RequestBody TicketDto ticketDto, Authentication auth) {

        ticketDto.setUid((Integer) auth.getPrincipal());

        return new ResultResponse<>(ticketService.create(ticketDto));
    }

    @PutMapping(value = "")
    public BaseResponse<Integer> reply(@RequestBody TicketDto ticketDto, Authentication auth) {

        ticketDto.setUid((Integer) auth.getPrincipal());
        ticketDto.setStatus(Constant.TICKET_USER);

        return new ResultResponse<>(ticketService.update(ticketDto));
    }

    @PutMapping(value = "/close")
    public BaseResponse<Integer> close(@RequestBody TicketDto ticketDto, Authentication auth) {

        ticketDto.setUid((Integer) auth.getPrincipal());
        ticketDto.setStatus(Constant.TICKET_FINISH);

        return new ResultResponse<>(ticketService.update(ticketDto));
    }
}

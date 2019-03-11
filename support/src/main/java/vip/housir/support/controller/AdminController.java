package vip.housir.support.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vip.housir.base.constant.TicketStatus;
import vip.housir.base.dto.TicketDto;
import vip.housir.base.response.BaseResponse;
import vip.housir.base.response.ResultResponse;
import vip.housir.support.entity.Notice;
import vip.housir.support.service.NoticeService;
import vip.housir.support.service.TicketService;

/**
 * @author housirvip
 */
@RestController
@RequestMapping(value = "/support-admin")
@RequiredArgsConstructor
public class AdminController {

    private final NoticeService noticeService;
    private final TicketService ticketService;

    @PostMapping(value = "/notice")
    public BaseResponse<Integer> notice(@RequestBody Notice notice) {

        return new ResultResponse<>(noticeService.createOrUpdate(notice));
    }

    @PutMapping(value = "/ticket")
    public BaseResponse<Integer> reply(@RequestBody TicketDto ticketDto) {

        ticketDto.setStatus(TicketStatus.AdminReply);

        return new ResultResponse<>(ticketService.update(ticketDto));
    }

    @PutMapping(value = "/ticket/close")
    public BaseResponse<Integer> close(@RequestBody TicketDto ticketDto) {

        ticketDto.setStatus(TicketStatus.Finished);

        return new ResultResponse<>(ticketService.update(ticketDto));
    }
}

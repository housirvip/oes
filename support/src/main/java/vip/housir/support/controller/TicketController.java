package vip.housir.support.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.housir.base.response.BaseResponse;
import vip.housir.base.response.ResultResponse;
import vip.housir.support.client.QiniuClient;
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

    private final QiniuClient qiniuClient;

    @GetMapping(value = "/{id}")
    public BaseResponse<Ticket> ticket(@PathVariable Integer id, Authentication auth) {

        return new ResultResponse<>(ticketService.oneById(id, (Integer) auth.getPrincipal()));
    }

    @GetMapping(value = "/git")
    public BaseResponse<String> git() {

        return new ResultResponse<>(qiniuClient.searchRepo("housirvip"));
    }
}

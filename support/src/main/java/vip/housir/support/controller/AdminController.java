package vip.housir.support.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vip.housir.base.response.BaseResponse;
import vip.housir.base.response.ResultResponse;
import vip.housir.support.entity.Notice;
import vip.housir.support.service.NoticeService;

/**
 * @author housirvip
 */
@RestController
@RequestMapping(value = "/support-admin")
@RequiredArgsConstructor
public class AdminController {

    private final NoticeService noticeService;

    @PostMapping(value = "/notice")
    public BaseResponse<Integer> notice(@RequestBody Notice notice) {

        return new ResultResponse<>(noticeService.createOrUpdate(notice));
    }
}

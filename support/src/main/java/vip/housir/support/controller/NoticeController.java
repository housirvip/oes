package vip.housir.support.controller;

import com.github.pagehelper.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.housir.base.dto.PageDto;
import vip.housir.base.response.BaseResponse;
import vip.housir.base.response.PageResponse;
import vip.housir.base.response.ResultResponse;
import vip.housir.support.entity.Notice;
import vip.housir.support.service.NoticeService;

/**
 * @author housirvip
 */
@RestController
@RequestMapping(value = "/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping(value = "/{id}")
    public BaseResponse<Notice> ticket(@PathVariable Integer id) {

        return new ResultResponse<>(noticeService.oneById(id));
    }

    @GetMapping(value = "/list")
    public BaseResponse<Page> papers(@Validated PageDto pageDto) {

        Page<Notice> notices = noticeService.pageByParam(pageDto);

        return new PageResponse<>(notices, notices.getTotal());
    }
}

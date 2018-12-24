package vip.housir.exam.controller;

import com.github.pagehelper.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vip.housir.base.request.PageRequest;
import vip.housir.base.response.BaseResponse;
import vip.housir.base.response.PageResponse;
import vip.housir.base.response.ResultResponse;
import vip.housir.exam.entity.Paper;
import vip.housir.exam.service.PaperService;

/**
 * @author housirvip
 */
@RestController
@RequestMapping(value = "/paper")
@RequiredArgsConstructor
public class PaperController {

    private final PaperService paperService;

    @GetMapping(value = "/one")
    public BaseResponse<Paper> one(@RequestParam Integer id) {

        return new ResultResponse<>(paperService.render(id));
    }

    @GetMapping(value = "/list")
    public BaseResponse<Page> list(@Validated PageRequest pageRequest) {

        Page<Paper> papers = paperService.pageByParam(pageRequest);

        return new PageResponse<>(papers, papers.getTotal());
    }
}

package vip.housir.exam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vip.housir.base.response.BaseResponse;
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
    public BaseResponse<Paper> list(@RequestParam Integer id) {

        return new ResultResponse<>(paperService.render(id));
    }
}

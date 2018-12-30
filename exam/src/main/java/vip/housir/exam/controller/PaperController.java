package vip.housir.exam.controller;

import com.github.pagehelper.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import vip.housir.base.dto.PageDto;
import vip.housir.base.response.BaseResponse;
import vip.housir.base.response.PageResponse;
import vip.housir.base.response.ResultResponse;
import vip.housir.exam.entity.Paper;
import vip.housir.exam.service.PaperService;

/**
 * @author housirvip
 */
@RestController
@RequiredArgsConstructor
public class PaperController {

    private final PaperService paperService;

    @GetMapping(value = "/paper/{id}")
    public BaseResponse<Paper> one(@PathVariable Integer id) {

        return new ResultResponse<>(paperService.render(id));
    }

    @GetMapping(value = "/papers")
    public BaseResponse<Page> list(@Validated PageDto pageDto, Authentication auth) {

        Page<Paper> papers = paperService.pageByParam(pageDto.putUid(auth.getPrincipal()));

        return new PageResponse<>(papers, papers.getTotal());
    }
}

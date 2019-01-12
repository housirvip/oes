package vip.housir.exam.controller;

import com.github.pagehelper.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.housir.base.dto.PageDto;
import vip.housir.base.response.BaseResponse;
import vip.housir.base.response.PageResponse;
import vip.housir.base.response.ResultResponse;
import vip.housir.exam.entity.Paper;
import vip.housir.exam.entity.Question;
import vip.housir.exam.entity.Section;
import vip.housir.exam.service.PaperService;
import vip.housir.exam.service.QuestionService;
import vip.housir.exam.service.SectionService;

/**
 * @author housirvip
 */
@RestController
@RequestMapping(value = "/paper")
@RequiredArgsConstructor
public class PaperController {

    private final PaperService paperService;
    private final SectionService sectionService;
    private final QuestionService questionService;

    @GetMapping(value = "/{id}/render")
    public BaseResponse<Paper> render(@PathVariable Integer id) {

        return new ResultResponse<>(paperService.render(id));
    }

    @GetMapping(value = "/list")
    public BaseResponse<Page> papers(@Validated PageDto pageDto, Authentication auth) {

        Page<Paper> papers = paperService.pageByParam(pageDto.putUid((Integer) auth.getPrincipal()));

        return new PageResponse<>(papers, papers.getTotal());
    }

    @GetMapping(value = "/{id}")
    public BaseResponse<Paper> paper(@PathVariable Integer id) {

        return new ResultResponse<>(paperService.oneById(id));
    }

    @GetMapping(value = "/section/{id}")
    public BaseResponse<Section> section(@PathVariable Integer id) {

        return new ResultResponse<>(sectionService.oneById(id));
    }

    @GetMapping(value = "/question/{id}")
    public BaseResponse<Question> question(@PathVariable Integer id) {

        return new ResultResponse<>(questionService.oneById(id));
    }
}

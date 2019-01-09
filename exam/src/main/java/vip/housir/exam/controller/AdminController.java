package vip.housir.exam.controller;

import com.github.pagehelper.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vip.housir.base.dto.PageDto;
import vip.housir.base.response.BaseResponse;
import vip.housir.base.response.PageResponse;
import vip.housir.base.response.ResultResponse;
import vip.housir.exam.entity.Exam;
import vip.housir.exam.entity.Paper;
import vip.housir.exam.entity.Question;
import vip.housir.exam.entity.Section;
import vip.housir.exam.service.ExamService;
import vip.housir.exam.service.PaperService;

/**
 * @author housirvip
 */
@RestController
@RequestMapping(value = "/exam-admin")
@RequiredArgsConstructor
public class AdminController {

    private final ExamService examService;
    private final PaperService paperService;

    @GetMapping(value = "/exam/{id}")
    @PreAuthorize("hasAnyRole('INSPECTOR','ADMIN','ROOT')")
    public BaseResponse<Exam> exam(@PathVariable Integer id) {

        return new ResultResponse<>(examService.one(id, null));
    }

    @GetMapping(value = "/exam/list")
    @PreAuthorize("hasAnyRole('INSPECTOR','ADMIN','ROOT')")
    public BaseResponse<Page> exams(@Validated PageDto pageDto) {

        Page<Exam> examPage = examService.pageByParam(pageDto);

        return new PageResponse<>(examPage, examPage.getTotal());
    }

    @PostMapping(value = "/paper")
    @PreAuthorize("hasAnyRole('ADMIN','ROOT')")
    public BaseResponse<Integer> paper(@RequestBody Paper paper) {

        return new ResultResponse<>(paperService.createOrUpdate(paper));
    }

    @PostMapping(value = "/section")
    @PreAuthorize("hasAnyRole('ADMIN','ROOT')")
    public BaseResponse<Integer> section(@RequestBody Section section) {

        return new ResultResponse<>(paperService.createOrUpdate(section));
    }

    @PostMapping(value = "/question")
    @PreAuthorize("hasAnyRole('ADMIN','ROOT')")
    public BaseResponse<Integer> question(@RequestBody Question question) {

        return new ResultResponse<>(paperService.createOrUpdate(question));
    }
}

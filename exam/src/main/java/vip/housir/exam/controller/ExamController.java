package vip.housir.exam.controller;

import com.github.pagehelper.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vip.housir.base.dto.PageDto;
import vip.housir.base.response.BaseResponse;
import vip.housir.base.response.PageResponse;
import vip.housir.base.response.ResultResponse;
import vip.housir.exam.entity.Exam;
import vip.housir.exam.service.ExamService;

/**
 * @author housirvip
 */
@RestController
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    @GetMapping(value = "/exam/{id}")
    public BaseResponse<Exam> one(@PathVariable Integer id, Authentication auth) {

        return new ResultResponse<>(examService.one(id, (Integer) auth.getPrincipal()));
    }

    @GetMapping(value = "/exams")
    public BaseResponse<Page> list(@Validated PageDto pageDto, Authentication auth) {

        pageDto.putUid((Integer) auth.getPrincipal());

        Page<Exam> examPage = examService.pageByParam(pageDto);

        return new PageResponse<>(examPage, examPage.getTotal());
    }

    @PostMapping(value = "/exam")
    public BaseResponse<Boolean> create(@RequestBody Exam exam, Authentication auth) {

        exam.setUid((Integer) auth.getPrincipal());

        return new ResultResponse<>(examService.submit(exam));
    }
}

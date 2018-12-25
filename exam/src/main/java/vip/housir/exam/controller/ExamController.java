package vip.housir.exam.controller;

import com.github.pagehelper.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vip.housir.base.request.PageRequest;
import vip.housir.base.response.BaseResponse;
import vip.housir.base.response.PageResponse;
import vip.housir.base.response.ResultResponse;
import vip.housir.exam.entity.Exam;
import vip.housir.exam.service.ExamService;

/**
 * @author housirvip
 */
@RestController
@RequestMapping(value = "/exam")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    @GetMapping
    public BaseResponse<Exam> one(@RequestParam Integer id) {

        return new ResultResponse<>(examService.one(id));
    }

    @GetMapping(value = "/list")
    public BaseResponse<Page> list(@Validated PageRequest pageRequest) {

        Page<Exam> examPage = examService.pageByParam(pageRequest);

        return new PageResponse<>(examPage, examPage.getTotal());
    }

    @PostMapping
    public BaseResponse<Boolean> create(@RequestBody Exam exam) {

        return new ResultResponse<>(examService.submit(exam));
    }
}

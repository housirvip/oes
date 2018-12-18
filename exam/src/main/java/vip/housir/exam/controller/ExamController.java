package vip.housir.exam.controller;

import com.github.pagehelper.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vip.housir.base.response.BaseResponse;
import vip.housir.base.response.PageResponse;
import vip.housir.base.response.ResultResponse;
import vip.housir.exam.entity.Exam;
import vip.housir.exam.service.ExamService;

import java.util.Map;

/**
 * @author housirvip
 */
@RestController
@RequestMapping(value = "/exam")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    @GetMapping(value = "/one")
    public BaseResponse<Exam> one(@RequestParam Integer id) {

        return new ResultResponse<>(examService.one(id));
    }

    @GetMapping(value = "/list")
    public BaseResponse<Page> list(@RequestParam Map<String, Object> param) {

        Page<Exam> examPage = examService.pageByParam(param);

        return new PageResponse<>(examPage, examPage.getTotal());
    }

    @PostMapping(value = "/submit")
    public BaseResponse<Integer> submit(@RequestBody Exam exam) {

        return new ResultResponse<>(examService.submit(exam));
    }
}

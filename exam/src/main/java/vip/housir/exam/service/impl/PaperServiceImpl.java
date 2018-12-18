package vip.housir.exam.service.impl;

import com.github.pagehelper.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import vip.housir.base.response.ErrorMessage;
import vip.housir.exam.entity.Paper;
import vip.housir.exam.entity.Question;
import vip.housir.exam.entity.Section;
import vip.housir.exam.mapper.ExamMapper;
import vip.housir.exam.mapper.PaperMapper;
import vip.housir.exam.mapper.QuestionMapper;
import vip.housir.exam.mapper.SectionMapper;
import vip.housir.exam.service.PaperService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author housirvip
 */
@Service
@RequiredArgsConstructor
public class PaperServiceImpl implements PaperService {

    private final PaperMapper paperMapper;
    private final SectionMapper sectionMapper;
    private final QuestionMapper questionMapper;
    private final ExamMapper examMapper;

    @Override
    public Paper render(Integer id) {

        //查找试卷
        Paper paper = paperMapper.selectByPrimaryKey(id);
        Assert.notNull(paper, ErrorMessage.PAPER_NOT_FOUND);

        //TODO 用户等级验证

        //试卷中没有模块直接返回
        if (paper.getSids() == null || paper.getSids().size() == 0) {
            return paper;
        }

        //装载模块
        Map<Integer, Section> sectionMap = sectionMapper.listInIds(paper.getSids());
        List<Section> sectionList = new ArrayList<>();
        paper.getSids().forEach(sid -> {

            Section section = sectionMap.get(sid);
            sectionList.add(section);

            //模块中没有题目，continue
            if (section.getQids() == null || section.getQids().size() == 0) {
                return;
            }

            //装载题目
            List<Question> questionList = new ArrayList<>();
            Map<Integer, Question> questionMap = questionMapper.listInIds(section.getQids());

            section.getQids().forEach(qid -> questionList.add(questionMap.get(qid)));

            section.setQuestions(questionList);
        });

        paper.setSections(sectionList);

        return paper;
    }

    @Override
    public Page<Paper> pageByParam(Map<String, Object> param) {

        Page<Paper> paperPage = paperMapper.listByParam(param);

        List<Integer> pids = new ArrayList<>();
        paperPage.forEach(p -> pids.add(p.getId()));

        Map<String, Object> countParam = new HashMap<>(2);
        //TODO uid 设置
        countParam.put("uid", null);
        countParam.put("pids", pids);

        Map<Integer, Map<String, Long>> countRes = examMapper.countTimesByPids(countParam);
        paperPage.forEach(p -> {
            Map<String, Long> map = countRes.get(p.getId());
            if (map != null) {
                p.setTimes(map.get("times"));
            }
        });

        return paperPage;
    }
}

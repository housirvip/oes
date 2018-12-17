package vip.housir.exam.service.impl;

import com.github.pagehelper.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vip.housir.exam.entity.Paper;
import vip.housir.exam.entity.Question;
import vip.housir.exam.entity.Section;
import vip.housir.exam.mapper.PaperMapper;
import vip.housir.exam.mapper.QuestionMapper;
import vip.housir.exam.mapper.SectionMapper;
import vip.housir.exam.service.PaperService;

import java.util.ArrayList;
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

    @Override
    public Paper render(Integer id) {

        //查找试卷
        Paper paper = paperMapper.selectByPrimaryKey(id);
        if (paper.getSids() == null || paper.getSids().size() == 0) {
            return paper;
        }

        //装载模块
        Map<Integer, Section> sectionMap = sectionMapper.listInIds(paper.getSids());
        List<Section> sectionList = new ArrayList<>();
        for (Integer sid : paper.getSids()) {
            Section section = sectionMap.get(sid);
            sectionList.add(section);

            if (section.getQids() == null || section.getQids().size() == 0) {
                continue;
            }

            //装载题目
            List<Question> questionList = new ArrayList<>();
            Map<Integer, Question> questionMap = questionMapper.listInIds(section.getQids());
            for (Integer qid : section.getQids()) {
                questionList.add(questionMap.get(qid));
            }

            section.setQuestions(questionList);
        }
        paper.setSections(sectionList);

        return paper;
    }

    @Override
    public Page<Paper> pageByParam(Map<String, Object> param) {
        return paperMapper.listByParam(param);
    }
}

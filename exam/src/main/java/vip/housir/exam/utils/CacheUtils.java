package vip.housir.exam.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import vip.housir.exam.entity.Paper;
import vip.housir.exam.entity.Question;
import vip.housir.exam.entity.Section;
import vip.housir.exam.mapper.PaperMapper;
import vip.housir.exam.mapper.QuestionMapper;
import vip.housir.exam.mapper.SectionMapper;

import java.util.List;

/**
 * @author housirvip
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CacheUtils {

    private final PaperMapper paperMapper;
    private final SectionMapper sectionMapper;
    private final QuestionMapper questionMapper;

    @Cacheable(value = "paper", key = "#p0")
    public Paper getPaper(Integer id) {

        log.warn("getPaper: " + id);

        return paperMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value = "paper-full", key = "#p0.id")
    public Paper loadPaper(Paper paper) {

        log.warn("loadPaper: " + paper.getId());

        //试卷中没有模块，return
        List<Section> sectionList = sectionMapper.listByPid(paper.getId());
        if (sectionList.isEmpty()) {
            return paper;
        }

        //装载模块
        paper.setSections(sectionList);

        sectionList.forEach(section -> {

            //模块中没有题目，continue
            List<Question> questionList = questionMapper.listBySid(section.getId());
            if (questionList.isEmpty()) {
                return;
            }

            //装载题目
            section.setQuestions(questionList);
        });

        return paper;
    }
}

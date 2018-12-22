package vip.housir.exam.service.impl;

import com.github.pagehelper.Page;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import vip.housir.base.client.UserClient;
import vip.housir.base.dto.UserDto;
import vip.housir.base.response.ErrorMessage;
import vip.housir.exam.entity.Paper;
import vip.housir.exam.entity.Question;
import vip.housir.exam.entity.Section;
import vip.housir.exam.mapper.ExamMapper;
import vip.housir.exam.mapper.PaperMapper;
import vip.housir.exam.mapper.QuestionMapper;
import vip.housir.exam.mapper.SectionMapper;
import vip.housir.exam.service.PaperService;

import java.util.List;
import java.util.Map;

/**
 * @author housirvip
 */
@Service
@RequiredArgsConstructor
public class PaperServiceImpl implements PaperService {

    private final ExamMapper examMapper;
    private final PaperMapper paperMapper;
    private final SectionMapper sectionMapper;
    private final QuestionMapper questionMapper;

    private final UserClient userClient;

    @Override
    public Paper render(Integer id) {

        //查找试卷
        Paper paper = paperMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(paper, ErrorMessage.PAPER_NOT_FOUND);

        //用户等级验证
        UserDto userDto = userClient.one().getResult();
        Preconditions.checkNotNull(userDto, ErrorMessage.PAPER_NOT_FOUND);
        Preconditions.checkState(paper.getMinLevel() <= userDto.getLevel(), ErrorMessage.PAPER_LEVEL_LIMIT);

        //试卷中没有模块直接返回
        if (paper.getSids() == null || paper.getSids().size() == 0) {
            return paper;
        }

        //装载模块
        Map<Integer, Section> sectionMap = sectionMapper.listInIds(paper.getSids());
        List<Section> sectionList = Lists.newArrayList();
        paper.getSids().forEach(sid -> {

            Section section = sectionMap.get(sid);
            sectionList.add(section);

            //模块中没有题目，continue
            if (section.getQids() == null || section.getQids().isEmpty()) {
                return;
            }

            //装载题目
            List<Question> questionList = Lists.newArrayList();
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

        List<Integer> pids = Lists.newArrayList();
        paperPage.forEach(p -> pids.add(p.getId()));

        //查询用户试卷完成次数
        Integer uid = (Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ImmutableMap<String, Object> countParam = ImmutableMap.of("uid", uid, "pids", pids);
        Map<Integer, Map<String, Long>> countResult = examMapper.countTimesByPids(countParam);
        paperPage.forEach(p -> {
            Map<String, Long> map = countResult.get(p.getId());
            if (map != null) {
                p.setTimes(map.get("times"));
            }
        });

        return paperPage;
    }
}

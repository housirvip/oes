package vip.housir.exam.service.impl;

import com.github.pagehelper.Page;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vip.housir.base.client.UserClient;
import vip.housir.base.constant.Constant;
import vip.housir.base.constant.ErrorMessage;
import vip.housir.base.dto.PageDto;
import vip.housir.base.dto.UserDto;
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
import java.util.Optional;

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

    @Value("${exam.time-limit}")
    private Integer timeLimit;

    @Override
    public Paper render(Integer id) {

        //查找试卷
        Paper paper = paperMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(paper, ErrorMessage.PAPER_NOT_FOUND);

        //用户等级验证
        UserDto userDto = userClient.user().getResult();
        Preconditions.checkNotNull(userDto, ErrorMessage.USER_NOT_FOUND);
        Preconditions.checkArgument(paper.getMinLevel() <= userDto.getLevel(), ErrorMessage.PAPER_LEVEL_LIMIT);

        //次数上限验证
        Map<Integer, Map<String, Long>> countResult = examMapper.countTimesByPids(
                ImmutableMap.of(Constant.PIDS, ImmutableList.of(id), Constant.UID, userDto.getId()));
        Optional.ofNullable(countResult.get(id))
                .map(map -> map.get(Constant.TIMES))
                .ifPresent(times -> Preconditions.checkArgument(times < timeLimit, ErrorMessage.PAPER_TIMES_LIMIT));

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

    @Override
    public Page<Paper> pageByParam(PageDto pageDto) {

        Page<Paper> paperPage = paperMapper.listByParam(pageDto.putParam().getParamAsMap());
        if (paperPage.getTotal() == 0) {
            return paperPage;
        }

        List<Integer> pids = Lists.newArrayList();
        paperPage.forEach(p -> pids.add(p.getId()));
        pageDto.getParamAsMap().put(Constant.PIDS, pids);

        //查询用户试卷完成次数
        Map<Integer, Map<String, Long>> countResult = examMapper.countTimesByPids(pageDto.getParamAsMap());
        paperPage.forEach(p ->
                Optional.ofNullable(countResult.get(p.getId()))
                        .map(map -> map.get(Constant.TIMES))
                        .ifPresent(p::setTimes));

        return paperPage;
    }

    @Override
    public Paper oneById(Integer id) {

        return paperMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer createOrUpdate(Paper paper) {

        if (paper.getId() == null) {
            paperMapper.insertSelective(paper);
        } else {
            paperMapper.updateByPrimaryKeySelective(paper);
        }

        return paper.getId();
    }
}

package vip.housir.base.mq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import vip.housir.base.constant.ErrorMessage;
import vip.housir.base.dto.DingDto;

/**
 * @author housirvip
 */
@Slf4j
@RequiredArgsConstructor
@EnableBinding(value = DingOutput.class)
public class DingSender {

    private final DingOutput dingOutput;

    public void string(String content) {

        DingDto dingDto = new DingDto().putText(content);

        dingOutput.push().send(MessageBuilder.withPayload(dingDto).build());
    }

    public void exception(Exception ex) {

        log.error(ErrorMessage.SERVICE_EXCEPTION, ex);

        String title = "后台异常";
        String content = "### " + title + "\n\n> 异常信息：" + ex.toString() + "`\n\n> 异常位置：`" + ex.getStackTrace()[0].toString() + "`";
        DingDto dingDto = new DingDto().putMarkdown(title, content);

        dingOutput.push().send(MessageBuilder.withPayload(dingDto).build());
    }

    public void signup(Integer uid, String phone, String username) {

        String title = "新用户注册";
        String content = "### " + title + "\n\n> 用户ID：`" + uid + "`\n\n> 手机号码：`" + phone + "`\n\n> 用户名称：`" + username + "`";
        DingDto dingDto = new DingDto().putMarkdown(title, content);

        dingOutput.push().send(MessageBuilder.withPayload(dingDto).build());
    }

    public void recharge(Integer uid, Integer money) {

        String title = "用户充值";
        String content = "### " + title + "\n\n> 用户ID：`" + uid + "`\n\n> 成功充值：`" + money + "`";
        DingDto dingDto = new DingDto().putMarkdown(title, content);

        dingOutput.push().send(MessageBuilder.withPayload(dingDto).build());
    }

    public void sentry(String project, String message, String culprit, String url) {

        String title = "前端异常";
        String content = "### " + title + "\n\n> 异常项目：`" + project + "`\n\n> 异常信息：`" + message + "`\n\n> 异常位置：`" + culprit + "`\n\n> [详情查看](" + url + ")";
        DingDto dingDto = new DingDto().putMarkdown(title, content);

        dingOutput.push().send(MessageBuilder.withPayload(dingDto).build());
    }

    public void ticket(String name, Integer tid, Integer uid) {

        String title = "工单提醒";
        String content = "### " + title + "\n\n> 工单标题：`" + name + "`\n\n> 工单号：`" + tid + "`\n\n> 用户：" + uid + "`";
        DingDto dingDto = new DingDto().putMarkdown(title, content);

        dingOutput.push().send(MessageBuilder.withPayload(dingDto).build());
    }
}

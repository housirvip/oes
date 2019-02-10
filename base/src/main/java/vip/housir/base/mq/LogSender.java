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
@EnableBinding(value = LogOutput.class)
public class LogSender {

    private final LogOutput logOutput;

    public void string(String content) {

        DingDto dingDto = new DingDto().putText(content);

        logOutput.log().send(MessageBuilder.withPayload(dingDto).build());
    }

    public void exception(Exception ex) {

        log.error(ErrorMessage.SERVICE_EXCEPTION, ex);

        String content = "### " + ex.toString() + "\n\n > " + ex.getStackTrace()[0].toString();
        DingDto dingDto = new DingDto().putMarkdown("JavaException", content);

        logOutput.log().send(MessageBuilder.withPayload(dingDto).build());
    }

    public void signup(Integer uid, String phone, String username) {

        String title = "新用户注册";
        String content = "### " + title + "\n\n> 用户ID：`" + uid + "`\n\n> 手机号码：`" + phone + "`\n\n> 用户名称：`" + username + "`";
        DingDto dingDto = new DingDto().putMarkdown(title, content);

        logOutput.log().send(MessageBuilder.withPayload(dingDto).build());
    }

    public void recharge(Integer uid, Integer money) {

        String title = "用户充值";
        String content = "### " + title + "\n\n> 用户ID：`" + uid + "`\n\n> 成功充值：`" + money + "`";
        DingDto dingDto = new DingDto().putMarkdown(title, content);

        logOutput.log().send(MessageBuilder.withPayload(dingDto).build());
    }
}

package vip.housir.store.controller;

import com.alipay.api.AlipayApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vip.housir.base.constant.TradeStatus;
import vip.housir.store.alipay.AlipayManager;
import vip.housir.store.service.RechargeService;

import java.util.Map;

/**
 * @author housirvip
 */
@RestController
@RequestMapping(value = "/noauth/store")
@RequiredArgsConstructor
public class NoAuthController {

    private final RechargeService rechargeService;

    private final AlipayManager alipayManager;

    @PostMapping(value = "/notify")
    public String alipay(@RequestParam Map<String, String> param) throws AlipayApiException {

        if (!alipayManager.verify(param)) {
            return TradeStatus.Failure.getValue();
        }

        rechargeService.finish(param);
        return TradeStatus.Success.getValue();
    }
}

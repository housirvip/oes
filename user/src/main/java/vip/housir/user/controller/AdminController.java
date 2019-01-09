package vip.housir.user.controller;

import com.github.pagehelper.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vip.housir.base.dto.PageDto;
import vip.housir.base.dto.TradeDto;
import vip.housir.base.response.BaseResponse;
import vip.housir.base.response.PageResponse;
import vip.housir.base.response.ResultResponse;
import vip.housir.user.entity.User;
import vip.housir.user.service.UserService;
import vip.housir.user.service.WalletService;

/**
 * @author housirvip
 */
@RestController
@RequestMapping(value = "/user-admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final WalletService walletService;

    @GetMapping(value = "/user/list")
    @PreAuthorize("hasAnyRole('INSPECTOR','ADMIN','ROOT')")
    public BaseResponse<Page> users(@Validated PageDto pageDto) {

        Page<User> userPage = userService.pageByParam(pageDto);

        return new PageResponse<>(userPage, userPage.getTotal());
    }

    @PutMapping(value = "/levelUp")
    @PreAuthorize("hasAnyRole('ADMIN','ROOT')")
    public BaseResponse<Boolean> levelUp(@RequestBody TradeDto tradeDto) {

        return new ResultResponse<>(userService.levelUp(tradeDto));
    }

    @PutMapping(value = "/award")
    @PreAuthorize("hasAnyRole('ADMIN','ROOT')")
    public BaseResponse<Boolean> award(@RequestBody TradeDto tradeDto) {

        return new ResultResponse<>(walletService.award(tradeDto));
    }
}

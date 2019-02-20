package vip.housir.support.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.housir.base.mq.DingSender;
import vip.housir.support.entity.Sentry;

/**
 * @author housirvip
 */
@RestController
@RequestMapping(value = "/noauth")
@RequiredArgsConstructor
public class NoAuthController {

    private final DingSender dingSender;

    @PostMapping(value = "sentry")
    public String push(@RequestBody Sentry sentry) {

        dingSender.sentry(sentry.getProject(), sentry.getMessage(), sentry.getCulprit(), sentry.getUrl());

        return "success";
    }
}

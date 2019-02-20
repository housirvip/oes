package vip.housir.support.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author housirvip
 */
@Data
public class Sentry implements Serializable {

    private String project;

    private String message;

    private String url;

    private String culprit;

    private static final long serialVersionUID = 1L;
}
